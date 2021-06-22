package com.example.firstkotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AddDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        var builder:AlertDialog.Builder = AlertDialog.Builder(requireContext(),)
        builder.setTitle("Success")
        builder.setMessage("Record Added Successfully")
        builder.setPositiveButton("Ok", DialogInterface.OnClickListener{
                dialogInterface: DialogInterface, i:Int ->
                dismiss()
                var intent = Intent(requireContext(),StudentActivity:: class.java)
                startActivity(intent)
        })

        return builder.create()
    }
}