package com.example.fodify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private var mList: List<FoodData>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        val foodDescTv: TextView = itemView.findViewById(R.id.foodDesc)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        fun collapseExpandedView(){
            foodDescTv.visibility = View.GONE
        }
    }

    fun setFilteredList(mList: List<FoodData>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        if (position >= 0 && position < mList.size) {
            val foodData = mList[position]
            holder.logo.setImageResource(foodData.logo)
            holder.titleTv.text = foodData.title
            holder.foodDescTv.text = foodData.desc

            val isExpandable: Boolean = foodData.isExpandable
            holder.foodDescTv.visibility = if (isExpandable) View.VISIBLE else View.GONE

            holder.constraintLayout.setOnClickListener {
                isAnyItemExpanded(position)
                foodData.isExpandable = !foodData.isExpandable
                notifyItemChanged(position, Unit)
            }
        }
    }

    private fun isAnyItemExpanded(position: Int){
        val temp = mList.indexOfFirst {
            it.isExpandable
        }

        if (temp >= 0 && temp != position){
            mList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun onBindViewHolder(
        holder: FoodViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == 0) {
            holder.collapseExpandedView()
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }
}