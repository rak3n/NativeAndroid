package com.example.firstkotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.firstkotlin.Fragments.BluetoothFragment
import com.example.firstkotlin.Fragments.LoginFragment
import com.example.firstkotlin.Fragments.SignUpFragment
import com.google.android.material.navigation.NavigationView


class NavigationActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var toolbar:Toolbar
    private lateinit var drawer:DrawerLayout
    private lateinit var navigationView:NavigationView
    private lateinit var frameLayout: FrameLayout
    private lateinit var preference:SharedPreferences.Editor

    var db=UserDataBase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        init()
        listenChanges()
//        hideItem(true)
        setSupportActionBar(toolbar)
        listenChanges()
        var toogle=ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        navigationView.setNavigationItemSelectedListener{
            var fragment:Fragment?=null;
            when(it.itemId){
                R.id.signup->{
                    fragment = SignUpFragment(db, preference);
                    Toast.makeText(this,"Sign Up",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.login->{
                    fragment = LoginFragment(db, preference) as Fragment
                    Toast.makeText(this,"Log In", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.logout->{
                    fragment = LoginFragment(db, preference) as Fragment
                    preference.putString("login","0").apply()
                    Toast.makeText(this,"Logging Out", Toast.LENGTH_SHORT).show()
                    true
                }
                else->{true}
            }
            var manager=supportFragmentManager
            manager.beginTransaction().replace(R.id.fragment_container,fragment as Fragment).commit();

            it.setChecked(true)
            drawer.close()
            true
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.drawer_menu, menu)
//        var myMenu = menu
//        val item: MenuItem = menu!!.findItem(R.id.setting)
//        item.setVisible(false)
//        return super.onCreateOptionsMenu(menu)
//    }

    fun listenChanges(){
        var pref = getSharedPreferences("Login_Preference", MODE_PRIVATE)
        preference = pref.edit()
        if(pref.getString("login","0")=="0"){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment(db, preference)).commit();
//            hideItem(true)
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BluetoothFragment()).commit();
//            hideItem(false)
        }
        pref.registerOnSharedPreferenceChangeListener(this)
    }


    private fun hideItem() {
        val nav_Menu: Menu = navigationView.menu
        var pref = getSharedPreferences("Login_Preference", MODE_PRIVATE)
        var showAuthOpt=pref.getString("login","0")
        if(showAuthOpt=="0"){
            nav_Menu.findItem(R.id.signup).isVisible = true
            nav_Menu.findItem(R.id.login).isVisible = true
            nav_Menu.findItem(R.id.logout).isVisible = false
        }else{
            nav_Menu.findItem(R.id.signup).isVisible = false
            nav_Menu.findItem(R.id.login).isVisible = false
            nav_Menu.findItem(R.id.logout).isVisible = true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        hideItem()
        val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.onCreateOptionsMenu(menu)
    }

    fun init(){
        toolbar=findViewById(R.id.toolbar)
        drawer=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigationView)
        frameLayout=findViewById(R.id.fragment_container)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (sharedPreferences != null) {
            if(sharedPreferences.getString(key, "0")=="0"){
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment(
                    db,
                    preference
                )).commit();
            }else{
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BluetoothFragment()).commit();
            }
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment(
                db,
                preference
            )).commit();
        }
        this.invalidateOptionsMenu()
    }
}