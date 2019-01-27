package com.example.sierra.votestatistic.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import com.example.sierra.votestatistic.Classes.Question
import com.example.sierra.votestatistic.R
import com.example.sierra.votestatistic.db.DBHandler
import com.example.sierra.votestatistic.hz.FoldingCellListAdapter
import com.google.firebase.database.FirebaseDatabase
import com.ramotion.foldingcell.FoldingCell

import kotlinx.android.synthetic.main.fragment_vote.*
import java.util.ArrayList

class VoteFragment : Fragment() {



    var IdInDB=""
    var plsdontClickmore=true
    var arrayresult =Array<Int>(5){i->0}
    lateinit var db: DBHandler
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.setTitle("Опросы")
        db = DBHandler(context);
/*
        val Quests : List<Question>  = db.getAllQuestion()
        for (quest in Quests) {
            if(db.hasSkiped(quest.idInDB)) {
                QuestionTextView.text = quest.question
                IdInDB=quest.idInDB
                clearfragment();
                if(!quest.first.equals(""))
                {
                    choice1.setText(quest.first)
                    choice_layout1.visibility=View.VISIBLE
                    arrayresult[0]=quest.resultfirst
                }
                if(!quest.second.equals(""))
                {
                    choice2.setText(quest.second)
                    choice_layout2.visibility=View.VISIBLE
                    arrayresult[1]=quest.resultsecond
                }
                if(!quest.third.equals(""))
                {
                    choice3.setText(quest.third)
                    choice_layout3.visibility=View.VISIBLE
                    arrayresult[2]=quest.resultthird
                }
                if(!quest.fourth.equals(""))
                {
                    choice4.setText(quest.fourth)
                    choice_layout4.visibility=View.VISIBLE
                    arrayresult[3]=quest.resultfourth
                }
                if(!quest.fifth.equals(""))
                {
                    choice5.setText(quest.fifth)
                    choice_layout5.visibility=View.VISIBLE
                    arrayresult[4]=quest.resultfifth
                }
            }
        }
        choice_layout1.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(1)
            }
        })
        choice_layout2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(2)
            }
        })
        choice_layout3.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                    showresult(3)
            }
        })
        choice_layout4.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                showresult(4)
            }
        })
        choice_layout5.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                if(plsdontClickmore)
                showresult(5)
            }
        })
*/
        val Quests : List<Question>  = db.getAllQuestion()
        val items = ArrayList<Question>()
        for (quest in Quests) {
            if(db.hasSkiped(quest.idInDB)) {
                items.add(quest)
            }
        }

        val adapter = FoldingCellListAdapter(context, items)
        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(View.OnClickListener { Toast.makeText(context, "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show() })
        // set elements to adapter
        mainListView.setAdapter(adapter)

        // set on click event listener to list view
        mainListView.setOnItemClickListener(AdapterView.OnItemClickListener
        { adapterView, view, pos, l ->
            // toggle clicked cell state
            (view as FoldingCell).toggle(false)
            // register in adapter that state for selected cell is toggled
            adapter.registerToggle(pos)
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
        fun newInstance() = VoteFragment().apply {
                    arguments = Bundle().apply {}
                }
    }
    fun showresult(id: Int)
    {
        /*val animation1 = ObjectAnimator.ofInt(progress1, "progress",percent(arrayresult[0]))
        animation1.duration = 1500
        animation1.interpolator = DecelerateInterpolator()
        animation1.start()

        val animation2 = ObjectAnimator.ofInt(progress2, "progress", percent(arrayresult[1]))
        animation2.duration = 1500
        animation2.interpolator = DecelerateInterpolator()
        animation2.start()

        val animation3 = ObjectAnimator.ofInt(progress3, "progress", percent(arrayresult[2]))
        animation3.duration = 1500
        animation3.interpolator = DecelerateInterpolator()
        animation3.start()

        val animation4 = ObjectAnimator.ofInt(progress4, "progress", percent(arrayresult[3]))
        animation4.duration = 1500
        animation4.interpolator = DecelerateInterpolator()
        animation4.start()

        val animation5 = ObjectAnimator.ofInt(progress5, "progress", percent(arrayresult[4]))
        animation5.duration = 1500
        animation5.interpolator = DecelerateInterpolator()
        animation5.start()
        DBsendVoice(id);
        plsdontClickmore=false*/
    }

    fun percent(result: Int): Int
    {
        var SummResult=0
        for(i in 0..4)
            SummResult=SummResult+ arrayresult[i]

        return ((result.toDouble() / SummResult.toDouble())*100).toInt();
    }
   /* fun clearfragment()
    {
        for(i in 0..4)
            arrayresult[i]=0

        choice_layout1.visibility=View.GONE
        choice_layout2.visibility=View.GONE
        choice_layout3.visibility=View.GONE
        choice_layout4.visibility=View.GONE
        choice_layout5.visibility=View.GONE
    }*/
    fun DBsendVoice(id: Int)
    {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Question").child("1").child(IdInDB).child("Result").child(id.toString())
        val key = myRef.push().key
            myRef.child(key).setValue(key)
        db.addSkipedVoting(IdInDB)
    }
}
