package com.example.sierra.votestatistic.Fragments


import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sierra.votestatistic.Classes.Question
import com.example.sierra.votestatistic.db.DBHandler


class FragmentPagerAdapter (fragmentManager: FragmentManager, context: Context?) : FragmentStatePagerAdapter(fragmentManager) {
    val mcontext=context
    override fun getItem(position: Int): Fragment {

        val db = DBHandler(mcontext);
        val Quests : List<Question>  = db.getAllQuestion()
        for (quest in Quests) {
            if (db.hasSkipedTMP(quest.idInDB)) {
                db.addSkipedTMP(quest.idInDB)
                return VoteFragment2.newInstance(quest.id)
            }
        }
        return VoteFragment2.newInstance(0)
    }
    override fun getCount(): Int {
        return 99999
    }


}