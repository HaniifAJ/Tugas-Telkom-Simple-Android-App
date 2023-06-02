package com.example.tugastelkom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugastelkom.data.model.User
import com.example.tugastelkom.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var users = mutableListOf<User>(
        User (
            "haniifaj",
            "qwerty123",
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doLogin()
        doRegister()
    }

    private fun doLogin() {
        binding.btnLogin.setActions {
            var found = users.filter{ it.username == binding.etUsername.text.toString() }
            if(found.isEmpty()){
                Toast.makeText(this, "Username or Password incorrect!", Toast.LENGTH_SHORT).show()
                return@setActions
            }
            val foundUser = found[0]
            if (binding.etPassword.text.toString() == foundUser.password) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WelcomePageActivity::class.java)
                Log.d("login", intent.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username or Password incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doRegister() {
        binding.btnRegister.setActions {
            var found = users.filter{ it.username == binding.etUsername.text.toString() }
            if(found.isNotEmpty()){
                Toast.makeText(this, "Username is already registered! Please Login!", Toast.LENGTH_SHORT).show()
                return@setActions
            }
            val newUser = User(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
            users.add(newUser)
            Toast.makeText(this, "Register success! Please Login!", Toast.LENGTH_SHORT).show()
        }
    }
}
