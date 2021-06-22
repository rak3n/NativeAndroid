package com.example.firstkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.firstkotlin.R

class SingalChoiceDialogFragment: DialogFragment() {
    private lateinit var dailogActivity:Communication

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        var builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Make your Choice")
//        builder.setMessage("Choose an Option")
        builder.setItems(R.array.country, DialogInterface.OnClickListener { dialog, which ->
            var countries = activity?.resources?.getStringArray(R.array.country)
            var selectedCountry = countries?.get(which)
            dailogActivity.getName(selectedCountry.toString())
            dismiss()
        })

        var dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dailogActivity = context as DialogActivityActivity
    }
}