package com.example.youtube

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Method to add a desired [Fragment] to the required layout with [containerId].
     */
    fun addFragmentToContainer(fragment: Fragment, @IdRes containerId: Int) {

        if(supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName) != null){
            return
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerId, fragment, fragment::class.java.simpleName)
        transaction.commit()
    }

    /**
     * Method to replace a desired [Fragment] in the required layout with [containerId].
     */
    fun replaceFragmentInContainer(fragment: Fragment, @IdRes containerId: Int) {

        if(supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName) != null){
            return
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment, fragment::class.java.simpleName)
        transaction.commit()
    }
}