package com.example.youtube

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.youtube.common.MAX_ANIM_PROGRESS
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), VideoFragment.OnTransitionListener {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_trending -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subscriptions -> {
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_inbox -> {
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_library -> {
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null

        bnMainNavigation.setOnNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener
        )

        // Adding the Video Fragment to the container.
        val videoFragment = VideoFragment.newInstance(this@MainActivity)
        replaceFragmentInContainer(videoFragment, flFragmentContainer.id)

        mlRoot.transitionToEnd()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    /**
     * Method to listen to the progress of the transition animation of [VideoFragment].
     */
    override fun onProgress(progress: Float) {
        // Playing the scene in the opposite direction to animate and display
        // the Toolbar and the Bottom Navigation bar.
        mlRoot.progress = (MAX_ANIM_PROGRESS - progress).also {
            Log.d(TAG, "Main progress: $it")
        }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
