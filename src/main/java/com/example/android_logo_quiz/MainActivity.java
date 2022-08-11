package com.example.android_logo_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button add,view;
    EditText name;
    EditText age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=(Button)findViewById(R.id.add);
        view=(Button)findViewById(R.id.view);
        name=(EditText)findViewById(R.id.p1);
        age=(EditText)findViewById(R.id.age);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerDetails playerDetails;
                try {
                    //create new customer reference object
                    playerDetails = new PlayerDetails(name.getText().toString(), 1,Integer.parseInt(age.getText().toString()));
                    Toast.makeText(MainActivity.this, playerDetails.toString(), Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "error in creating data", Toast.LENGTH_SHORT).show();
                    playerDetails= new PlayerDetails("error",1,0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                //databaseHelper.addrecord(playerDetails);

                boolean b = databaseHelper.addRecord( playerDetails);
                Toast.makeText(MainActivity.this,"SUCCESS= "+ b, Toast.LENGTH_SHORT).show();
                //success=true
                //String value = name.getText().toString();
                Intent intent = new Intent(MainActivity.this,Activity1.class);
                //intent.putExtra("key",value);
                startActivity(intent);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent1);

            }
        });

    }
}

