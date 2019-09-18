package com.laserbotlabs.securitylibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.model.Resource
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_INT
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_STRING
import kotlinx.android.synthetic.main.list_item.view.*


class ResourcesAdapter(
    private val context: Context,
    private val resources: List<Resource>
) :
    RecyclerView.Adapter<ResourcesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutIdForListItem = R.layout.list_item
        val view = inflater.inflate(layoutIdForListItem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource: Resource = resources[position]
        val name = resource.name
        holder.itemTextView.text = name
        holder.itemImageView.contentDescription = name
        holder.itemImageView.setImageResource(R.drawable.ic_resource)
        holder.itemImageView.setColorFilter(
            ContextCompat.getColor(context, resource.imageResource),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    override fun getItemCount(): Int {
        return resources.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemTextView: TextView = itemView.item_tv
        val itemImageView: ImageView = itemView.item_iv

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            val bundle = bundleOf(EXTRA_INT to adapterPosition, EXTRA_STRING to "resource")
            v.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }
}