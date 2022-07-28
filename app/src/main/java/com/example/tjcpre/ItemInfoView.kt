package com.example.tjcpre

import android.content.ClipData
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemInfoView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iteminfoview)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        var ItemName = findViewById<TextView>(R.id.ItemInfo_ItemName)
        ItemName.text = intent.getStringExtra("itemName")


    }



}