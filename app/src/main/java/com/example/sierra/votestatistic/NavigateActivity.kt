package com.example.sierra.votestatistic

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.sierra.votestatistic.Fragments.*
import kotlinx.android.synthetic.main.activity_navigate.*
import kotlinx.android.synthetic.main.app_bar_navigate.*


class NavigateActivity :
        AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        VoteFragment.OnFragmentInteractionListener,
        VoteFragment2.OnFragmentInteractionListener,
        ViewPager_fragment.OnFragmentInteractionListener,
        ChooseThemeFragment.OnFragmentInteractionListener,
        SendQuestion.OnFragmentInteractionListener {

    lateinit var voteFragment: VoteFragment
    lateinit var voteFragment2: VoteFragment2
    lateinit var sendFragment: SendQuestion
    lateinit var chooseFragment: ChooseThemeFragment
    lateinit var pager: ViewPager_fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigate)
      //  setSupportActionBar(toolbar)

     //   val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
     //   drawer_layout.addDrawerListener(toggle)
     //   toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        voteFragment= VoteFragment.newInstance()
        sendFragment= SendQuestion.newInstance()
        chooseFragment= ChooseThemeFragment.newInstance()
        voteFragment2= VoteFragment2.newInstance(0)//
        pager= ViewPager_fragment.newInstance()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, VoteFragment())
                       .addToBackStack(VoteFragment.toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
            R.id.nav_gallery -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, SendQuestion())
                        .addToBackStack(SendQuestion.toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
            R.id.nav_slideshow -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, ChooseThemeFragment())
                        .addToBackStack(ChooseThemeFragment.toString())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            R.id.nav_share -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, ViewPager_fragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();


            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
       Log.d("a","a")
    }
}
