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


class itemview(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.itemview, this)

        val imageView: ImageView = findViewById(R.id.image)
        val textView: TextView = findViewById(R.id.caption1)
        val textView2: TextView = findViewById(R.id.caption2)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.itemview)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.itemview_image))
        textView.text = attributes.getString(R.styleable.itemview_text)
        textView2.text = attributes.getString(R.styleable.itemview_text2)
        attributes.recycle()

    }



}