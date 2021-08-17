package com.modcom.simon.babyshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var etEmail:TextInputEditText
    private lateinit var etPassword:TextInputEditText
    private lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.etloginEmail)
        etPassword =findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if(!TextUtils.isEmpty(email) && ! TextUtils.isEmpty(password)){
                btnLogin.text = "Signing In..."
                //data is ready to be validated
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { res->
                    if(res.isSuccessful){
                        //here user is successfully logged in,so we have to move to home
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()

                    }else{
                        btnLogin.text ="Try Again"
                        Toast.makeText(this,
                            "something went wrong: ${res.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }else{
                Toast.makeText(this,"Check your email or password",Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun moveToRegister(view: View) {
        startActivity(Intent(this,RegisterActivity::class.java))
        finish()
    }
}