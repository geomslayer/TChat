package com.geomslayer.tchat

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Listener
                ?: throw RuntimeException("$context must implement ${Listener::class.java.name}")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.loginButton.setOnClickListener {
            val username = view.usernameEditText.text.toString()
            listener?.onLogin(username)
        }
        return view
    }

    interface Listener {
        fun onLogin(username: String)
    }

}