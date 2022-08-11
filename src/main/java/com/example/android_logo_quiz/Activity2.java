package com.example.android_logo_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class Activity2 extends AppCompatActivity {
    Button viewer,deletedata;
    ListView lv;
    ArrayAdapter adapter;
    DatabaseHelper databaseHelper;
    EditText delete_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(Activity2.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        viewer=(Button)findViewById(R.id.viewer);
        lv=(ListView)findViewById(R.id.list_view);
        deletedata=(Button)findViewById(R.id.delete);
        delete_id = (EditText)findViewById(R.id.id_2);



        viewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<PlayerDetails> all=databaseHelper.getalldata();
                adapter = new ArrayAdapter<PlayerDetails>(Activity2.this, android.R.layout.simple_list_item_1,all);
                lv.setAdapter(adapter);
                //ArrayAdapter
                Toast.makeText(Activity2.this,"success ",Toast.LENGTH_SHORT).show();

            }
        });
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows = databaseHelper.deleteData(delete_id.getText().toString());
                if(deleterows>0)
                    Toast.makeText(getApplicationContext(),"deleted successfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"delete failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}