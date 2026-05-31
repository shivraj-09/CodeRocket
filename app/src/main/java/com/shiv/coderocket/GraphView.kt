package com.shiv.coderocket

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sin
import kotlin.math.sqrt

class GraphView(
    context: Context,
    attrs: AttributeSet?
) : View(context, attrs) {

    private val curvePaint = Paint()
    private val pointPaint = Paint()
    private val textPaint = Paint()

    var equationType = "quadratic"

    var rocketX = 40f
    var rocketY = 0f

    init {

        curvePaint.color = Color.BLUE
        curvePaint.strokeWidth = 8f

        pointPaint.color = Color.RED

        textPaint.color = Color.BLACK
        textPaint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCurve(canvas)
        drawStartAndEnd(canvas)
    }

    private fun drawCurve(canvas: Canvas) {

        var previousX = 0f
        var previousY = height.toFloat()

        for (x in 0..50) {

            val graphX = x * 15f

            val yValue = when (equationType) {

                "quadratic" -> x * x / 5f

                "cubic" -> x * x * x / 300f

                "sine" -> {
                    100 * sin(x / 5.0).toFloat() + 150
                }

                else -> 0f
            }

            val graphY = height - yValue

            canvas.drawLine(
                previousX,
                previousY,
                graphX,
                graphY,
                curvePaint
            )

            previousX = graphX
            previousY = graphY
        }
    }

    private fun drawStartAndEnd(canvas: Canvas) {

        val startX = 40f
        val startY = height - 40f

        val endX = 730f
        val endY = 330f

        if (rocketY == 0f) {
            rocketX = startX
            rocketY = startY
        }

        canvas.drawCircle(
            startX,
            startY,
            15f,
            pointPaint
        )

        canvas.drawText(
            "A",
            startX + 20,
            startY,
            textPaint
        )

        canvas.drawText(
            "🚀",
            rocketX,
            rocketY,
            textPaint
        )

        canvas.drawCircle(
            endX,
            endY,
            15f,
            pointPaint
        )

        canvas.drawText(
            "⭐ B",
            endX + 20,
            endY,
            textPaint
        )
    }

    fun moveRocketAlongCurve(onResult: (Boolean) -> Unit) {

        Thread {

            for (x in 0..50) {

                val graphX = x * 15f

                val yValue = when (equationType) {

                    "quadratic" -> x * x / 5f

                    "cubic" -> x * x * x / 300f

                    "sine" -> {
                        100 * sin(x / 5.0).toFloat() + 150
                    }

                    else -> 0f
                }

                val graphY = height - yValue

                rocketX = graphX
                rocketY = graphY

                postInvalidate()

                Thread.sleep(50)
            }

            val endX = 730f
            val endY = 330f

            val distance = sqrt(
                ((rocketX - endX) * (rocketX - endX) +
                        (rocketY - endY) * (rocketY - endY)).toDouble()
            )

            val success = equationType == "quadratic"

            post {
                onResult(success)
            }

        }.start()
    }
}