package com.example.sierra.votestatistic.Fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator

import com.example.sierra.votestatistic.Classes.Question

import com.example.sierra.votestatistic.R
import com.example.sierra.votestatistic.R.id.pager

import com.example.sierra.votestatistic.db.DBHandler
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.app_bar_navigate.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

import kotlinx.android.synthetic.main.fragment_vote_fragment2.*


private const val POSITION = "position"

class VoteFragment2 : Fragment() {

    private var position: Int =0
    var IdInDB=""
    var plsdontClickmore=true
    var arrayresult =Array<Int>(5){i->0}
    lateinit var db: DBHandler

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments!!.getInt(POSITION)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vote_fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DBHandler(context);
        val Quests : List<Question>  = db.getAllQuestion()
        for (quest in Quests) {
            if (quest.id==position) {
                QuestionTextView_voting2.text = quest.question
                IdInDB = quest.idInDB
                clearfragment();
                if (!quest.first.equals("")) {
                    choice1_voting2.setText(quest.first)
                    choice_layout1_voting2.visibility = View.VISIBLE
                    arrayresult[0] = quest.resultfirst
                }
                if (!quest.second.equals("")) {
                    choice2_voting2.setText(quest.second)
                    choice_layout2_voting2.visibility = View.VISIBLE
                    arrayresult[1] = quest.resultsecond
                }
                if (!quest.third.equals("")) {
                    choice3_voting2.setText(quest.third)
                    choice_layout3_voting2.visibility = View.VISIBLE
                    arrayresult[2] = quest.resultthird
                }
                if (!quest.fourth.equals("")) {
                    choice4_voting2.setText(quest.fourth)
                    choice_layout4_voting2.visibility = View.VISIBLE
                    arrayresult[3] = quest.resultfourth
                }
                if (!quest.fifth.equals("")) {
                    choice5_voting2.setText(quest.fifth)
                    choice_layout5_voting2.visibility = View.VISIBLE
                    arrayresult[4] = quest.resultfifth
                }
            }
        }

        choice_layout1_voting2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(1)
            }
        })
        choice_layout2_voting2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(2)
            }
        })
        choice_layout3_voting2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(3)
            }
        })
        choice_layout4_voting2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(4)
            }
        })
        choice_layout5_voting2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(5)
            }
        })

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(position : Int) = VoteFragment2().apply {
            arguments = Bundle().apply {
                putInt(POSITION, position)
            }
        }
    }
    fun showresult(id: Int)
    {
        val animation1 = ObjectAnimator.ofInt(progress1_voting2, "progress",percent(arrayresult[0]))
        animation1.duration = 1500
        animation1.interpolator = DecelerateInterpolator()
        animation1.start()

        val animation2 = ObjectAnimator.ofInt(progress2_voting2, "progress", percent(arrayresult[1]))
        animation2.duration = 1500
        animation2.interpolator = DecelerateInterpolator()
        animation2.start()

        val animation3 = ObjectAnimator.ofInt(progress3_voting2, "progress", percent(arrayresult[2]))
        animation3.duration = 1500
        animation3.interpolator = DecelerateInterpolator()
        animation3.start()

        val animation4 = ObjectAnimator.ofInt(progress4_voting2, "progress", percent(arrayresult[3]))
        animation4.duration = 1500
        animation4.interpolator = DecelerateInterpolator()
        animation4.start()

        val animation5 = ObjectAnimator.ofInt(progress5_voting2, "progress", percent(arrayresult[4]))
        animation5.duration = 1500
        animation5.interpolator = DecelerateInterpolator()
        animation5.start()
        DBsendVoice(id);
        plsdontClickmore=false
    }

    fun percent(result: Int): Int
    {
        var SummResult=0
        for(i in 0..4)
            SummResult=SummResult+ arrayresult[i]

        return ((result.toDouble() / SummResult.toDouble())*100).toInt();
    }
    fun clearfragment()
    {
        for(i in 0..4)
            arrayresult[i]=0

        choice_layout1_voting2.visibility=View.GONE
        choice_layout2_voting2.visibility=View.GONE
        choice_layout3_voting2.visibility=View.GONE
        choice_layout4_voting2.visibility=View.GONE
        choice_layout5_voting2.visibility=View.GONE
    }
    fun DBsendVoice(id: Int)
    {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Question").child("1").child(IdInDB).child("Result").child(id.toString())
        val key = myRef.push().key
        myRef.child(key).setValue(key)
        db.deleteQuestion(IdInDB)
        db.addSkipedVoting(IdInDB)
    }
}
