package com.example.tjcpre

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class LogInView: AppCompatActivity(){
    //lateinit var dataBase : DatabaseRefrence
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance();





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)

        val LogInButton = findViewById(R.id.Login_button) as Button
        val CreateAccountButton = findViewById<Button>(R.id.CreateAccountButton)

        val UsernameInput = findViewById<EditText>(R.id.EditUserString)
        val PasswordInput = findViewById<EditText>(R.id.EditPassworedString)

        LogInButton.setOnClickListener {

            var sendUser : String = UsernameInput.text.toString()
            var sendPassword : String = PasswordInput.text.toString()

            if (sendUser.isNotEmpty()) {

                ReadData(sendUser)
            }
            //TODO: Implament fragment to Notify user of Empty parameters
            if (sendPassword.isNotEmpty())
            {

            }
            //TODO: Implament fragment to Notify user of Empty parameters






        }
        CreateAccountButton.setOnClickListener {
            //TODO: Implement Action For Create Login Button

        }



    }

    private fun ReadData(input: String) {




    }

}