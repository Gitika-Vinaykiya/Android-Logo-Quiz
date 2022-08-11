package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android_logo_quiz.Activity1;


import java.util.List;

import Common.Common;

public class GridViewSuggestadapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private Activity1 activity1;


    public GridViewSuggestadapter(List<String> suggestSource, Context context, Activity1 activity1) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.activity1 = activity1;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null){
            if (suggestSource.get(position).equals("null")){
                button= new Button(context);
                button.setLayoutParams(new ViewGroup.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else{
                button= new Button(context);
                button.setLayoutParams(new ViewGroup.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if correct answer contains character user selected
                        if(String.valueOf(activity1.answer).contains(suggestSource.get(position))){
                            char compare = suggestSource.get(position).charAt(0);
                            //getchar
                            for (int i =0;i<activity1.answer.length;i++)
                            {
                                if(compare == activity1.answer[i])

                                    Common.user_submit_answer[i]=compare;
                            }
                            //update UI
                            GridViewAnsweradapter answeradapter= new GridViewAnsweradapter(Common.user_submit_answer,context);
                            activity1.gridViewAnswer.setAdapter(answeradapter);


                            answeradapter.notifyDataSetChanged();
                            //remove from suggest source
                            activity1.suggestSource.set(position,"null");
                            activity1.suggestadapter=new GridViewSuggestadapter(activity1.suggestSource,context,activity1);
                            activity1.gridViewSuggest.setAdapter(activity1.suggestadapter);

                            activity1.suggestadapter.notifyDataSetChanged();
                        }
                        else
                            activity1.suggestSource.set(position,"null");
                        activity1.suggestadapter=new GridViewSuggestadapter(activity1.suggestSource,context,activity1);
                        activity1.gridViewSuggest.setAdapter(activity1.suggestadapter);
                        activity1.suggestadapter.notifyDataSetChanged();

                    }
                });
            }
        }
        else{
            button = (Button)convertView;

        }
        return button;
    }
}
