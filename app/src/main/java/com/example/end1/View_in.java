package com.example.end1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class View_in extends AppCompatActivity {
    // connected with Database
    DatabaseHelper mydb;
    // Create variable save the Array from userlist
    ArrayList<User> userList;
    // Create variable for listview
    ListView listView;
    // Create variable link Class user
    User user;
    // Create Button for go to Back
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
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        /*-----------------------------------------------------------------------*/
        // link the DB
        mydb = new DatabaseHelper(this);
        // new array save list
        userList = new ArrayList<>();
        // link Function in db for get all contents
        Cursor data = mydb.getListContents();
        // Create variable type int save the Function getcount
        int numRows = data.getCount();
        // the variable == 0 means the Database empty
        if (numRows == 0) {
            Toast.makeText(View_in.this, " Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            // Create variable =0
            int i = 0;
            // the Wile link with data function from DB
            while (data.moveToNext()) {
                // Connected with Db and get data from db
                user = new User(data.getString(0), data.getString(1), data.getString(2), data.getString(3));
                // userlist link function add from DB Add user
                userList.add(i, user);
                // Print Column the get data
                System.out.println(data.getString(0) + " " + data.getString(1) + " " + data.getString(2) + " " + data.getString(3));
                System.out.println(userList.get(i).getFirstName());
                i++;
            }
            // ThreeColumn class inside 4 column Save list inside class because organized data in column
            ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

/*-------------------------- View the Column the selected -----------------*/

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             // one click for show data and Create variable Position because save click and Create variable type long
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(View_in.this);
                    // Create variable save the  position
                    final int item = position;
                    //  variable id get from userlist
                    String dId = userList.get(item).getID();
                    //  variable name get from userlist
                    String dName = userList.get(item).getFirstName();
                    //  variable food get from userlist
                    String dfood = userList.get(item).getLastName();
                    //  variable email get from userlist
                    String dEmail = userList.get(item).getEmail();
                    // print all variable from userlist
                    Toast.makeText(getApplicationContext(), dId + " :  " + dName + " :  " + dfood + " : " + dEmail, Toast.LENGTH_LONG).show();
                }
            });
/*-------------------------------------------------------------------------------------------------*/

                }


    }
}

