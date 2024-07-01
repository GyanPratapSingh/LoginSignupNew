package com.example.loginsignuppageuk

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_registration_app_kt.LoginResponce
import com.example.login_registration_app_kt.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity()
{

    lateinit var email_login : EditText
    lateinit var password_login : EditText
    lateinit var button_login : Button
    lateinit var text_login : TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email_login = findViewById(R.id.email);
        password_login = findViewById(R.id.password);
        button_login = findViewById(R.id.btn_login);
        text_login = findViewById(R.id.RegisterNow);

        button_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                if (TextUtils.isEmpty(email_login.text.toString()) || TextUtils.isEmpty(
                        password_login.text.toString())) {
                    Toast.makeText(this@Login, "Both fields required !!", Toast.LENGTH_SHORT)
                        .show();
                }
                val request = LoginRequest()
                request.email = email_login.text.toString()
                request.password = password_login.text.toString()
                request.isGoogleAuth = 0

                RetrofitClient.instance.signIn(request)
                    .enqueue(object : Callback<LoginResponce?> {
                        override fun onResponse(
                            call: Call<LoginResponce?>,
                            response: Response<LoginResponce?>
                        ) {
                            if (response.isSuccessful) {
                                var LoginResponce = response.body()
                                val intent = Intent(this@Login, MainActivity::class.java)
                                intent.putExtra("LoginResponce", LoginResponce)
                                Toast.makeText(this@Login, "Login successful.", Toast.LENGTH_SHORT).show()

                                startActivity(intent)
                                finish()
                            } else {
                                val errorBody = response.errorBody()?.string()
                                Log.e("LoginError","error: ${response.code()},Body:$errorBody")
                                Toast.makeText(
                                    this@Login,
                                    "Login Failed !!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponce?>, t: Throwable) {

                            Log.e("LoginFailure","Error: ${t.message}")
                            Toast.makeText(
                                this@Login,
                                "Error : ${t.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        })
        text_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@Login,Signup::class.java))
                finish();
            }
        })



    }
}