package com.example.end1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import java.util.ArrayList;

public class Search_PHP extends AppCompatActivity {
      // Create Array from User
    ArrayList<User> userList;
     // Create list
    ListView listView;
     // Variable link class User
       User user;
       // Connected With Db class phpConn
    phpConn phpC = new phpConn();
    // variable Type String (final ) Connected out File
    final String fetch = "http://172.20.10.3/sqli/fetch2json.php";
    JSONArray js_array;
    // Text for Search
    String value2search1 = null;
    TextView text1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__php);
        // the text link search , get text
        text1 = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        value2search1 = intent.getStringExtra("message");
        Log.v("Value  ",value2search1);
         // new Array
        userList = new ArrayList<>();
        try {
            phpC.urlCon(fetch);
                 //getting an array back
            js_array = new JSONArray(phpC.getAnswer());
            // reading the JSON array line by line
            for (int i = 0; i < js_array.length(); i++) {
                String value = js_array.getString(i);

                    String value1 = value.substring(1, value.indexOf(","));
                    value1 = value1.replace("\"", "");
                    Log.v("Abdalaziz value1 ", value1);


                    String remainder = value.substring(value.indexOf(",") + 2);
                    String value2 = remainder.substring(0, remainder.indexOf(","));
                    value2 = value2.replace("\"", "");
                    Log.v("Abdalaziz value2 ", value2);

                    String remainder1 = remainder.substring(remainder.indexOf(",") + 2);
                    String value3 = remainder1.substring(0, remainder1.indexOf(","));
                    value3 = value3.replace("\"", "");
                    Log.v("Abdalaziz value3 ", value3);

                    String remainder2 = remainder1.substring(remainder1.indexOf(",") + 1, remainder1.length() - 1);
                    String value4 = remainder2.substring(0);
                    value4 = value4.replace("\"", "");
                    Log.v("Abdalaziz value4 ", value4);
                             // if the value equals text value2search1
                   if (    value1.contains(value2search1)||
                           remainder.contains(value2search1)||
                           remainder1.contains(value2search1) ||
                           remainder2.contains(value2search1)) {
                    user = new User(value1, value2, value3, value4);}
                    userList.add(i, user);
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());}

        ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
