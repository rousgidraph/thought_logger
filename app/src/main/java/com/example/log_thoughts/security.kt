package com.example.log_thoughts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.log_thoughts.databinding.ActivitySecurityBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class security : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var email_view : TextView
    private lateinit var password_view: TextView
    private lateinit var btn_sign : Button
    private lateinit var btn_login : Button

    private lateinit var binding : ActivitySecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_security)
        auth = FirebaseAuth.getInstance()

        email_view = binding.emailEdtText
        password_view = binding.passEdtText
        btn_sign = binding.signupBtn
        btn_login = binding.loginBtn

        btn_sign.setOnClickListener {
            if ((email_view.text != null) && (password_view.text != null)){
                createUserwithEmail(email_view.text.toString(), password_view.text.toString())
            }else{
                email_view.requestFocus()
            }
        }

        btn_login.setOnClickListener {
            if ((email_view.text != null) && (password_view.text != null)){
               signInwithEmail(email_view.text.toString(), password_view.text.toString())
            }else{
                email_view.requestFocus()
            }
        }

    }

    private fun createUserwithEmail(email : String,password : String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( this, OnCompleteListener { task ->
           if(task.isSuccessful){
               Toast.makeText(baseContext, "Created user successfully", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()
           } else{
               Log.i("Error bro ", "createUserwithEmail: "+task.exception.toString())
               Toast.makeText(baseContext, "Something went horribly wrong", Toast.LENGTH_SHORT).show()
           }
        })

    }

    private fun signInwithEmail(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this , OnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Signed in ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                Log.i("Error bro ", "signin UserwithEmail: "+task.exception.toString())
                Toast.makeText(baseContext, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        })
    }
}