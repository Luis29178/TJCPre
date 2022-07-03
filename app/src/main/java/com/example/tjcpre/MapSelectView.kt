package com.example.tjcpre

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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


        listView.isClickable = true

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,MapView::class.java)

            // this when loop will be Resposible for assigning
            // witch map will open for raid mode
            when (position) {

                // region Customs
                0 ->{


                    startActivity(intent)
                }
                // endregion

                // region Interchange
                1 ->{


                    startActivity(intent)
                }
                // endregion

                // region Reserve
                2 ->{


                    startActivity(intent)
                }
                // endregion

                // region Labs
                3 ->{


                    startActivity(intent)
                }
                // endregion

                // region Shoreline
                4 ->{


                    startActivity(intent)
                }
                // endregion

                // region Factory
                5 ->{


                    startActivity(intent)
                }
                // endregion

                // region Woods
                6 ->{


                    startActivity(intent)
                }
                // endregion

                // region Lighthouse
                7 ->{


                    startActivity(intent)
                }
                // endregion





            }
        }










    }





}