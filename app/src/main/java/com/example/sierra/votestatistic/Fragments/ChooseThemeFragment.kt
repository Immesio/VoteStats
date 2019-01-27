package com.example.sierra.votestatistic.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sierra.votestatistic.Classes.Question
import com.example.sierra.votestatistic.Interfaces.OnGetDataListener

import com.example.sierra.votestatistic.R
import com.example.sierra.votestatistic.db.DBHandler
import com.google.firebase.database.*


class ChooseThemeFragment : Fragment() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Question")
    lateinit var db: DBHandler
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db=DBHandler(context);
        db.deleteAllQuestion()
        readData(myRef.child("1"),object: OnGetDataListener {
            override fun onSuccess(dataSnapshot:DataSnapshot) {
                for (postSnapshot :DataSnapshot  in dataSnapshot.getChildren()) {
                    val ID= postSnapshot.child("Info").child("ID").getValue().toString()
                    if(db.hasSkiped(ID)) {
                        val Questiontxt = postSnapshot.child("Info").child("Question").getValue().toString()
                        val CountChoice = postSnapshot.child("Info").child("CountChoice").getValue().toString().toInt()

                        val arraychoice = Array<String>(5) { i -> "" }
                        for (i in 1..CountChoice)
                            arraychoice[i - 1] = postSnapshot.child("Info").child(i.toString()).getValue().toString()

                        val arrayresultchoice = Array<Int>(5) { i -> 0 }
                        for (i in 1..CountChoice)
                            arrayresultchoice[i - 1] = postSnapshot.child("Result").child(i.toString()).childrenCount.toString().toInt()

                        db.addQuestion(Question(ID, Questiontxt, CountChoice, arraychoice[0], arraychoice[1], arraychoice[2], arraychoice[3], arraychoice[4], arrayresultchoice[0], arrayresultchoice[1], arrayresultchoice[2], arrayresultchoice[3], arrayresultchoice[4]))
                    }
                }
                db.close()
            }
            override fun onFailure() {
               // комбек в люди
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

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                ChooseThemeFragment().apply {
                    arguments = Bundle().apply {}
                }
    }

    fun readData(Ref: DatabaseReference, listener: OnGetDataListener) {
        Ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listener.onSuccess(dataSnapshot)
            }
            override fun onCancelled(p0: DatabaseError?) {
                listener.onFailure()
            }
        })
    }
}
