package com.alishatergholi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alishatergholi.databinding.CommentItemBinding
import com.alishatergholi.db.entity.CommentEntity

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var items = ArrayList<CommentEntity>()

    fun setComments(comments : List<CommentEntity>){
        this.items.addAll(comments)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding  = CommentItemBinding.inflate(inflater,parent,false)
        return  CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.BindData(items.get(position))
    }

    class CommentViewHolder(private val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun BindData(item : CommentEntity) {
            binding.item = item
        }
    }
}