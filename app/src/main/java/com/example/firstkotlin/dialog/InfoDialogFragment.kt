package com.example.firstkotlin.dialog

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.app.Dialog as Dialog1

//import com.example.firstkotlin.R

class InfoDialogFragment : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog1 {
        super.onCreate(savedInstanceState)

        var builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Info Dialog")
        builder.setMessage("This is an Info Dialog Fragment")
//        builder.setIcon(dialogActivity.resources.getDrawable(R.drawable.ic_success_foreground))
        builder.setPositiveButton("Great", DialogInterface.OnClickListener { dialog, which ->
            dismiss()
        })

        return builder.create()
    }
}