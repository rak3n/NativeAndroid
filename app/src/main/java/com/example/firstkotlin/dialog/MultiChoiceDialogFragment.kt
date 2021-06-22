package com.example.firstkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.firstkotlin.R
import java.util.*

class MultiChoiceDialogFragment : DialogFragment(){
    private lateinit var dailogActivity:Communication

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        var builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Make your Choice")
//        builder.setMessage("Choose an Option")
        var contries = activity?.resources?.getStringArray(R.array.country)
        print(contries)
        var boolean = Array<Boolean>(contries?.size!!, { i->false})
        builder.setMultiChoiceItems(R.array.country, null, DialogInterface.OnMultiChoiceClickListener()
        { dialogInterface: DialogInterface, i: Int, b: Boolean ->
            boolean[i] = b
        })

        builder.setPositiveButton("Done", DialogInterface.OnClickListener { dialog, which ->
            var i=0
            var nameSelected:String=""
            while(i<contries.size){
                if(boolean[i]){
                    nameSelected+=" " + contries[i]
                }
                i++
            }
            dailogActivity.getName(nameSelected.toString())
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