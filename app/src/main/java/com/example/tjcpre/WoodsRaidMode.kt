package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WoodsRaidMode  : AppCompatActivity(){
    companion object{
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customs_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        var WoodsMap : ZoomableImage = findViewById(R.id.WoodsMap)
        WoodsMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.woodsmap))



    }

}