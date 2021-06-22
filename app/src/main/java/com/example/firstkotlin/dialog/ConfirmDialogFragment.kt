package com.example.firstkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.firstkotlin.R

class ConfirmDialogFragment: DialogFragment() {

    private lateinit var dialogActivity: DialogActivityActivity


//    constructor(context: Context){
//        dialogActivity=context as DialogActivityActivity
//    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        var builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm")
        builder.setMessage("Are you Sure to exit the App")
        builder.setIcon(dialogActivity.resources.getDrawable(R.drawable.ic_success_foreground))
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            dialogActivity.finish()
        })

        builder.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
            dismiss()
        })
        var dialogFrag = builder.create()
        dialogFrag.setCanceledOnTouchOutside(false)
        return dialogFrag
    }

    //One way to get context.........
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogActivity = context as DialogActivityActivity
    }
}