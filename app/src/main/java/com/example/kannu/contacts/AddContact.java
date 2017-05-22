package com.example.kannu.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity
{
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
    }
    public void  onPress(View view)
    {
        String u=e1.getText().toString();
        String p=e2.getText().toString();
        String e=e3.getText().toString();
        DBHelper obj=new DBHelper(getApplicationContext());
        long num=obj.save(u,p,e);
        if(num==-1)
        {
            Toast.makeText(this,"Record Not Inserted",Toast.LENGTH_SHORT).show();
        }
        if(num!=-1)
        {
            Toast.makeText(this,"Successfully Inserted",Toast.LENGTH_SHORT).show();
        }
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }

    public void back(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}
