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


class LogInView: AppCompatActivity(){
    //lateinit var dataBase : DatabaseRefrence
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance();

    companion object{
        var authUId : String = ""
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        val LogInButton = findViewById(R.id.Login_button) as Button
        val CreateAccountButton = findViewById(R.id.CreateAccountButton) as Button

        val UsernameInput = findViewById<EditText>(R.id.EditUserString)
        val PasswordInput = findViewById<EditText>(R.id.EditPassworedString)

        CreateAccountButton.setOnClickListener{

            val intent = Intent(this,CreateUserView::class.java)
            startActivity(intent)
        }

        LogInButton.setOnClickListener {

            var sendUser : String = UsernameInput.text.toString()
            var sendPassword : String = PasswordInput.text.toString()


            when{
                (sendPassword.isEmpty() && sendUser.isEmpty()) -> {
                    Toast.makeText(
                        this@LogInView,
                        "Please enter Username or Email and Password",
                        Toast.LENGTH_SHORT).show()
                }
                sendUser.isEmpty() -> {
                   Toast.makeText(
                       this@LogInView,
                       "Please enter Username or Email",
                       Toast.LENGTH_SHORT).show()

                }
                sendPassword.isEmpty() -> {
                    Toast.makeText(
                        this@LogInView,
                        "Please enter Password",
                        Toast.LENGTH_SHORT).show()

                }
                else -> {
                    val InputEmail : String  = sendUser.trim{ it <= ' ' }
                    val InputPassword : String = sendPassword.trim{ it <= ' '}

                    mAuth.signInWithEmailAndPassword(InputEmail, InputPassword).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //TODO: Implement return to home screen with login credentials
                            Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                            val user : FirebaseUser = task.result!!.user!!
                            val intent =
                                Intent(this@LogInView, TJCPHome::class.java)
                            // removes instances running in the stack when activity is switched
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            authUId = user.uid
                            intent.putExtra("user_id", user.uid)
                            intent.putExtra("User_email",InputEmail)
                            LoggedIn = true
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
                        }
                    }

                }



            }








        }




    }

    private fun ReadData(input: String) {




    }

}