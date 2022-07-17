package com.example.tjcpre

import android.content.Intent
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tjcpre.LogInView.Companion.LoggedIn

class TJCPHome : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (LoggedIn){
            setContentView(R.layout.tjcphomepagelogedin)

            val userId = intent.getStringExtra("user_id")
            val userIdTxt = findViewById<TextView>(R.id.UserId)
            userIdTxt.text = userId.toString()

        }
        else {
            setContentView(R.layout.tjcphomepage)

            findViewById<Button>(R.id.Login).setOnClickListener{
                val intent = Intent(this,LogInView::class.java)
                startActivity(intent)
            }
        }

        val TJCPimgView : ImageView =  findViewById(R.id.TJCPTitle)
        TJCPimgView.setImageResource(R.drawable.tjcpwareagle)


        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{


            // TEMP: Switches to MapView Mode
            val intent = Intent(this,MapSelectView::class.java)
            startActivity(intent)


        }
        findViewById<Button>(R.id.ItemTraker).setOnClickListener{

            val intent = Intent(this,ItemTrackerMenuView::class.java)
            startActivity(intent)

        }
        findViewById<Button>(R.id.Paths).setOnClickListener{


            val intent = Intent(this,PathSelectView::class.java)
            startActivity(intent)

        }





    }


}