package com.example.tjcpre

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tjcpre.TJCPHome.Companion.UID
import com.example.tjcpre.TJCPHome.Companion.inPaths
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MapSelectView : AppCompatActivity() {
    companion object{
        var MapSelected = 12
        var SelectPath : Boolean = false
        var pathCount :Int = 0
        var mapString :String = ""
        var pathUri  = ""
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapselectview)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;





        val mapTitle = findViewById<TextView>(R.id.MapTitle)
        mapTitle.text = "Maps"
        val listView = findViewById<ListView>(R.id.MapList)
        listView.adapter = CustomMapViewAdapter(this)
        val pathSelectswitch :Switch = findViewById(R.id.choosePathSwitch)
        var choosingPath: Boolean = false

        pathSelectswitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            choosingPath = !choosingPath
            SelectPath = choosingPath
        })



        listView.isClickable = true

        listView.setOnItemClickListener { parent, view, position, id ->

            MapSelected = position





            if(!choosingPath)// Use Path Switch dependent
            {
                when (position) {
                    // region Customs
                    0 -> {
                        val intent = Intent(this, CustomsRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Interchange
                    1 -> {
                        val intent = Intent(this, InterchangeRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Reserve
                    2 -> {
                        val intent = Intent(this, ReserveRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Labs
                    3 -> {
                        val intent = Intent(this, LabsRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Shoreline
                    4 -> {
                        val intent = Intent(this, ShorelineRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Factory
                    5 -> {
                        val intent = Intent(this, FactoryRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Woods
                    6 -> {
                        val intent = Intent(this, WoodsRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion

                    // region Lighthouse
                    7 -> {
                        val intent = Intent(this, LighthouseRaidMode::class.java)
                        startActivity(intent)
                    }
                    // endregion


                }
            }
            else
            {
                when (position) {

                    // region Customs
                    0 -> {
                        mapString = "Customs"

                    }
                    // endregion

                    // region Interchange
                    1 -> {
                        mapString = "Interchange"

                    }
                    // endregion

                    // region Reserve
                    2 -> {
                        mapString = "Reserve"

                    }
                    // endregion

                    // region Labs
                    3 -> {
                        mapString = "Labs"

                    }
                    // endregion

                    // region Shoreline
                    4 -> {
                        mapString = "Shoreline"

                    }
                    // endregion

                    // region Factory
                    5 -> {
                        mapString = "Factory"

                    }
                    // endregion

                    // region Woods
                    6 -> {
                        mapString = "Woods"

                    }
                    // endregion

                    // region Lighthouse
                    7 -> {
                        mapString = "Lighthouse"

                    }
                    // endregion
                }
                var dataBase =  Firebase.firestore
                var CustomsPathred = dataBase.document("testUsers/${UID}/Paths/${mapString}").get()
                CustomsPathred.addOnSuccessListener { it ->
                    var count = it.get("count")
                    pathCount = count.toString().toInt()
                }



                val intent = Intent(this, RaidModePathSelectView::class.java)
                startActivity(intent)


            }



        }


    }

    override fun onStop() {
        super.onStop()


    }
}