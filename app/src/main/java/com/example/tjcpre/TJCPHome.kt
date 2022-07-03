package com.example.tjcpre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TJCPHome : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tjcphomepage)

        val TJCPimgView : ImageView =  findViewById(R.id.TJCPTitle)
        TJCPimgView.setImageResource(R.drawable.tjcpwareagle)

        val EFTtxt : Button = findViewById(R.id.EscapeFromTarkove)
        val Pathstxt : Button = findViewById(R.id.Paths)
        val ItemTrakertxt : Button = findViewById(R.id.ItemTraker)
        val Logintxt : Button = findViewById(R.id.Login)

        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{


            // TEMP: Switches to MapView Mode
            val intent = Intent(this,MapSelectView::class.java)
            startActivity(intent)


        }
        findViewById<Button>(R.id.ItemTraker).setOnClickListener{

            ///TODO: change to switch Item Tracker Screen

        }
        findViewById<Button>(R.id.Paths).setOnClickListener{


            val intent = Intent(this,PathSelectView::class.java)
            startActivity(intent)

        }
        findViewById<Button>(R.id.Login).setOnClickListener{

            ///TODO: change to switch to Login Screen

        }




    }


}