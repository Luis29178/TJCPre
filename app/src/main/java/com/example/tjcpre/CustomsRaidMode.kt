package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CustomsRaidMode : AppCompatActivity(){

    companion object{
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customs_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        var CustomsMap : ZoomableImage = findViewById(R.id.CustomsMap)
        CustomsMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.custumsmapog))

    }


}