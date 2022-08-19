package com.appinionbd.gallery.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appinionbd.gallery.R
import kotlinx.android.synthetic.main.paging_state.view.*

class PagingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagingStateAdapter.PagingStateViewHolder>() {


    class PagingStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val errorMessage: TextView = itemView.error_msg
        private val loading: ProgressBar = itemView.progress_bar
        private val retryBtn: Button = itemView.retry_button

        init {
            retryBtn.setOnClickListener {
                retry.invoke()
            }
        }


        @SuppressLint("SetTextI18n")
        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorMessage.text = "Load error"
            }
            loading.isVisible = loadState is LoadState.Loading
            errorMessage.isVisible = loadState !is LoadState.Loading
            retryBtn.isVisible = loadState !is LoadState.Loading

        }

    }

    override fun onBindViewHolder(holder: PagingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PagingStateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.paging_state,parent,false),
        retry
    )

}