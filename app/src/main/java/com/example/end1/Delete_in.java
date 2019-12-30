package com.example.end1;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_in extends AppCompatActivity {
     // create a variable link Database
    DatabaseHelper mydb;
    // create a variable link db Function ArrayList
    ArrayList<User> userList;
    // create a variable Connected listview
    ListView listView;
    // create a variable link Class user
    User user;
    // Object type Button for go to Back
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
         // link DB
        mydb = new DatabaseHelper(this);
        // Arraylist
        userList = new ArrayList<>();
        // link with db the Function getListContents
        Cursor data = mydb.getListContents();
        // Create variable type int and link getCount()
        int Rows = data.getCount();
        // if the numRows equals 0 the mean Database is Empty
        if(Rows == 0){
            Toast.makeText(Delete_in.this," Database is Empty ...  (.",Toast.LENGTH_LONG).show();
                }else{
            //  create variable "i" type int equals 0
            int i=0;
            // link with db Function moveRoNext and get the data from user
            while(data.moveToNext()){
                user = new User(data.getString(0),data.getString(1),data.getString(2),data.getString(3));
                userList.add(i,user);
                // print All data the get
                System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(userList.get(i).getFirstName());
                i++;}
         // call up class because arranging list view inside
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

/*------------------------------ one click for show data--------------------------*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   // create variable type int because save teh position
                    final int item = position;
                    new AlertDialog.Builder(Delete_in.this)
                            // the icon for delete
                            .setIcon(android.R.drawable.ic_menu_delete)
                            // shoe Massage title
                            .setTitle("Are you Want ?")
                            .setMessage("Do you want to Delete this item . ")
                            // set teh click button
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // create variable link user list and get id from
                                    String id=userList.get(item).getID();
                                    // link db and remove from the database
                                    mydb.deleteContactById(id);
                                    // update the bage
                                    Intent intent=new Intent(Delete_in.this,Delete_in.class);
                                    startActivity(intent);
                                    // Done the Delete
                                    Toast.makeText(getApplicationContext(), "Deleted . ", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();


                }
            });
        }
    }
}