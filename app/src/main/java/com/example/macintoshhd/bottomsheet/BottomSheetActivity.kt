package com.example.macintoshhd.bottomsheet

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.view.menu.MenuPopupHelper
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.PopupWindow
import android.view.ViewGroup
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater



class BottomSheetActivity : AppCompatActivity(), ReviewBottomSheetDialogFragment.ReviewBottomSheetListener {
    override fun onEditClick() {
        Toast.makeText(this@BottomSheetActivity, "You clicked Edit", Toast.LENGTH_LONG).show()
    }

    override fun onDeleteClick() {
        Toast.makeText(this@BottomSheetActivity, "You clicked Delete", Toast.LENGTH_LONG).show()
    }

    override fun onCloseClick() {
        Toast.makeText(this@BottomSheetActivity, "You clicked Close", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        val btnShowPopupMenu = findViewById<Button>(R.id.btn_popupmenu)
        val btnBottomSheetFragment = findViewById<Button>(R.id.btn_bottom_sheet_dialog_fragment)
        val dummyView = findViewById<View>(R.id.dummyview)
        val btnShowPopupWindow = findViewById<TextView>(R.id.btn_popupwindow)
        //on popupmenu
        btnShowPopupMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, dummyView, Gravity.TOP)
            popupMenu.menuInflater.inflate(R.menu.menu_action_sheet, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    Toast.makeText(this@BottomSheetActivity, "You clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show()
                    return true
                }
            })
            try {
                val fields = popupMenu.javaClass.declaredFields
                for (field in fields) {
                    if ("mPopup" == field.name) {
                        field.isAccessible = true
                        val menuPopupHelper = field.get(popupMenu)
                        val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                        val setForceIcons =
                            classPopupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                        setForceIcons.invoke(menuPopupHelper, true)
                        break
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            popupMenu.show()
        }
        //on popupwindow
        btnShowPopupWindow.setOnClickListener {
            val inflater = this@BottomSheetActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.layout_review_bottom_sheet, null) as ViewGroup
            var popupWindow = PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
            popupWindow.showAtLocation(dummyView, Gravity.BOTTOM, 0, 0)
        }
        //on bottomsheetfragment
        btnBottomSheetFragment.setOnClickListener {
            val reviewBottomSheetDialogFragment = ReviewBottomSheetDialogFragment().newInstance()
            reviewBottomSheetDialogFragment.show(
                supportFragmentManager,
                "tag"
            )
        }
    }
}
