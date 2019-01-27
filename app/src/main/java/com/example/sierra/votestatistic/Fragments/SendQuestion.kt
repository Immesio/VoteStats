package com.example.sierra.votestatistic.Fragments

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.*


import com.example.sierra.votestatistic.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_send_question.*
import android.widget.ArrayAdapter
import android.widget.Toast
import android.view.MenuInflater
import java.util.zip.Inflater


class SendQuestion : Fragment() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("ReceivedQuestion")
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_send_question, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.infosendquestion, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val builder = AlertDialog.Builder(context)
        builder.setView(layoutInflater.inflate(R.layout.layout_dialog_info,null))
        val dialog = builder.create()
        return if (id == R.id.info_btn) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.show()
            true
        } else super.onOptionsItemSelected(item)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.setTitle("Создать опрос")
        val ch = arrayOf("Слабо / Не слабо", "1 vs. 1", "Важные статистические")
        val adapter = ArrayAdapter<String>(context,R.layout.dropdownlist,R.id.ElementDropDown, ch)
        SendSpinner.setAdapter(adapter)

        SendSpinner.setSelection(0);

        QuestionChoice1.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                CheckEditandShow()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        QuestionChoice2.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                CheckEditandShow()
                }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        QuestionChoice3.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                CheckEditandShow()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        QuestionChoice4.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                CheckEditandShow()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        QuestionChoice5.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                CheckEditandShow()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        SendQuestion.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                CheckAlledits()
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
        fun newInstance() =
                SendQuestion().apply {
                    arguments = Bundle().apply {}
                }
    }
    fun CheckAlledits() {
        if(QuestionSendView.text.length<10 ) {
            QuestionSendView.setError("Недопустимая длинна вопроса (меньше 10 символов)")
            QuestionSendView.requestFocus()
        }
        else {
            if (CountEdit() < 2) {
                TextError.text = "Необходимо минимум 2 варианта ответа."
                flameerror.visibility = View.VISIBLE
                val handler = Handler()
                handler.postDelayed(Runnable {
                    flameerror.visibility = View.GONE

                }, 1500)
            } else {
                if (isOnline()) {
                    val ft = fragmentManager!!.beginTransaction()
                    val prev = fragmentManager!!.findFragmentByTag("dialog")
                    if (prev != null) {
                        ft.remove(prev)
                    }
                    ft.addToBackStack(null)
                    val dialogFragment = AlertDialogFragment()
                    dialogFragment.show(ft, "dialog")

                    val key = myRef.push().key
                    myRef.child(key).child("ID").setValue(key);
                    myRef.child(key).child("Theme").setValue(SendSpinner.selectedItemId.toString());
                    myRef.child(key).child("Question").setValue(QuestionSendView.text.toString());
                    myRef.child(key).child("CountChoice").setValue(CountEdit().toString());
                    var a = 1;
                    if (QuestionChoice2.text.length > 0) {
                        myRef.child(key).child(a.toString()).setValue(QuestionChoice2.text.toString())
                        a++;
                    }
                    if (QuestionChoice3.text.length > 0) {
                        myRef.child(key).child(a.toString()).setValue(QuestionChoice3.text.toString())
                        a++;
                    }
                    if (QuestionChoice4.text.length > 0) {
                        myRef.child(key).child(a.toString()).setValue(QuestionChoice4.text.toString())
                        a++;
                    }
                    if (QuestionChoice5.text.length > 0) {
                        myRef.child(key).child(a.toString()).setValue(QuestionChoice5.text.toString())
                        a++;
                    }
                    if (QuestionChoice1.text.length > 0) {
                        myRef.child(key).child(a.toString()).setValue(QuestionChoice1.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful) {
                                dialogFragment.changeimages()
                                clearedits();
                                val handler = Handler()
                                handler.postDelayed(Runnable {
                                    dialogFragment.dismiss()
                                }, 1000)
                            }
                            else {
                                dialogFragment.dismiss()
                                Toast.makeText(context, "Ошибка:" + it.exception.toString(), Toast.LENGTH_SHORT).show()
                            };
                        }
                    }
                } else {
                    TextError.text = "Отсутствует интернет соединение."
                    flameerror.visibility = View.VISIBLE
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        flameerror.visibility = View.GONE

                    }, 1500)
                }
            }
        }
    }

    private fun clearedits() {
        QuestionChoice1.setText("")
        QuestionChoice2.setText("")
        QuestionChoice3.setText("")
        QuestionChoice4.setText("")
        QuestionChoice5.setText("")
        QuestionSendView.setText("")
        SendSpinner.setSelection(0);
        CheckEditandShow();
    }

    fun CheckEditandShow()
    {
        var countgoodedit=0;
        var countvisible=0;
        if(QuestionChoice1.text.length>0)
            countgoodedit++;
        if(QuestionChoice2.text.length>0)
            countgoodedit++;
        if(QuestionChoice3.text.length>0)
            countgoodedit++;
        if(QuestionChoice4.text.length>0)
            countgoodedit++;
        if(QuestionChoice5.text.length>0)
            countgoodedit++;

        if(QuestionChoice1.visibility==View.VISIBLE)
            countvisible++;
        if(QuestionChoice2.visibility==View.VISIBLE)
            countvisible++;
        if(QuestionChoice3.visibility==View.VISIBLE)
            countvisible++;
        if(QuestionChoice4.visibility==View.VISIBLE)
            countvisible++;
        if(QuestionChoice5.visibility==View.VISIBLE)
            countvisible++;
        if(countvisible==2 && countgoodedit<2)
            return;

        if(countgoodedit-countvisible==0)
        {
            if(QuestionChoice1.visibility==View.GONE ) {
                QuestionChoice1.visibility = View.VISIBLE
                return;
            }
            if(QuestionChoice2.visibility==View.GONE) {
                QuestionChoice2.visibility = View.VISIBLE
                return;
            }
            if(QuestionChoice3.visibility==View.GONE ) {
                QuestionChoice3.visibility = View.VISIBLE
                return;
            }
            if(QuestionChoice4.visibility==View.GONE ) {
                QuestionChoice4.visibility = View.VISIBLE
                return;
            }
            if(QuestionChoice5.visibility==View.GONE ) {
                QuestionChoice5.visibility = View.VISIBLE
                return;
            }
        }
        if(countvisible-countgoodedit==2)
        {
            if(QuestionChoice5.visibility==View.VISIBLE && QuestionChoice5.text.length==0) {
                QuestionChoice5.visibility = View.GONE
                return;
            }
            if(QuestionChoice4.visibility==View.VISIBLE && QuestionChoice4.text.length==0) {
                QuestionChoice4.visibility = View.GONE
                return;
            }
            if(QuestionChoice3.visibility==View.VISIBLE && QuestionChoice3.text.length==0) {
                QuestionChoice3.visibility = View.GONE
                return;
            }
            if(QuestionChoice2.visibility==View.VISIBLE && QuestionChoice2.text.length==0) {
                QuestionChoice2.visibility = View.GONE
                return;
            }
            if(QuestionChoice1.visibility==View.VISIBLE && QuestionChoice1.text.length==0) {
                QuestionChoice1.visibility = View.GONE
                return;
            }
        }
    }

    fun isOnline(): Boolean {
        val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
    fun CountEdit(): Int {
      var count =0;
        if(QuestionChoice1.text.length>0)
            count++;
        if(QuestionChoice2.text.length>0)
            count++;
        if(QuestionChoice3.text.length>0)
            count++;
        if(QuestionChoice4.text.length>0)
            count++;
        if(QuestionChoice5.text.length>0)
            count++;
        return count;
    }
}
