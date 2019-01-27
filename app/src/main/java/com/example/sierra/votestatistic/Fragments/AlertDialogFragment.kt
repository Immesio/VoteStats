package com.example.sierra.votestatistic.Fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sierra.votestatistic.R
import kotlinx.android.synthetic.main.fragment_alert_dialog.*

class AlertDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alert_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val frameAnimation = VotingImages.background as AnimationDrawable
        frameAnimation.start()
    }

    fun changeimages ()
    {
        VotingImages.visibility=View.INVISIBLE
        VotingImages2.visibility=View.VISIBLE
    }
}
