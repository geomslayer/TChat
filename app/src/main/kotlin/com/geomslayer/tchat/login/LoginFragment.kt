package com.geomslayer.tchat.login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

class LoginFragment : Fragment() {

    companion object {
        val TAG = LoginFragment::class.java.simpleName
    }

    private var listener: LoginListener? = null

    var success: Boolean? = null
        get() {
            val res = field
            field = null
            return res
        }
        set(value) {
            if (listener != null) {
                listener?.onResult(value ?: false)
            } else {
                field = value
            }
        }

    var inProgress = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? LoginListener
                ?: throw RuntimeException("$context must implement LoginListener")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        success?.let { listener?.onResult(it) }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    interface LoginListener {
        fun onResult(success: Boolean)
    }

}