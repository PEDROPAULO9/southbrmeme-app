package com.example.dev.southbrmemes.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.view.fragment.EditUserFragment
import com.example.dev.southbrmemes.view.fragment.MyMemeFragment
import com.example.dev.southbrmemes.view.fragment.TermFragment
import com.example.dev.southbrmemes.view.fragment.TimeLineMemeFragment
import com.example.dev.southbrmemes.view.popUp.PopUpRegisterMeme
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var timeLineMemeFragment: TimeLineMemeFragment
    private lateinit var myMemeFragment: MyMemeFragment
    private lateinit var editUserFragment: EditUserFragment
    private lateinit var termFragment: TermFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val toolbar = findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        toolbar.setOnClickListener {
            PopUpRegisterMeme(_activity = this).creatPopUpMenu()
        }

        timeLineMemeFragment = TimeLineMemeFragment.getInstance()
        myMemeFragment = MyMemeFragment.getInstance()
        editUserFragment = EditUserFragment.getInstance()
        termFragment = TermFragment.getInstance()

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flLogged, timeLineMemeFragment, null)
                    .commit()


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.logged, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_timeline -> {
                chengefragment(timeLineMemeFragment, R.id.flLogged)
            }
            R.id.nav_my_meme -> {
                chengefragment(myMemeFragment, R.id.flLogged)
            }
            R.id.nav_edit_account -> {
                chengefragment(editUserFragment, R.id.flLogged)
            }
            R.id.nav_loggof -> {
                ChangesActivity.changeActivityNoReturnExit(IndexActivity::class.java, this)
            }
            R.id.nav_share -> {
                share()
            }
            R.id.nav_send -> {
                chengefragment(termFragment, R.id.flLogged)
            }
            R.id.nav_app -> {
                myApps("https://play.google.com/store/apps/developer?id=SnackTime")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun chengefragment(fragment: android.support.v4.app.Fragment, id: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(id, fragment, null)
                .addToBackStack("Fragment")
                .commit()
    }

    fun share() {
        val appPackageName = packageName
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        try {
            intent.putExtra(Intent.EXTRA_TEXT, "conheça o south br memes https://play.google.com/store/apps/details?id=$appPackageName")
            startActivity(intent)
        } catch (e: Exception) {
        }
    }

    fun myApps(url: String) {
        val i = Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onStart() {
        super.onStart()
        aksCheckedPermissions()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun aksCheckedPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            askForPermissions()

        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                7)
    }
}
