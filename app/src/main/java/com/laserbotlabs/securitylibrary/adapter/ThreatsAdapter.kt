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
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_INT
import kotlinx.android.synthetic.main.threat_list_item.view.*


class ThreatsAdapter(
    private val context: Context,
    private val threats: List<Threat>
) :
    RecyclerView.Adapter<ThreatsAdapter.ViewHolder>() {

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
            ContextCompat.getColor(context, threat.imageResource),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        holder.starImageView.visibility = if (threat.canBeTested) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return threats.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemTextView: TextView = itemView.item_tv
        val itemImageView: ImageView = itemView.item_iv
        val starImageView: ImageView = itemView.star_iv

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            val bundle = bundleOf(EXTRA_INT to adapterPosition)
            v.findNavController().navigate(R.id.action_threatsFragment_to_threatDetailFragment, bundle)
        }
    }
}