package com.example.end1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search extends AppCompatActivity {
    // textview type Edittext Search PHP and Local
    EditText textBox,textBox1;
    // 3 Button
    Button passButton,passButton1,btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

           // Text link button
        textBox = (EditText)findViewById(R.id.textBox);
           // Button Local
        passButton = (Button)findViewById(R.id.passButton);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create variable link Text and get data
                String str = textBox.getText().toString();
                // To Class Search in
                Intent intent = new Intent(getApplicationContext(), Search_in.class);
                intent.putExtra("message", str);
                startActivity(intent);
            }
        });
/*-----------------------------    Search PHP       -----------------------------*/
        // Text link button
        textBox1 = (EditText)findViewById(R.id.textBox1);
        passButton1 = (Button)findViewById(R.id.passButton1);
        passButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = textBox1.getText().toString();
                Intent i = new Intent(getApplicationContext(), Search_PHP.class);
                i.putExtra("message", str1);
                startActivity(i);
            }
        });
/* ----------------------------        Back          ----------------------------------*/
        btn_Back = (Button)findViewById(R.id.btn_Back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
