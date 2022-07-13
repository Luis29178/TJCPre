package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ItemTrackerView : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemtraker)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


        val Database_ItemName = ArrayList<String>()
        val listView = findViewById<ListView>(R.id.ItemTrakerListView)
        listView.adapter = CustomItemTrackerViewAdapter(this)
        listView.isClickable = true






    }


}