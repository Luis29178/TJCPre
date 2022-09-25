package com.example.tjcpre

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TJCPHome : AppCompatActivity() {
    companion object{
        var UID = ""
        var LoggedIn : Boolean = false
        var inRaid = false
        var inPaths = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inRaid = false
        inPaths = false


        if (LoggedIn){
            setContentView(R.layout.tjcphomepagelogedin)

            val userId = intent.getStringExtra("user_id")
            val userIdTxt = findViewById<TextView>(R.id.UserId)
            userIdTxt.text = userId.toString()
            UID = userId.toString()

        }
        else {
            setContentView(R.layout.tjcphomepage)
            findViewById<Button>(R.id.Login).setOnClickListener{
                val intent = Intent(this,LogInView::class.java)
                startActivity(intent)
            }
            findViewById<Button>(R.id.HomeSignup).setOnClickListener{
                val intent = Intent(this,CreateUserView::class.java)
                startActivity(intent)
            }

            UID = ""
        }
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        val TJCPimgView : ImageView =  findViewById(R.id.TJCPTitle)
        TJCPimgView.setImageResource(R.drawable.tjcpwareagle)


        findViewById<Button>(R.id.EscapeFromTarkove).setOnClickListener{


            // TEMP: Switches to MapView Mode
            inRaid = true
            val intent = Intent(this,MapSelectView::class.java)
            startActivity(intent)


        }
        findViewById<Button>(R.id.ItemTraker).setOnClickListener{

            val intent = Intent(this,ItemTrackerMenuView::class.java)
            startActivity(intent)

        }
        findViewById<Button>(R.id.Paths).setOnClickListener{

            inPaths = true
            val intent = Intent(this,PathSelectView::class.java)
            startActivity(intent)

        }






    }


}