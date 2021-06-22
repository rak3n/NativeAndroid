package com.example.firstkotlin.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.firstkotlin.R

class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        var builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Uploading Image")
        var view=activity?.layoutInflater?.inflate(R.layout.progress_layout, null, false)
        builder.setView(view)
        var dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false);
        var progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar?.setOnClickListener(){
            dismiss()
        }
        return dialog
    }
}