package com.laserbotlabs.securitylibrary.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.util.Utils


class ThreatsAdapter(
    private val context: Context,
    private val threats: List<Threat>,
    private val mClickHandler: ThreatsAdapterOnClickHandler
) :
    RecyclerView.Adapter<ThreatsAdapter.ViewHolder>() {

    interface ThreatsAdapterOnClickHandler {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutIdForListItem = R.layout.threat_list_item
        val view = inflater.inflate(layoutIdForListItem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val threat: Threat = threats[position]
        val name = threat.name
        holder.itemTextView.text = name
        holder.itemImageView.contentDescription = name
        holder.itemImageView.setImageResource(R.drawable.ic_bug)
        holder.itemImageView.setColorFilter(
            ContextCompat.getColor(context, Utils.getRandomColor()),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        holder.starImageView.visibility = if (threat.canBeTested) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return threats.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemTextView: TextView = itemView.findViewById(R.id.item_tv)
        val itemImageView: ImageView = itemView.findViewById(R.id.item_iv)
        val starImageView: ImageView = itemView.findViewById(R.id.star_iv)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            mClickHandler.onClick(adapterPosition)
        }
    }
}