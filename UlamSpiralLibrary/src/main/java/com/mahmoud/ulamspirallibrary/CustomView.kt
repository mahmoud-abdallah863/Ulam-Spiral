package com.mahmoud.ulamspirallibrary

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs

class CustomView (
    context: Context,
    attrs: AttributeSet
): View(context, attrs) {

    private val TAG = "CustomViewTag"

    private val str = ","
    private var strRect = Rect()

    private var xStartDrawingPosition = 0f
    private var yStartDrawingPosition = 0f

    private val textPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GREEN
        textSize = 14 * resources.displayMetrics.density
    }

    private val rectPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        xStartDrawingPosition = w / 2f
        yStartDrawingPosition = h / 2f

        val strIntRect = Rect()
        textPaint.getTextBounds(str, 0, str.length, strIntRect)

        strRect.set(
            (xStartDrawingPosition - strIntRect.width() / 2f).toInt(),
            (yStartDrawingPosition - strIntRect.height() / 2f).toInt(),
            (xStartDrawingPosition + strIntRect.width() / 2f).toInt(),
            (yStartDrawingPosition + strIntRect.height() / 2f + abs(textPaint.descent())).toInt()
        )
        textPaint.descent()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(strRect, rectPaint)
        canvas?.drawText(
            str,
            strRect.left.toFloat(),
            strRect.bottom - textPaint.descent(),
            textPaint
        )


        canvas?.drawText()
    }
}