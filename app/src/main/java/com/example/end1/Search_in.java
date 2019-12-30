package com.example.end1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Search_in extends AppCompatActivity {

    DatabaseHelper mydb;
    ArrayList<User> userList;
    ListView listView;
    User user;

    String value2search = null;
    Button btn_back;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_in);

       /*------------------------------ Button Back ------------------------*/
       btn_back = (Button)findViewById(R.id.btn_back);
       btn_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), Search.class);
               startActivity(i);
           }
       });
       /*-----------------------------------------------------------------------*/
              // get text
         Intent intent = getIntent();
         value2search = intent.getStringExtra("message");
       Log.v("Value a Search  ",value2search);


           // link Db
        mydb = new DatabaseHelper(this);
        // Create new Array from userlist
        userList = new ArrayList<>();
             // connected with database Function( getListContents() )
        Cursor data = mydb.getListContents();
           // connected data with function
        int numRows = data.getCount();
        if (numRows == 0) {
            // if not data Show massage Database Empty
            Toast.makeText(Search_in.this, " Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                // if the Search the data contains the text ( value2search )
                if (data.getString(0).contains(value2search)|| data.getString(1).contains(value2search)|| data.getString(2).contains(value2search) || data.getString(3).contains(value2search) ){
                    user = new User(data.getString(0), data.getString(1), data.getString(2), data.getString(3));
                }
                    userList.add(i, user);
                    i++;}
               // save the data in ThreeColumn_ListAdapter adapter 3 Column
            ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

        }
    }
}


