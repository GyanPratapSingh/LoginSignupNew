package com.example.loginsignuppageuk

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login_registration_app_kt.LoginResponce

class MainActivity : AppCompatActivity() {

    lateinit var name : TextView
    lateinit var fullname : TextView
    lateinit var email: TextView
    lateinit var userId : TextView
    lateinit var role : TextView
    lateinit var nmtx : TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.profileName)
        fullname = findViewById(R.id.fullname)
        email = findViewById(R.id.profileEmail)
        role = findViewById(R.id.role)
        userId = findViewById(R.id.userid)
        nmtx = findViewById(R.id.nametext)

        val intent = intent
        if (intent.extras != null) {
            val extras = intent.extras
            if (extras != null) {
                val LoginResponce = extras.getSerializable("LoginResponce") as LoginResponce?
                LoginResponce?.let {

                    name.text = LoginResponce.data?.fullName
                    fullname.text = LoginResponce.data?.fullName
                    nmtx.text = LoginResponce.data?.fullName
                    email.text = LoginResponce.data?.email
                    role.text = LoginResponce.data?.role
                    userId.text = LoginResponce.data?.userId



                    Log.e("TAG", "====> ${it.data?.fullName}")
                }
            }
        }

    }
}