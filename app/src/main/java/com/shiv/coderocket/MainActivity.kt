package com.shiv.coderocket

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var selectedEquationText: TextView
    private lateinit var levelText: TextView
    private lateinit var graphView: GraphView

    private var selectedEquation = ""
    private var currentLevel = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        graphView = findViewById(R.id.graphView)

        levelText = findViewById(R.id.levelText)

        graphView.loadLevel(1)

        selectedEquationText =
            findViewById(R.id.selectedEquation)

        val quadratic =
            findViewById<Button>(R.id.btnQuadratic)

        val cubic =
            findViewById<Button>(R.id.btnCubic)

        val sine =
            findViewById<Button>(R.id.btnSine)

        val run =
            findViewById<Button>(R.id.runButton)

        quadratic.setOnClickListener {

            selectedEquation = "quadratic"

            selectedEquationText.text =
                "Selected: y = x²"

            graphView.equationType = "quadratic"
            graphView.invalidate()
        }

        cubic.setOnClickListener {

            selectedEquation = "cubic"

            selectedEquationText.text =
                "Selected: y = x³"

            graphView.equationType = "cubic"
            graphView.invalidate()
        }

        sine.setOnClickListener {

            selectedEquation = "sine"

            selectedEquationText.text =
                "Selected: y = sin(x)"

            graphView.equationType = "sine"
            graphView.invalidate()
        }

        run.setOnClickListener {

            if (selectedEquation.isEmpty()) {

                Toast.makeText(
                    this,
                    "Select an equation first!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            graphView.moveRocketAlongCurve { success ->

                if (success) {

                    Toast.makeText(
                        this,
                        "🎉 Level Complete!",
                        Toast.LENGTH_LONG
                    ).show()

                    currentLevel++

                    when (currentLevel) {

                        2 -> {
                            graphView.loadLevel(2)
                            levelText.text = "Level 2"
                        }

                        3 -> {
                            graphView.loadLevel(3)
                            levelText.text = "Level 3"
                        }

                        4 -> {

                            Toast.makeText(
                                this,
                                "🏆 You Finished The Game!",
                                Toast.LENGTH_LONG
                            ).show()

                            currentLevel = 1

                            graphView.loadLevel(1)

                            levelText.text = "Level 1"
                        }
                    }

                } else {

                    Toast.makeText(
                        this,
                        "❌ Try Again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}