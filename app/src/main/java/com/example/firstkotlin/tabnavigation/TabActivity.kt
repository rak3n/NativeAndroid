package com.example.firstkotlin.tabnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.firstkotlin.R
import com.google.android.material.tabs.TabLayout

class TabActivity : AppCompatActivity() {
    private lateinit var tablayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        init()
        setSupportActionBar(toolbar)
        adapter=MyAdapter(supportFragmentManager)
        viewPager.adapter=adapter
        tablayout.setupWithViewPager(viewPager)
        tablayout.getTabAt(2)?.setIcon(R.drawable.icon1)
    }

    fun init(){
        tablayout = findViewById<TabLayout>(R.id.tab)
        viewPager = findViewById<ViewPager>(R.id.pageViewer)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
    }
}