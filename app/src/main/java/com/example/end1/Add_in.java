package com.example.end1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_in extends AppCompatActivity {
      // text for ADD
    EditText etFirstName,etLastName,etFavFood;
     // object type Button ADD , Back
    Button btnAdd , btnback ;
    //connected Database
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in);

        // Link Db
        myDB = new DatabaseHelper(this);

/*--------------------------------Textview type 3EditText { Firs tname , Last name , Food } ----------------------------*/
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

/*--------------------------------------------- Button ADD ------------------------------------------*/
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   // text for add get from Edit Text
                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();
             //  must enter the email Correctly
                if (!Patterns.EMAIL_ADDRESS.matcher(fFood).matches()){
                    Toast.makeText(getApplicationContext(),"not correct email",Toast.LENGTH_SHORT).show();
            // if all text is empty not Add and Show message Error
                }else
                    // if length the text not equals 0 , set text
                if(fName.length() != 0 && lName.length() != 0 && fFood.length() != 0){
                    AddData(fName,lName, fFood);
                    etFavFood.setText("");
                    etLastName.setText("");
                    etFirstName.setText("");
                }else{
                    // you must enter text
                    Toast.makeText(Add_in.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }
        });

     /*-------------------------------------------------- Buttoun ADD ------------------------------------*/
        btnback = (Button) findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_in.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
/*------------------------ Connected with db.addData------------------*/
    public void AddData(String firstName,String lastName, String favFood ){
        boolean insertData = myDB.addData(firstName,lastName,favFood);
        if(insertData==true){
            Toast.makeText(Add_in.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(Add_in.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }
}
