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
import com.laserbotlabs.securitylibrary.model.Vulnerability
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_INT
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_STRING
import kotlinx.android.synthetic.main.list_item.view.*


class VulnerabilitiesAdapter(
    private val context: Context,
    private val vulnerabilities: List<Vulnerability>
) :
    RecyclerView.Adapter<VulnerabilitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutIdForListItem = R.layout.list_item
        val view = inflater.inflate(layoutIdForListItem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vulnerability: Vulnerability = vulnerabilities[position]
        val name = vulnerability.name
        holder.itemTextView.text = name
        holder.itemImageView.contentDescription = name
        holder.itemImageView.setImageResource(R.drawable.ic_bug)
        holder.itemImageView.setColorFilter(
            ContextCompat.getColor(context, vulnerability.imageResource),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    override fun getItemCount(): Int {
        return vulnerabilities.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemTextView: TextView = itemView.item_tv
        val itemImageView: ImageView = itemView.item_iv

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            val bundle = bundleOf(EXTRA_INT to adapterPosition, EXTRA_STRING to "vulnerability")
            v.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }
}