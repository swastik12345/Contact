package com.example.kannu.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    TextView t1, t2, t3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        t1 = (TextView) findViewById(R.id.textView5);
        t2 = (TextView) findViewById(R.id.textView7);
        t3 = (TextView) findViewById(R.id.textView9);
        b1 = (Button) findViewById(R.id.button4);
        b2 = (Button) findViewById(R.id.button5);
        b3 = (Button) findViewById(R.id.button9);
        Intent i = getIntent();
        final ArrayList<String> alist = i.getStringArrayListExtra("values");
        t1.setText(alist.get(1));
        t2.setText(alist.get(2));
        t3.setText(alist.get(3));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper obj = new DBHelper(getApplicationContext());
                String str = t1.getText().toString();
                int i = obj.deleteRecord(str);
                Toast.makeText(getApplicationContext(), str + " Record Delete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplication(), Edit.class);
                i.putExtra("values", alist);
                startActivity(i);


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = t2.getText().toString();
                Intent i=new Intent(Intent.ACTION_WEB_SEARCH,Uri.parse("http://www.google.com"));
                Toast.makeText(getApplication(), "Calling", Toast.LENGTH_SHORT).show();







                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(i);
            }
        });
    }
}
