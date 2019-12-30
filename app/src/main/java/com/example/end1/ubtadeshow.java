package com.example.end1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ubtadeshow extends Activity {
      // link db
    DatabaseHelper db;
       // share data
    SharedPreferences.Editor editor;
    // keep preferences
    SharedPreferences pref;
      // text for update
    String Name,id,contact,email;
       // get text
    EditText etId,etName,etContact,etemail;
      // click update
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubtadeshow);
           // link db
        db=new DatabaseHelper(this);
             // text the update
        etId=(EditText)findViewById(R.id.et_id);
        etName=(EditText)findViewById(R.id.et_name);
        etemail=(EditText)findViewById(R.id.et_id2);
        etContact=(EditText)findViewById(R.id.et_num);
           // object type Button
        btnUpdate=(Button) findViewById(R.id.btnUpdatepopup);
              // pref : keep preferences
        pref = PreferenceManager.getDefaultSharedPreferences(this);
          // get data
        id=     pref.getString("name_id",null);
        Name=   pref.getString("user_name",null);
        contact=pref.getString("user_contact",null);
        email=  pref.getString("user_email",null);
            // Set data
        etContact.setText(contact);
        etId.setText(id);
        etName.setText(Name);
        etemail.setText(email);

        Log.v("check ", " editor " +editor);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            // Connected with Function in Database get the data and update
                db.updateContact(new User(etId.getText().toString(),etName.getText().toString(),etContact.getText().toString(),etemail.getText().toString()));
                         // update the page and go ubdate class
                Intent intent =new Intent(ubtadeshow.this,ubdate.class);
                startActivity(intent);

            }
        });
    }
}


