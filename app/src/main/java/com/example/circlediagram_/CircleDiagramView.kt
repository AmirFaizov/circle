package com.example.circlediagram_

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleDiagramView(context: Context) : View(context) {

    private val data = listOf(
        Pair(Color.RED, 25f),
        Pair(Color.BLUE, 10f),
        Pair(Color.MAGENTA, 20f),
        Pair(Color.GREEN, 29f),
        Pair(Color.YELLOW, 16f)
    )

    private val angleOffsets = data.map { it.second / 100f * 360f }.toFloatArray()
    private val paints = data.map { createPaint(it.first) }

    private fun createPaint(color: Int): Paint {
        val paint = Paint()
        paint.color = color
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        return paint
    }
    private fun createStrokePaint(): Paint{
        val paint = Paint()
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.strokeWidth = 4f
        paint.style = Paint.Style.STROKE
        return paint
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width / 2f - 32f

        var startAngle = -180f

        for (i in angleOffsets.indices) {
            canvas?.drawArc(
                centerX - radius, centerY - radius, centerX + radius,
                centerY + radius, startAngle, angleOffsets[i], true, paints[i])
            val percent = "${data[i].second.toInt()}%"
            val textX = centerX + (radius / 2f) * Math.cos(Math.toRadians((startAngle + angleOffsets[i] / 2f).toDouble())).toFloat()
            val textY = centerY + (radius / 2f) * Math.sin(Math.toRadians((startAngle + angleOffsets[i] / 2f).toDouble())).toFloat()
            val endX =
                centerX + radius * Math.cos(Math.toRadians((startAngle + angleOffsets[i]).toDouble())).toFloat()
            val endY =
                centerY + radius * Math.sin(Math.toRadians((startAngle + angleOffsets[i]).toDouble())).toFloat()

            canvas?.drawLine(centerX, centerY, endX, endY, createStrokePaint())
            canvas?.drawText(percent, textX, textY, createPaint(Color.WHITE))
            startAngle += angleOffsets[i]
        }

    }
}