package com.example.tjcpre

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.tjcpre.TJCPHome.Companion.LoggedIn
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateUserView : AppCompatActivity() {
    private lateinit var itemDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_page)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;



        val UsernameInput  = findViewById<EditText>(R.id.EditCreateUserString)
        val PasswordInput  = findViewById<EditText>(R.id.EditCreatePassworedString)
        val ConfirmPasswordInput  = findViewById<EditText>(R.id.EditConfirmPassworedString)

        val auth = FirebaseAuth.getInstance()

        val CreateNewUser = findViewById<Button>(R.id.createnewAccountButton)


        CreateNewUser.setOnClickListener {
            var _UsernameInput : String = UsernameInput.text.toString()
            var _PasswordInput : String = PasswordInput.text.toString()
            var _ConfirmPasswordInput : String = ConfirmPasswordInput.text.toString()

            if (_UsernameInput.isBlank() || _PasswordInput.isBlank() || _ConfirmPasswordInput.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()

            }

            if (_PasswordInput != _ConfirmPasswordInput) {
                Toast.makeText(
                    this,
                    "Password and Confirm Password do not match",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            // If all credential are correct
            // We call createUserWithEmailAndPassword
            // using auth object and pass the
            // email and pass in it.
            auth.createUserWithEmailAndPassword(_UsernameInput, _PasswordInput)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                        val user : FirebaseUser = task.result!!.user!!
                        val intent =
                            Intent(this@CreateUserView, TJCPHome::class.java)
                        // removes instances running in the stack when activity is switched
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", user.uid)
                        intent.putExtra("User_email",_UsernameInput)
                        LoggedIn = true
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                    }


                }
        }



    }
}