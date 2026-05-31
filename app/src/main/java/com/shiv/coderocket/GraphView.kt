package com.shiv.coderocket

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GraphView(
    context: Context,
    attrs: AttributeSet?
) : View(context, attrs) {

    private val paint = Paint()

    var equationType = "quadratic"

    init {
        paint.color = Color.BLUE
        paint.strokeWidth = 8f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var previousX = 0f
        var previousY = height.toFloat()

        for (x in 0..50) {

            val graphX = x * 15f

            val yValue = when (equationType) {

                "quadratic" -> x * x / 5f

                "cubic" -> x * x * x / 300f

                "sine" -> {
                    100 * kotlin.math.sin(
                        x / 5.0
                    ).toFloat() + 150
                }

                else -> 0f
            }

            val graphY =
                height - yValue

            canvas.drawLine(
                previousX,
                previousY,
                graphX,
                graphY,
                paint
            )

            previousX = graphX
            previousY = graphY
        }
    }
}