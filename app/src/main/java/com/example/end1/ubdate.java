package com.example.end1;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ubdate extends AppCompatActivity {
  // create variable link db
    DatabaseHelper mydb;
    // variable list link class user
    ArrayList<User> userList;
    // list
    ListView listView;
    // variable connected class User
    User user;
    // get data from the class transfer next class because editor
    SharedPreferences.Editor editor;
    // share data
    SharedPreferences pref;
    // Button for bake first bage
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_in);

/*---------------------------------------     Button Back      ------------------------------------*/
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);} });
  /*-------------------------------------------------------------------------------------------------*/
    // link DB with this class
        mydb = new DatabaseHelper(this);
                   // get data
        pref = PreferenceManager.getDefaultSharedPreferences(this);//*
        userList = new ArrayList<>();
        final Cursor data = mydb.getListContents();
        final int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ubdate.this," Database is Empty ?(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                user = new User(data.getString(0),data.getString(1),data.getString(2),data.getString(3));
                userList.add(i,user);
              System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
               System.out.println(userList.get(i).getFirstName());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
             listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
/*-------------------------------      get all data transfer ntext class       -------------------*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         // one click for show data
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(ubdate.this);
                       // save the position
                    final int item = position;
                         // get the item (id)
                    String dId=userList.get(item).getID();
                    // get the item (first name )
                    String dName=userList.get(item).getFirstName();
                    // get the item (last name )
                    String dfood=userList.get(item).getLastName();
                    // get the item (email)
                    String dEmail=userList.get(item).getEmail();
                    // print data
                    Toast.makeText(getApplicationContext(), dId+" :  "+dName+" :  "+dfood +" : "+dEmail, Toast.LENGTH_LONG).show();
                      // check
                    Log.v("LLL", " Check " +item);
                       // share data for next class
                    editor=pref.edit();
                    editor.putString("name_id",dId);
                    editor.putString("user_name",dName);
                    editor.putString("user_contact",dfood);
                    editor.putString("user_email",dEmail);
                    editor.commit();

                    Toast.makeText(ubdate.this,pref.getString("user_name",null), Toast.LENGTH_SHORT).show();//*
                    Intent intent=new Intent(ubdate.this,ubtadeshow.class);
                    startActivity(intent);
                }
            });
        }
    }


}
