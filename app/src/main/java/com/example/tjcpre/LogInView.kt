package com.example.tjcpre

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LogInView: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)

        val LogInButton = findViewById(R.id.Login_button) as Button
        val CreateAccountButton = findViewById<Button>(R.id.CreateAccountButton)

        LogInButton.setOnClickListener {
            //TODO: Implement Action For Login Button

        }
        CreateAccountButton.setOnClickListener {
            //TODO: Implement Action For Create Login Button

        }



    }

}