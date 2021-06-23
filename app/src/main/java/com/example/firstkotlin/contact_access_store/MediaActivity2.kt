package com.example.firstkotlin.contact_access_store

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.firstkotlin.R

class MediaActivity2 :Communication, AppCompatActivity() {
    lateinit var  videoView: VideoView
    lateinit var dialogFrag:MediaChoiceDialogFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media2)
        init()
        requestPermissions()
//        dialogFrag = MediaChoiceDialogFragment()
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Get Media From")
        var data=arrayOf("Gallery", "Camera")
        builder.setItems(data) { dialog, which ->
            if (which == 0) {
                selectVideoFromGallery()
            } else if(which==1) {
                selectVideoFromCamera()
            }else{
//                dismiss()
            }
        }
        var dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun init(){
        videoView = findViewById<VideoView>(R.id.videoView)
    }

    override fun selectVideoFromGallery(){
        var intent = Intent(Intent.ACTION_VIEW, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 300)
    }

    override fun selectVideoFromCamera(){
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 100)
    }

    fun requestPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==300 && resultCode== Activity.RESULT_OK && data!=null){
            var uri=data.data
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
        }

        if(requestCode==100 && resultCode== Activity.RESULT_OK && data!=null){
            var uri=data.data
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
        }
    }
}