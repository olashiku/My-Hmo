package com.qucoon.myhmo.views.UitilityView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.qucoon.myhmo.R


class customview_dashboard(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.customviewdashboard2, this)

        val imageView: ImageView = findViewById(R.id.imageicon)
        val textView: TextView = findViewById(R.id.title)
        val textView2: TextView = findViewById(R.id.message)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.itemview)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.itemview_image))
        textView.text = attributes.getString(R.styleable.itemview_text)
        textView2.text = attributes.getString(R.styleable.itemview_text2)
        attributes.recycle()

    }


    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.setColor(Color.RED)
        paint.setStrokeWidth(1.5f)
        paint.setStyle(Paint.Style.STROKE)
        canvas!!.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
    }
}