package com.modcom.simon.babyshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var  etEmail:EditText
    private lateinit var  etPassword:EditText
    private lateinit var  etRetypePassword:EditText
    private lateinit var btnSignUp:ExtendedFloatingActionButton


    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etRetypePassword = findViewById(R.id.etRePassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        mAuth = FirebaseAuth.getInstance()
        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass1 = etPassword.text.toString().trim()
            val pass2 = etPassword.text.toString().trim()

            if (!TextUtils.isEmpty(email)) {
                if (!TextUtils.isEmpty(pass1) && !TextUtils.isEmpty(pass2)) {
                    if (pass1.equals(pass2)) {
                        //everything is ready
                        //starting process of registration
                        btnSignUp.text = "Creating Account..."
                        mAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener { res->
                            //check if registration process was successful
                            if(res.isSuccessful){
                                btnSignUp.text = "Account Created Successfully"
                            }else{
                                btnSignUp.text = "Something went wrong"
                                Toast.makeText(this,"Error: ${res.exception?.message}",
                                Toast.LENGTH_SHORT)
                            }

                            }


                        }
                    } else {
                        Toast.makeText(
                            this, "Sorry Your Passwords do not match",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                } else {
                    Toast.makeText(this, "Please Enter a valid Password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }