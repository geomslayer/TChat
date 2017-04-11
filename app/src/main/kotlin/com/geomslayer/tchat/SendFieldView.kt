package com.geomslayer.tchat

import android.content.Context
import android.support.v7.widget.CardView
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.widget_send_field.view.*

class SendFieldView : CardView {

    lateinit var layout: View
    var clickListener: (String) -> Unit = {}

    var message: String
        get() = layout.textField.text.toString()
        set(value) = layout.textField.setText(value)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) :
            super(context, attrs, defStyle) {
        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet) {
        layout = LayoutInflater.from(context).inflate(R.layout.widget_send_field, this)

        layout.sendButton.isEnabled = false
        layout.textField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable) {
                layout.sendButton.apply {
                    if (text.trim().isEmpty()) {
                        setImageResource(R.drawable.ic_send_disabled)
                        isEnabled = false
                    } else {
                        setImageResource(R.drawable.ic_send)
                        isEnabled = true
                    }
                }
            }

            override fun beforeTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {}
        })

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SendFieldView)
        layout.textField.hint = typedArray.getText(R.styleable.SendFieldView_hint)
        typedArray.recycle()

        layout.sendButton.setOnClickListener {
            clickListener(layout.textField.text.toString().trim())
            layout.textField.text.clear()
        }
    }

    fun setOnMesageSendListener(listener: (String) -> Unit) {
        clickListener = listener
    }

}