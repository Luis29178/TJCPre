package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ItemTrackerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemtraker)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


        val Database_ItemName = ArrayList<String>()
        val listView = findViewById<ListView>(R.id.ItemTrakerListView)
        listView.adapter = CustomItemTrackerViewAdapter(this)
        listView.isClickable = true




    }

    override fun onStop() {
        super.onStop()

        val listView = findViewById<ListView>(R.id.ItemTrakerListView)
        var ItemIdPos = 1 // starts at one to avoid Info Item


        val dataBase = Firebase.firestore
        //                        TODO: Implement UID as Branch for Each Users Data
        val UserItemDoc = dataBase.document("testUsers/Uid")
        //                Simulates Format In FireStore
        var UploadData : MutableMap<String,Any> = HashMap()


        for (Item in listView)
        {
            val UploadValue : Int = Item.findViewById<TextView>(R.id.ItemTracker_ListItemCount).text.toString().toInt()

            if (UploadValue > 0)
            {
                // this will log a pair with Key,Value or in here ItemId, Count
                UploadData[ItemIdPos.toString()] = UploadValue
                UserItemDoc.set(UploadData, SetOptions.merge())

            }


            ItemIdPos++
        }






    }


}