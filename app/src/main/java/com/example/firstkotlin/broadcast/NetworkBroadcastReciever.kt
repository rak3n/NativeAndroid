package com.example.firstkotlin.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkBroadcastReciever: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        var manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo = manager.activeNetwork
        var comm=context as Communication
        if(activeNetworkInfo!=null){
            comm.showSnackBar("You are online")
        }else{
            comm.showSnackBar("You are offline")
        }
    }
}