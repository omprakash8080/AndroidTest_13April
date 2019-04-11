package com.android.nytimesarticles.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.android.nytimesarticles.R
import kotlinx.android.synthetic.main.fragment_detail.*




class DetailFragment : BaseFragment() {
    var TAG = "DetailFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(com.android.nytimesarticles.R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.setTitle("Article Detail")
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(true)
        setHasOptionsMenu(true)


        val mTitle = this.arguments!!.getString("title")!!.toString()
        val mAbstract = this.arguments!!.getString("abstract")!!.toString()

        title.text = mTitle
        list_description.text = mAbstract
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {

                (activity as AppCompatActivity).supportActionBar!!.setTitle(activity!!.getString(R.string.app_name))
                (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(false)

                activity!!.onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): DetailFragment = DetailFragment()
    }
}