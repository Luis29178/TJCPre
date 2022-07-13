package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShorelineRaidMode : AppCompatActivity(){
    companion object{
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shoreline_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        var ShorelineMap : ZoomableImage = findViewById(R.id.ShorelineMap)
        ShorelineMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.shorelinemap))




    }

}