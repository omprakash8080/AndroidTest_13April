package com.android.nytimesarticles.activities

import android.os.Bundle
import com.android.nytimesarticles.R
import com.android.nytimesarticles.fragments.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val fragmentTransaction = supportFragmentManager
                .beginTransaction()
            fragmentTransaction.replace( R.id.frame_container, MainFragment())
            fragmentTransaction.commit()
    }
}
