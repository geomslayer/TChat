package com.geomslayer.tchat

import android.os.Bundle
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    lateinit var fragmentView: ViewGroup

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_about, container, false) as ViewGroup
        animate(true)
        return fragmentView
    }

    private fun animate(visible: Boolean) {
        TransitionManager.beginDelayedTransition(fragmentView, Fade().setDuration(2000))
        fragmentView.aboutTextView.visibility = if (visible) View.VISIBLE else View.GONE
        fragmentView.aboutTextView.postDelayed({ animate(!visible) }, 2000)
    }

}