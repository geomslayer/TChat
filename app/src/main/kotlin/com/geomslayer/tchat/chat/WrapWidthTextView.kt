package com.geomslayer.tchat.chat

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.widget.TextView

class WrapWidthTextView : TextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (layout != null) {
            val width = Math.ceil(getMaxLineWidth(layout)).toInt() +
                    compoundPaddingLeft + compoundPaddingRight
            setMeasuredDimension(width, measuredHeight)
        }
    }

    private fun getMaxLineWidth(layout: Layout): Double {
        var maxWidth = 0F
        for (line in 0 until layout.lineCount) {
            if (layout.getLineWidth(line) > maxWidth) {
                maxWidth = layout.getLineWidth(line)
            }
        }
        return maxWidth.toDouble()
    }
}