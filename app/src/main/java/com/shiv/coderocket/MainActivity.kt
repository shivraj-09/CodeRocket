package com.shiv.coderocket

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var rocket: TextView
    private lateinit var star: TextView
    private lateinit var codeInput: EditText
    private lateinit var runButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rocket = findViewById(R.id.rocket)
        star = findViewById(R.id.star)
        codeInput = findViewById(R.id.codeInput)
        runButton = findViewById(R.id.runButton)

        runButton.setOnClickListener {

            val code = codeInput.text.toString()

            val commands = code.split("\n")

            for (command in commands) {

                when (command.trim()) {

                    "moveRight()" -> {
                        if (rocket.x < 800) {
                            rocket.x += 100
                        }
                    }

                    "moveLeft()" -> {
                        if (rocket.x > 0) {
                            rocket.x -= 100
                        }
                    }

                    "moveDown()" -> {
                        if (rocket.y < 800) {
                            rocket.y += 100
                        }
                    }

                    "moveUp()" -> {
                        if (rocket.y > 0) {
                            rocket.y -= 100
                        }
                    }

                    "" -> {
                        // Ignore empty lines
                    }

                    else -> {
                        Toast.makeText(
                            this,
                            "Invalid Command: $command",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            checkWin()
        }
    }

    private fun checkWin() {

        val distanceX = abs(rocket.x - star.x)
        val distanceY = abs(rocket.y - star.y)

        if (distanceX < 100 && distanceY < 100) {

            Toast.makeText(
                this,
                "Level Complete! 🚀",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}