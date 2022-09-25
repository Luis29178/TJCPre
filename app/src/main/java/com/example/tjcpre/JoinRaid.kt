package com.example.tjcpre

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.tjcpre.TJCPHome.Companion.LoggedIn
import com.example.tjcpre.LogInView.Companion.authUId

class JoinRaid : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join_raidview)
        var join_a_raid = false
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        var jbutton = findViewById<Button>(R.id.JoinRaidButton)
        var cbutton = findViewById<Button>(R.id.CreateRaidButton)
        var prompt = findViewById<TextView>(R.id.SelectedPromptRaidOptions)
        var responce = findViewById<EditText>(R.id.RaidID)
        var rAccbtn = findViewById<Button>(R.id.AcceptButtonRaidOption)


        jbutton.setOnClickListener{
            prompt.text = "Enter Raid ID:"
            responce.isClickable = true
            responce.visibility = View.VISIBLE
            rAccbtn.isClickable = true
            rAccbtn.visibility = View.VISIBLE
            join_a_raid =true
        }
        cbutton.setOnClickListener{
            prompt.text = "Create Raid ID:"
            responce.isClickable = true
            responce.visibility = View.VISIBLE
            rAccbtn.isClickable = true
            rAccbtn.visibility = View.VISIBLE
            join_a_raid =false
        }

        val dataBase = Firebase.firestore               //         curr is temp for testing
        //                                              //        v RaidID     v v Data   v
        val UserItemDoc = dataBase.document("RaidData/JoinTheMachine")
        //string creation to acces correct data will be base on the enterd text when joining or creating Raid

        //                Simulates Format In FireStore
        var UploadData : MutableMap<String,Any> = HashMap()

        rAccbtn.setOnClickListener{
            var RaidID = responce.text
            if(!RaidID.isEmpty() && !join_a_raid){
                // upload to the database to create instance of raid
                if(LoggedIn){
                    intent.putExtra("user_id", authUId) // chang to place in database


                }
                else{

                }


            }
            else{
                Toast.makeText(this@JoinRaid,"Empty Entry",Toast.LENGTH_SHORT).show()
                //add toast for different scenarios such as Raid Id Exists
            }
            if(!RaidID.isEmpty() && join_a_raid)
            {
                //check for raid and add to database


            }
            else{
                Toast.makeText(this@JoinRaid,"Empty Entry",Toast.LENGTH_SHORT).show()
                //add toast for different scenarios such as Raid Id dose not  Exists
            }


        }



    }
}