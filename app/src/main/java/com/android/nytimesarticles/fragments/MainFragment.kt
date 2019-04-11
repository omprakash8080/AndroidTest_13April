package com.android.nytimesarticles.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nytimesarticles.adapters.ListAdapter
import com.android.nytimesarticles.models.ArticlesResponse
import com.android.nytimesarticles.models.Result
import com.android.nytimesarticles.network.ApiClient
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BaseFragment(), ListAdapter.ItemClickListener {
    internal var results: ArrayList<Result>? = null
    var TAG = "MainFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(com.android.nytimesarticles.R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        results = ArrayList()
        var listAdapter = ListAdapter(results!!)
        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }

        listAdapter.setOnItemClickListener(this)

        val call = ApiClient().getNyTimesApi().getArticlesResponse("3AgX5SjKx4AkogJcUVS6bTPYSIRMX8Ls")
        call.enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                //Log.e(TAG, response.body().toString())
                (results as ArrayList<Result>).addAll(response.body()!!.results)
                listAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Log.e(TAG, t.toString())

            }
        })
    }

    override fun onItemClick(position: Int, v: View) {
        Log.e(TAG, "finally position " + position)

        var result = results!![position]
        val bundle = Bundle()
        bundle.putString("title", result.title)
        bundle.putString("abstract", result.abstract)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle


        val fragmentTransaction = activity!!.supportFragmentManager
            .beginTransaction()
        fragmentTransaction.add(com.android.nytimesarticles.R.id.frame_container, detailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}