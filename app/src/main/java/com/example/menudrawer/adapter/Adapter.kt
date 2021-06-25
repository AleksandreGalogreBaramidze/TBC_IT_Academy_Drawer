package com.example.menudrawer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawer.R
import com.example.menudrawer.databinding.ItemBinding
import com.example.menudrawer.model.MenuItems

typealias onClickMenu = (position:Int) -> Unit
class Adapter(private val item: MutableList<MenuItems>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    lateinit var onClickMenu: onClickMenu
    var itemPosition = 0
    override fun getItemCount() = item.size
    fun resetMenu(){
        itemPosition = 0
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
    inner class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var model: MenuItems
        fun onBind() {
            model = item[adapterPosition]
            binding.iconOnMenu.setImageResource(model.image)
            binding.fragmentName.text = model.title
            binding.root.setOnClickListener(this)
            if (adapterPosition == itemPosition){
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context,
                    R.color.teal_200
                ))
                binding.Indicator.visibility = View.VISIBLE
            }else{
                binding.Indicator.visibility = View.INVISIBLE
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
        }
        override fun onClick(v: View?) {
            itemPosition = adapterPosition
            onClickMenu.invoke(adapterPosition)
            notifyDataSetChanged()
        }
    }
    }