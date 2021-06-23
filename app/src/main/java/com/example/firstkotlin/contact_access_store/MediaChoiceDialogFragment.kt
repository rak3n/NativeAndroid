package com.example.firstkotlin.contact_access_store

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MediaChoiceDialogFragment: DialogFragment() {

    lateinit var listner:Communication

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        var builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Get Media From")
        var data=arrayOf("Gallery", "Camera")
        builder.setItems(data) { dialog, which ->
            if (which == 0) {
                listner.selectVideoFromGallery()
            } else if(which==1) {
                listner.selectVideoFromCamera()
            }else{
                dismiss()
            }
        }
        var dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
//        dialog.show()
        return dialog
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listner = context as Communication
    }


}