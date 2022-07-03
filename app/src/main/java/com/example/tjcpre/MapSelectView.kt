package com.example.tjcpre

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapSelectView : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapselectview)


        val mapTitle = findViewById<TextView>(R.id.MapTitle)
        mapTitle.text = "Maps"
        val listView = findViewById<ListView>(R.id.MapList)
        listView.adapter = CustomAdapter(this)




    }





}