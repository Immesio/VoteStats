package com.example.sierra.votestatistic.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sierra.votestatistic.Classes.SwipeDirection

import com.example.sierra.votestatistic.R
import com.example.sierra.votestatistic.Transformation.DepthTransformation
import com.example.sierra.votestatistic.Transformation.Hinge
import com.example.sierra.votestatistic.db.DBHandler
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPager_fragment : Fragment() {


    private var listener: OnFragmentInteractionListener? = null
    private lateinit var pagerAdapter: FragmentPagerAdapter
    private  lateinit  var viewPager: CustomViewPager
    lateinit var db: DBHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hingeTransformation = DepthTransformation()
        db=DBHandler(context);
        viewPager=pager
        viewPager.setAllowedSwipeDirection(SwipeDirection.right)
        viewPager.setPageTransformer(true, hingeTransformation)
        pagerAdapter = FragmentPagerAdapter(childFragmentManager,context)
        viewPager.adapter = pagerAdapter
        db.deleteAllSkipedTMP()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPager_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                ViewPager_fragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
