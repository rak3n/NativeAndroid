package com.example.firstkotlin.tabnavigation

import androidx.fragment.app.*
import com.example.firstkotlin.Fragments.CallFragment
import com.example.firstkotlin.Fragments.ChatFragment
import com.example.firstkotlin.Fragments.StatusFragment

class MyAdapter(var manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment?=null
        when(position){
            0-> fragment= ChatFragment()
            1->fragment= StatusFragment()
            2->fragment= CallFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): String? {
        super.getPageTitle(position)

        var title:String=""
        when(position){
            0->title="Chat"
            1->title="Status"
            2->title=""
        }
        return title
    }

}
