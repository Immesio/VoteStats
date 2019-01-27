package com.example.sierra.votestatistic.hz;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.sierra.votestatistic.Classes.Question;
import com.example.sierra.votestatistic.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Question> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    ViewHolder viewHolder = new ViewHolder();

    public FoldingCellListAdapter(Context context, List<Question> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Question item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        if (cell == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder

            viewHolder.question = cell.findViewById(R.id.SendQuestionText);
            viewHolder.question2 = cell.findViewById(R.id.SendQuestionText2);
            viewHolder.var1 = cell.findViewById(R.id.choice1);
            viewHolder.var2 = cell.findViewById(R.id.choice2);
            viewHolder.var3 = cell.findViewById(R.id.choice3);
            viewHolder.var4 = cell.findViewById(R.id.choice4);
            viewHolder.var5 = cell.findViewById(R.id.choice5);

            viewHolder.var1lay = cell.findViewById(R.id.choice_layout1);
            viewHolder.var2lay = cell.findViewById(R.id.choice_layout2);
            viewHolder.var3lay = cell.findViewById(R.id.choice_layout3);
            viewHolder.var4lay = cell.findViewById(R.id.choice_layout4);
            viewHolder.var5lay = cell.findViewById(R.id.choice_layout5);

            viewHolder.progress1 = cell.findViewById(R.id.progress1);
            viewHolder.progress2 = cell.findViewById(R.id.progress2);
            viewHolder.progress3 = cell.findViewById(R.id.progress3);
            viewHolder.progress4 = cell.findViewById(R.id.progress4);
            viewHolder.progress5 = cell.findViewById(R.id.progress5);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.question.setText(item.getQuestion());
        viewHolder.question2.setText(item.getQuestion());
        if(!item.getFirst().equals(""))
        {
            viewHolder.var1.setText(item.getFirst());
            viewHolder.var1lay.setVisibility(View.VISIBLE);
            //  arrayresult[0]=item.getResultfirst();
        }
        if(!item.getSecond().equals(""))
        {
            viewHolder.var2.setText(item.getSecond());
            viewHolder.var2lay.setVisibility(View.VISIBLE);
            //  arrayresult[0]=item.getResultfirst();
        }
        if(!item.getThird().equals(""))
        {
            viewHolder.var3.setText(item.getThird());
           viewHolder.var3lay.setVisibility(View.VISIBLE);
            //  arrayresult[0]=item.getResultfirst();
        }
        if(!item.getFourth().equals(""))
        {
            viewHolder.var4.setText(item.getFourth());
            viewHolder.var4lay.setVisibility(View.VISIBLE);
            //  arrayresult[0]=item.getResultfirst();
        }
        if(!item.getFifth().equals(""))
        {
            viewHolder.var5.setText(item.getFifth());
            viewHolder.var5lay.setVisibility(View.VISIBLE);
            //  arrayresult[0]=item.getResultfirst();
        }
        viewHolder.progress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showresult(1);
            }
        });


        // set custom btn handler for list item from that item
      /*  if (item.getRequestBtnClickListener() != null) {
            viewHolder.progress1.setOnClickListener(item.getRequestBtnClickListener());
            viewHolder.progress2.setOnClickListener(item.getRequestBtnClickListener());
            viewHolder.progress3.setOnClickListener(item.getRequestBtnClickListener());
            viewHolder.progress4.setOnClickListener(item.getRequestBtnClickListener());
            viewHolder.progress5.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
          //  viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }
*/
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position)) {
            registerFold(position);
        }
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView question;
        TextView question2;
        TextView var1;
        TextView var2;
        TextView var3;
        TextView var4;
        TextView var5;
        ConstraintLayout var1lay;
        ConstraintLayout var2lay;
        ConstraintLayout var3lay;
        ConstraintLayout var4lay;
        ConstraintLayout var5lay;
        ProgressBar progress1;
        ProgressBar progress2;
        ProgressBar progress3;
        ProgressBar progress4;
        ProgressBar progress5;

    }
    public void  showresult(int id)
    {
        Animator animation1 = ObjectAnimator.ofInt(viewHolder.progress1, "progress",100);
        animation1.setDuration(1500);
        animation1.setInterpolator(new DecelerateInterpolator());
        animation1.start();

        Animator animation2 = ObjectAnimator.ofInt(viewHolder.progress2, "progress",20);
        animation2.setDuration(1500);
        animation2.setInterpolator(new DecelerateInterpolator());
        animation2.start();

        Animator animation3 = ObjectAnimator.ofInt(viewHolder.progress3, "progress",30);
        animation3.setDuration(1500);
        animation3.setInterpolator(new DecelerateInterpolator());
        animation3.start();

        Animator animation4 = ObjectAnimator.ofInt(viewHolder.progress4, "progress",40);
        animation4.setDuration(1500);
        animation4.setInterpolator(new DecelerateInterpolator());
        animation4.start();

        Animator animation5 = ObjectAnimator.ofInt(viewHolder.progress5, "progress",10);
        animation5.setDuration(1500);
        animation5.setInterpolator(new DecelerateInterpolator());
        animation5.start();

      /*  DBsendVoice(id);
        plsdontClickmore=false*/
    }

  /*  public int  percent(int result)
    {
       int SummResult=0
        for(i in 0..4)
        SummResult=SummResult+ arrayresult[i]

       // return ((result.toDouble() / SummResult.toDouble())*100).toInt();
        return 30;
    }*/
}
