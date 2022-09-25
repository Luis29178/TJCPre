package com.example.tjcpre

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PathSelectView : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapselectview)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


        val mapTitle = findViewById<TextView>(R.id.MapTitle)
        mapTitle.text = "Paths"
        val listView = findViewById<ListView>(R.id.MapList)
        listView.adapter = CustomMapViewAdapter(this)


        listView.isClickable = true

        listView.setOnItemClickListener { parent, view, position, id ->

            // this when loop will be Resposible for assigning
            // witch map will open for raid mode
            when (position) {

                // region Customs
                0 ->{

                    val intent = Intent(this,CustomsRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Interchange
                1 ->{
                    val intent = Intent(this,InterchangeRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Reserve
                2 ->{
                    val intent = Intent(this,ReserveRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Labs
                3 ->{
                    val intent = Intent(this,LabsRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Shoreline
                4 ->{
                    val intent = Intent(this,ShorelineRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Factory
                5 ->{
                    val intent = Intent(this,FactoryRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Woods
                6 ->{
                    val intent = Intent(this,WoodsRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion

                // region Lighthouse
                7 ->{
                    val intent = Intent(this,LighthouseRaidMode::class.java)
                    startActivity(intent)
                }
                // endregion





            }
        }










    }





}