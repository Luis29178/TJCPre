package com.example.tjcpre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TJCPHome : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tjcphomepage)

        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{

            ///TODO: change to switch to map selecting activity
            // TEMP: Switches to MapView Mode
            val intent = Intent(this,MapView::class.java)
            startActivity(intent)


        }
        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{

            ///TODO: change to switch to map selecting activity
            // TEMP: Switches to MapView Mode
            val intent = Intent(this,MapView::class.java)
            startActivity(intent)


        }




    }


}