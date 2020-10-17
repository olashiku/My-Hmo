package com.qucoon.myhmo.views.UitilityView

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.qucoon.myhmo.R


class itemview2(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    lateinit var textView: TextView
    lateinit var textView2: TextView

    init {
        inflate(context, R.layout.itemview2, this)

        val imageView: ImageView = findViewById(R.id.image)
         textView = findViewById(R.id.caption1)
         textView2 = findViewById(R.id.caption2)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.itemview)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.itemview_image))
        textView.text = attributes.getString(R.styleable.itemview_text)
        textView2.text = attributes.getString(R.styleable.itemview_text2)
        attributes.recycle()


    }

    fun setTextView1(text: String?) {
        textView.setText(text)
    }

    fun setTextView2(text: String?) {
        textView2.setText(text)
    }



}