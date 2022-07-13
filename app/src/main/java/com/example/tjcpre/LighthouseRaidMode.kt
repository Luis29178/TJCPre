package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LighthouseRaidMode : AppCompatActivity(){
    companion object{
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lighthouse_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        var LighthouseMap : ZoomableImage = findViewById(R.id.LighthouseMap)
        LighthouseMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.lighthousemap))



    }

}