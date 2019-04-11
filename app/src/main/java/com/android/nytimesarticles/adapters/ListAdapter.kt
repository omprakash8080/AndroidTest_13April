package com.android.nytimesarticles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.nytimesarticles.R
import com.android.nytimesarticles.models.Result

class ListAdapter(private val list: List<Result>) : RecyclerView.Adapter<ListAdapter.ArticleViewHolder>() {
    lateinit var mClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(position: Int, v: View)

    }

    fun setOnItemClickListener(clickListener: ItemClickListener) {
        mClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val movie: Result = list[position]
        holder.bind(movie, position)
    }

    override fun getItemCount(): Int = list.size

    inner class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mDescView: TextView? = null
        private var mDateView: TextView? = null
        private var containerView: RelativeLayout? = null

        init {
            mTitleView = itemView.findViewById(R.id.title)
            mDescView = itemView.findViewById(R.id.list_description)
            mDateView = itemView.findViewById(R.id.date)
            containerView = itemView.findViewById(R.id.container)

        }

        fun bind(result: Result, position: Int) {
            mTitleView?.text = result.title
            mDescView?.text = result.byline
            mDateView?.text = result.published_date
            containerView!!.setOnClickListener { v ->
                mClickListener.onItemClick(position, v)
            }
        }
    }
}


