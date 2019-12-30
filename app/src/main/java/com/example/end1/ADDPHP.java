package com.example.end1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ADDPHP extends AppCompatActivity {
   // Edittext for 3 text
    EditText etFirstName,etLastName,etFavFood;
    // object type button (ADD , Back )
    Button btnAdd,btnBack;
    // Connected with Class phpConn (DB)
    phpConn phpCon = new phpConn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphp);

        // Definition of 3 Entries
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
/*-------------------------------------- ADD in PHP  ----------------------------------------*/

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            // Definition of 3 String and get the text.
            public void onClick(View v) {
                String Name = etFirstName.getText().toString();
                String Phone = etLastName.getText().toString();
                String Email = etFavFood.getText().toString();

                    // if the enter empty show message Error
                if(Name.length() != 0 && Name.length() != 0 && Phone.length() != 0 && Email.length() != 0){
                    // link with db ( name - phone - Email )
                    phpCon.urlCon("http://172.20.10.3/sqli/insert.php?field1-name="+Name+"&field2-name="+Phone+"&field3-name="+Email);
                   // Set the text for link
                    etFirstName.setText("");
                    etFavFood.setText("");
                    etLastName.setText("");
                    // Show Massage Done The ADD
                    Toast.makeText(ADDPHP.this,"Successfully ..",Toast.LENGTH_LONG).show();
                }else{
                    // Show Massage it have Error the Enter
                    Toast.makeText(ADDPHP.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();}}});

  /*----------------------------- Buton Back -------------------------------*/
        // Definition the button to back
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //go to from the class to Main class
                Intent intent = new Intent(ADDPHP.this,MainActivity.class);
                startActivity(intent);}});

    }
}
