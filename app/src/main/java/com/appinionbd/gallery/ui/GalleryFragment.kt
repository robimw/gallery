package com.appinionbd.gallery.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appinionbd.gallery.R
import com.appinionbd.gallery.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.HttpException


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val viewmodel by viewModels<GalleryViewModel>()

    private val galleryAdapter = GalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerGallery:RecyclerView = view.findViewById(R.id.recycler_gallery)

        val loading:ProgressBar = view.findViewById(R.id.progress_bar)
        val retryBtn:Button = view.findViewById(R.id.retry_button)

        recyclerGallery.also {
            it.layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
            it.hasFixedSize()
            it.adapter= galleryAdapter
        }

        recyclerGallery.adapter = galleryAdapter.withLoadStateFooter(
            footer = PagingStateAdapter{galleryAdapter.retry()}
        )

        retryBtn.setOnClickListener {

            galleryAdapter.retry()
        }

        lifecycleScope.launch {
            viewmodel.photoResult.observe(viewLifecycleOwner) {

                galleryAdapter.submitData(lifecycle,it)
            }


        }

        lifecycleScope.launch {

            galleryAdapter.loadStateFlow.collect { loadState ->

                // Show loading spinner during initial load or refresh.
                loading.isVisible= loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryBtn.isVisible=loadState.source.refresh is LoadState.Error

                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.source.refresh as? LoadState.Error

                when(errorState?.error)
                {
                    is HttpException-> requireView().toast("Please check your network connection")
                }


            }

        }

    }


}