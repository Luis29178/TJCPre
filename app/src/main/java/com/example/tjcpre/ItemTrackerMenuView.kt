package com.example.tjcpre

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ItemTrackerMenuView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemtrackermenue)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        var AllButton = findViewById<Button>(R.id.ItemTrackerMenu_AllItemsButton)
        var QuestNeededButton = findViewById<Button>(R.id.ItemTrackerMenu_QuestNeededButtons)
        var HidoutNeededButton = findViewById<Button>(R.id.ItemTrackerMenu_NeededForHideoutButton)
        AllButton.setOnClickListener{
            val intent = Intent(this@ItemTrackerMenuView, ItemTrackerView::class.java)
            startActivity(intent)

        }
        QuestNeededButton.setOnClickListener{
            //val intent = Intent(this,MapSelectView::class.java)
            //startActivity(intent)

        }

        HidoutNeededButton.setOnClickListener{


        }


    }



}