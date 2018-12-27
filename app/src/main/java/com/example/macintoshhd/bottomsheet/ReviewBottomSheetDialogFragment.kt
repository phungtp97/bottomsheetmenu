package com.example.macintoshhd.bottomsheet

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ReviewBottomSheetDialogFragment : BottomSheetDialogFragment() {

    interface ReviewBottomSheetListener{
        fun onEditClick()
        fun onDeleteClick()
        fun onCloseClick()
    }

    private lateinit var mListener : ReviewBottomSheetListener

    open fun newInstance(): ReviewBottomSheetDialogFragment {
        return ReviewBottomSheetDialogFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = context as ReviewBottomSheetListener
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.layout_review_bottom_sheet, container,
            false
        )
        init(view)
        return view
    }

    fun init(view: View){
        val btnEdit = view.findViewById<TextView>(R.id.item_tv_edit)
        val btnDelete = view.findViewById<TextView>(R.id.item_tv_delete)
        val btnClose = view.findViewById<TextView>(R.id.item_tv_close)

        btnEdit.setOnClickListener { mListener.onEditClick() }
        btnDelete.setOnClickListener { mListener.onDeleteClick() }
        btnClose.setOnClickListener { mListener.onCloseClick() }

    }
}