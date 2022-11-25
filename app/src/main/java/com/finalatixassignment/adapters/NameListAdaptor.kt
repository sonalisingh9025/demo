package com.finalatixassignment.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.finalatixassignment.R
import com.finalatixassignment.databinding.ItemNameListBinding
import com.finalatixassignment.interfaces.NameSelectListener
import com.finalatixassignment.models.ResponseNameData


class NameListAdaptor(
    private var context: Context,
    private val nameList: ArrayList<ResponseNameData>,
    private val listener: NameSelectListener
): RecyclerView.Adapter<NameListAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNameListBinding>(
            itemView, R.layout.item_name_list, parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvTitle.text = nameList[position].name

        holder.binding.root.setOnClickListener {
            listener.selectName(nameList[position])
        }


    }

    inner class ViewHolder(val binding: ItemNameListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}
