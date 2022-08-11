package com.example.android_logo_quiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Common.Common;
import adapter.GridViewAnsweradapter;
import adapter.GridViewSuggestadapter;

public class Activity1 extends AppCompatActivity {
    public List<String> suggestSource = new ArrayList<>();
    public GridViewAnsweradapter answeradapter;
    public GridViewSuggestadapter suggestadapter;
    public Button btnSubmit;
    public GridView gridViewAnswer,gridViewSuggest;
    public Button view,back;


    EditText name;
    EditText age;
    ListView lv;
    int playerpoints=0;
    TextView score;
    ArrayAdapter adapter;


    public ImageView imgViewQuestion;
    int[] image_list = {
            R.drawable.audi,
            R.drawable.benz,
            R.drawable.kia,
            R.drawable.messenger,
            R.drawable.photoshop,
            R.drawable.snapchat,
            R.drawable.spotify,
            R.drawable.target,
            R.drawable.whatsapp,
           // R.drawable.wolks
    };

    public char[] answer;
    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        back = (Button)findViewById(R.id.back);
        view =(Button)findViewById(R.id.b2);
        score=(TextView)findViewById(R.id.p1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this,Activity2.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this,MainActivity.class);
                startActivity(intent);

            }
        });

        initView();


    }

    private void initView() {
        gridViewAnswer =(GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest=(GridView)findViewById(R.id.gridViewSuggest);
        imgViewQuestion=(ImageView)findViewById(R.id.imgLogo);

        //add setup list here
        setupList();
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = "";
                for(int i=0;i< Common.user_submit_answer.length;i++){
                    result+=String.valueOf(Common.user_submit_answer[i]);
                }
                if(result.equals(correct_answer)) {
                    Toast.makeText(getApplicationContext(), "finish!!This is " + result, Toast.LENGTH_SHORT).show();
                    //reset
                    playerpoints++;
                    score.setText("SCORE : " + playerpoints);
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];
                    //setadapter
                    GridViewAnsweradapter answeradapter = new GridViewAnsweradapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answeradapter);
                    answeradapter.notifyDataSetChanged();
                    GridViewSuggestadapter suggestadapter=new GridViewSuggestadapter(suggestSource,getApplicationContext(),Activity1.this);
                    gridViewSuggest.setAdapter(suggestadapter);
                    suggestadapter.notifyDataSetChanged();
                    setupList();
                }
                else{
                    Toast.makeText(Activity1.this,"Incorrect!!",Toast.LENGTH_SHORT).show();
                    playerpoints--;
                    score.setText("SCORE : " + playerpoints);
                }

            }
        });
    }

    private void setupList() {
        //random logo
        Random random=new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);
        correct_answer= getResources().getResourceName(imageSelected);
        correct_answer= correct_answer.substring(correct_answer.lastIndexOf("/")+1);
        answer=correct_answer.toCharArray();
        Common.user_submit_answer=new char[answer.length];
        //add answer charater to list
        suggestSource.clear();
        for(char item:answer){
            //add logo name to list
            suggestSource.add(String.valueOf(item));
        }
        //random add some character to list
        for(int i=answer.length;i<answer.length*2;i++){
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);
        }
        //sort random
        Collections.shuffle(suggestSource);
        //set for grid view
        answeradapter= new GridViewAnsweradapter(setupNullList(),this);
        suggestadapter=new GridViewSuggestadapter(suggestSource,this,this);

        answeradapter.notifyDataSetChanged();
        suggestadapter.notifyDataSetChanged();
        gridViewSuggest.setAdapter(suggestadapter);
        gridViewAnswer.setAdapter(answeradapter);
    }

    private char[] setupNullList() {
        char result[]=new char[answer.length];
        for(int i=0;i<answer.length;i++){
            result[i]=' ';
        }
        return result;

    }
}