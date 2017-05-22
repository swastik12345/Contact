package com.example.kannu.contacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity
{
    ListView l1;
    Button b1;
    AutoCompleteTextView ac;
    ImageButton ib;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1=(ListView)findViewById(R.id.listView);
        ac=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
       // b1=(Button)findViewById(R.id.button7);
       // ib=(ImageButton) findViewById(R.id.imageButton);
        DBHelper obj=new DBHelper(this);
        ArrayList alist=obj.viewAll();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,alist);
        l1.setAdapter(adapter);
        //ac.dismissDropDown();
        ac.post(new Runnable() {
            @Override
            public void run() {
                ac.dismissDropDown();
            }
        });

        ac.setAdapter(adapter);
        ac.setThreshold(1);


        l1.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                String item=(String) arg0.getItemAtPosition(position);

                DBHelper obj=new DBHelper(getApplicationContext());
                ArrayList<String> alist=obj.searchRecord(item);

                Intent i=new Intent(getApplication(), Details.class);
                i.putStringArrayListExtra("values", alist);
                startActivity(i);

            }
        });


    }


    public void Add(View view)
    {
        Intent i=new Intent(this,AddContact.class);
        startActivity(i);
    }



}
