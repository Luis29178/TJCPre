package com.example.tjcpre

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemTrackerView : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapselectview)
        val Database_ItemName = ArrayList<String>()
        val listView = findViewById<ListView>(R.id.MapList)
        listView.adapter = CustomItemTrackerViewAdapter(this)


    }


}