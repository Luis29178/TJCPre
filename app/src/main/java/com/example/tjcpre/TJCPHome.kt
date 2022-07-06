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


        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{


            // TEMP: Switches to MapView Mode
            val intent = Intent(this,MapSelectView::class.java)
            startActivity(intent)


        }
        findViewById<Button>(R.id.ItemTraker).setOnClickListener{

            val intent = Intent(this,ItemTrackerView::class.java)
            startActivity(intent)

        }
        findViewById<Button>(R.id.Paths).setOnClickListener{


            val intent = Intent(this,PathSelectView::class.java)
            startActivity(intent)

        }
        findViewById<Button>(R.id.Login).setOnClickListener{

            val intent = Intent(this,LogInView::class.java)
            startActivity(intent)
        }




    }


}