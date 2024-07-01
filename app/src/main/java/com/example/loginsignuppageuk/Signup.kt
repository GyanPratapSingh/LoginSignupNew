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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login_registration_app_kt.RetrofitClient
import com.example.login_registration_app_kt.SignupResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signup : AppCompatActivity() {

    lateinit var username_signup: EditText
    lateinit var email_sign: EditText
    lateinit var password_signup: EditText
    lateinit var role_signup: EditText
    lateinit var button_signup: Button
    lateinit var text_signup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username_signup = findViewById(R.id.UserName)
        email_sign = findViewById(R.id.email)
        role_signup = findViewById(R.id.Role)
        password_signup = findViewById(R.id.password)
        button_signup = findViewById(R.id.btn_Register)
        text_signup = findViewById(R.id.loginNow)

        button_signup.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {
                if(TextUtils.isEmpty(email_sign.text.toString()) || TextUtils.isEmpty(password_signup.text.toString()) ||  TextUtils.isEmpty(username_signup.text.toString())
                    || TextUtils.isEmpty(role_signup.text.toString()) ){
                    Toast.makeText(this@Signup, "All fields required !!", Toast.LENGTH_SHORT).show();
                }
                var request = SignupRequest()
                request.fullName = username_signup.text.toString()
                request.email = email_sign.text.toString()
                request.password = password_signup.text.toString()
                request.role = role_signup.text.toString()
                request.isGoogleAuth = 0



                RetrofitClient.instance.signUp(request).enqueue(object : Callback<SignupResponce?> {
                    override fun onResponse(
                        call: Call<SignupResponce?>,
                        response: Response<SignupResponce?>
                    ) {
                        if (response.isSuccessful)
                        {

                            val SignupResponce = response.body()
                            val intent = Intent(this@Signup, Login::class.java)
                            Toast.makeText(this@Signup, "Signup successful! You can now log in.", Toast.LENGTH_SHORT).show()

                            intent.putExtra("signUpResponse", SignupResponce)
                            startActivity(intent)
                            finish()

                        } else {
                            val errorBody = response.errorBody()?.string()
                            Log.e("SignUpError", "Error: ${response.code()}, Body: $errorBody")
                            Toast.makeText(this@Signup, "Sign-Up Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponce?>, t: Throwable) {
                        Toast.makeText(this@Signup, "Error: ${t.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        })

        text_signup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@Signup, Login::class.java))
            }
        })

    }
}