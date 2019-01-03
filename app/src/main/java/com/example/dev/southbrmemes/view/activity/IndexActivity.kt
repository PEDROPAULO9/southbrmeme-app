package com.example.dev.southbrmemes.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.utils.ImageViewAdapter
import com.example.dev.southbrmemes.view.fragment.LoginFragment
import com.example.dev.southbrmemes.view.fragment.TermFragment
import com.example.dev.southbrmemes.view.fragment.TimeLineMemeFragment
import com.example.dev.southbrmemes.view.popUp.PopUpRegisterMeme
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.content_index.*


class IndexActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var timeLineMemeFragment: TimeLineMemeFragment
    private lateinit var loginFragment: LoginFragment
    private lateinit var termFragment: TermFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val toolbar = findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        toolbar.setOnClickListener {
            PopUpRegisterMeme(_activity = this).creatPopUpMenu()
        }

        Hawk.init(this).build()
        ImageViewAdapter()

        if (!SessionManager(this).getPreferences(SessionManager.TOKEN).isEmpty())
            ChangesActivity.changeActivity(LoggedActivity::class.java, this)


        timeLineMemeFragment = TimeLineMemeFragment.getInstance()
        loginFragment = LoginFragment.getInstance()
        termFragment = TermFragment.getInstance()


        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flIndex, timeLineMemeFragment, null)
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
        menuInflater.inflate(R.menu.index, menu)
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
            R.id.nav_home -> {
                chengefragment(timeLineMemeFragment, flIndex.id)
            }
            R.id.nav_login -> {
                chengefragment(loginFragment, flIndex.id)
            }
            R.id.nav_share -> {
                share()
            }
            R.id.nav_term -> {
                chengefragment(termFragment, flIndex.id)
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
    public fun aksCheckedPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            askForPermissions()

        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun askForPermissions() {
        requestPermissions(this,
                arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                7)
    }
}
