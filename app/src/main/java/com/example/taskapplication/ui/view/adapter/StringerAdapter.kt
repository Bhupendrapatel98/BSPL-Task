package com.example.taskapplication.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.data.model.StringerListItem
import com.example.taskapplication.databinding.StringerItemBinding


/**
 * Created by bhupendrapatel on 01/06/22.
 */
class StringerAdapter(
    var items: MutableList<StringerListItem>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<StringerAdapter.StringerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StringerItemBinding.inflate(inflater,parent,false)
        return StringerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StringerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class StringerViewHolder(val binding: StringerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StringerListItem) {
            binding.item = item


            binding.delete.setOnClickListener {
                onItemClickListener.onItemClick(item.StringerID,adapterPosition,"Delete")
                items.remove(items[adapterPosition])
                notifyItemRemoved(adapterPosition)
            }

            binding.edit.setOnClickListener{
                onItemClickListener.onItemClick(item.StringerID,adapterPosition,"Edit")
            }

            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(stringerId:Int,position: Int,type:String)
    }


}