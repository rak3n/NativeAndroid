package com.example.firstkotlin.broadcast

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.BatteryManager
import android.widget.ProgressBar
import android.widget.TextView

class BatteryBroadcastReciever: BroadcastReceiver {
    var progressBar: ProgressBar
    var level:TextView
    var broadcastActivity:BroadcastActivity

    constructor(progressBar: ProgressBar, level: TextView, broadcastActivity: BroadcastActivity){
        this.progressBar = progressBar
        this.level = level
        this.broadcastActivity=broadcastActivity
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        var level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
        this.progressBar.setProgress(level!!)
        this.level.setText(level!!.toString() + " %")
        if(level!!.toInt()<=10){
            var builder= AlertDialog.Builder(context)
            builder.setMessage("Battery too Low to open app....")
            builder.setPositiveButton("Close") { dialog, which ->
                this.broadcastActivity.finish()
            }
            var alert = builder.create()
            alert.show()
        }
    }
}