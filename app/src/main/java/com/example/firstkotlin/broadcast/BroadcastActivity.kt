package com.example.firstkotlin.broadcast

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.firstkotlin.R
import com.google.android.material.snackbar.Snackbar

class BroadcastActivity : AppCompatActivity(), Communication {
    lateinit var level: TextView
    lateinit var otp1:EditText
    lateinit var otp2:EditText
    lateinit var otp3:EditText
    lateinit var otp4:EditText
    lateinit var progressBar: ProgressBar
    lateinit var batteryBroadcastReciever:BatteryBroadcastReciever
    lateinit var otpBroadcastReciever:OTPBroadcastReciever
    lateinit var networkBroadcastReciever: NetworkBroadcastReciever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        init()
        requestPermissions()
        batteryBroadcastReciever = BatteryBroadcastReciever(progressBar, level, this)
        otpBroadcastReciever = OTPBroadcastReciever()
        otpBroadcastReciever.setListener(this)
        networkBroadcastReciever= NetworkBroadcastReciever()
    }

    override fun setOTP(str:String){
        otp1.setText(str[0].toString())
        otp2.setText(str[1].toString())
        otp3.setText(str[2].toString())
        otp4.setText(str[3].toString())
        showSnackBar("Message from$str")
    }

    fun requestPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), 100)
        }
    }

    override fun showSnackBar(msg:String){
        Snackbar.make(this, progressBar, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(batteryBroadcastReciever, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        registerReceiver(otpBroadcastReciever, IntentFilter("android.provider.Telephony.Receive"))
//        registerReceiver(networkBroadcastReciever, IntentFilter(Context.CONNECTIVITY_SERVICE))
//        registerReceiver(networkBroadcastReciever, IntentFilter(Intent.ACTION_MANAGE_NETWORK_USAGE))
        registerReceiver(networkBroadcastReciever, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(batteryBroadcastReciever)
        unregisterReceiver(otpBroadcastReciever)
        unregisterReceiver(networkBroadcastReciever)
        super.onDestroy()
    }

    fun init(){
        progressBar= findViewById(R.id.progressBar2)
        otp1 = findViewById(R.id.otp1)
        otp2 = findViewById(R.id.otp2)
        otp3 = findViewById(R.id.otp3)
        otp4 = findViewById(R.id.otp4)
        level= findViewById(R.id.textView3)
    }
}