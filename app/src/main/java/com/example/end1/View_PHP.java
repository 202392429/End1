package com.example.end1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import java.util.ArrayList;

public class View_PHP extends AppCompatActivity {
     // Array link userlist Class User
    ArrayList<User> userList;
    // listview
    ListView listView;
    // link Class user
    User user;
    // Create link db class phpConn
    phpConn phpC = new phpConn();
    // link file php
    final String fetch = "http://172.20.10.3/sqli/fetch2json.php";
    // link import
    JSONArray js_array;
    // button back
    Button btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__php);

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
           // new Array saved userlist
        userList = new ArrayList<>();
        try {
            phpC.urlCon(fetch);
                  //getting an array back
            js_array = new JSONArray(phpC.getAnswer());
            // reading the JSON array line by line
            for (int i = 0; i < js_array.length(); i++) {

                String value=js_array.getString(i);

                String value1 = value.substring(1, value.indexOf(","));
                value1=value1.replace("\"" ,"");
                Log.v("Abdalaziz value1 ",value1.toString());
                String remainder = value.substring(value.indexOf(",")+2);

                String value2 = remainder.substring( 0, remainder.indexOf(","));
                value2=value2.replace("\"" ,"");
                Log.v("Abdalaziz value2 ",value2.toString());
                String remainder1 = remainder.substring(remainder.indexOf(",")+2);

                String value3 = remainder1.substring( 0, remainder1.indexOf(","));
                value3=value3.replace("\"" ,"");
                Log.v("Abdalaziz value3 ",value3.toString());
                String remainder2 = remainder1.substring(remainder1.indexOf(",")+1, remainder1.length()-1);

                String value4 = remainder2;
                value4=value4.replace("\"" ,"");
                Log.v("Abdalaziz value4 ",value4);

                user = new User(value1,value2,value3,value4);
                userList.add(i,user);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());}

        ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

/* ------------------           one click for show data    -------------------------*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long l) {
                try {
                    int pos=position;
                    String curValue =js_array.getString(position);
                    Log.v("Array with :  ",curValue);

                    String value=js_array.getString(position);

                    String value1 = value.substring(1, value.indexOf(","));
                    value1=value1.replace("\"" ,"");
                    Log.v("Abdalaziz value1 ",value1);

                    String remainder = value.substring(value.indexOf(",")+2);

                    String value2 = remainder.substring( 0, remainder.indexOf(","));
                    value2=value2.replace("\"" ,"");
                    Log.v("george value2 ",value2);

                    String remainder1 = remainder.substring(remainder.indexOf(",")+2);

                    String value3 = remainder1.substring( 0, remainder1.indexOf(","));
                    value3=value3.replace("\"" ,"");
                    Log.v("george value3 ",value3.toString());

                    String remainder2 = remainder1.substring(remainder1.indexOf(",")+1, remainder1.length()-1);

                    String value4 = remainder2;
                    value4=value4.replace("\"" ,"");
                    Log.v("george value4 ",value4);
                       // print the value
                    Toast.makeText(getApplicationContext(), value1+". "+value2+" "+value3+" "+value4, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });
    }
}
