package com.example.kannu.contacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit extends Activity {

    EditText e1,e2,e3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText5);
        e3=(EditText)findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button6);
        ArrayList alist= getIntent().getStringArrayListExtra("values");
        e1.setText((String) alist.get(1));
        e2.setText((String) alist.get(2));
        e3.setText((String) alist.get(3));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=e1.getText().toString();
                String c=e2.getText().toString();
                String e=e3.getText().toString();
                DBHelper obj=new DBHelper(getApplicationContext());
                int i=obj.updateRecord(n,c,e);
                Toast.makeText(getApplication(),"Contact Update",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(),MainActivity.class));
            }
        });
    }




}
