package com.example.end1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button   btn_Add_PHP,
             btn_View_PHP,
             btn_Add_Loacl,
             btn_View_Local,
             btn_Delet_PHP,
             btn_Delet_in,
             passButton1,
             btn_ubdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*------------------------------ Button Searcg ------------------------*/
        passButton1 = (Button)findViewById(R.id.passButton1);
        passButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Search.class);
                startActivity(i);
            }
        });

/*--------------------------------------------------------------------------*/
        btn_ubdate = (Button)findViewById(R.id.btn_ubdate);
        btn_ubdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ubdate.class);
                startActivity(i);
            }
        });
/*-------------------------------------- Buttons Local --------------------------*/

        btn_Add_Loacl = (Button) findViewById(R.id.btn_Add_Local);
        btn_Add_Loacl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Add_in.class);
                startActivity(intent);
            }
        });

        btn_Delet_in = (Button) findViewById(R.id.btn_Delet_local);
        btn_Delet_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Delete_in.class);
                startActivity(intent);
            }
        });

        btn_View_Local = (Button) findViewById(R.id.btn_view_in);
        btn_View_Local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,View_in.class);
                startActivity(intent);
            }
        });
/*---------------------------------------------Buttoun PHP --------------------------------------------------------*/
        btn_Add_PHP = (Button) findViewById(R.id.btn_Add_PHP);
        btn_Add_PHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ADDPHP.class);
                startActivity(intent);
            }
        });

        btn_Delet_PHP = (Button) findViewById(R.id.btn_Delet_PHP);
        btn_Delet_PHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Delete_PHP.class);
                startActivity(intent);
            }
        });

        btn_View_PHP = (Button) findViewById(R.id.btn_View_PHP);
        btn_View_PHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,View_PHP.class);
                startActivity(intent);
            }
        });
}}
