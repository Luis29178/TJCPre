package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LabsRaidMode : AppCompatActivity(){
    companion object{
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.labs_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        var LabsMap : ZoomableImage = findViewById(R.id.LabsMap)
        LabsMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.labsmap))




    }

}