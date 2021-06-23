package com.example.firstkotlin.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import androidx.annotation.RequiresApi

class OTPBroadcastReciever: BroadcastReceiver() {
    lateinit var communication:Communication

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onReceive(context: Context?, intent: Intent?) {
        var mess = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        var msg = mess[0].messageBody
        println(msg+"---------------------------------------->")
        communication.showSnackBar("Message from")
        communication.setOTP(msg.toString())
    }

    fun setListener(comm:Communication) {
        this.communication=comm
    }
}