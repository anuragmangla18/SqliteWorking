package com.apkglobal.anurag.sqliteworking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
EditText et_name,et_email,et_mobile;
    Button btn_save,btn_show;
    TextView tv_id,tv_name,tv_email,tv_mobile;
    String sid,sname,smobile,semail;
    SQLiteDatabase sd;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_mobile=(EditText)findViewById(R.id.et_mobile);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_show=(Button)findViewById(R.id.btn_show);
        tv_id=(TextView)findViewById(R.id.tv_id);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_mobile=(TextView)findViewById(R.id.tv_mobile);
        tv_email=(TextView)findViewById(R.id.tv_email);
        // Creating the database

        createdatabase();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to insert the data
                insertintotable();

            }
        });

btn_show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // to fetch the data
        toshowdatafromtable();
    }
});



    }

    private void toshowdatafromtable() {
        String sid1,sname1,smobile1,semail1;
        String fetch="select * from anurag";
// to fetch the data from table
        c=sd.rawQuery(fetch,null);
        c.moveToLast();
        sid1=c.getString(0);
        sname1=c.getString(1);
        smobile1=c.getString(2);
        semail1=c.getString(3);

        tv_id.setText(sid1);
        tv_name.setText(sname1);
        tv_mobile.setText(smobile1);
        tv_email.setText(semail1);



    }

    private void insertintotable() {

        sname=et_name.getText().toString();
        semail=et_email.getText().toString();
        smobile=et_mobile.getText().toString();
        String insert="insert into anurag(name,mobile,email) values('"+sname+"','"+smobile+"','"+semail+"');";
        sd.execSQL(insert);
        Toast.makeText(getApplicationContext(),"insert done Successfully",Toast.LENGTH_LONG).show();
    }

    private void createdatabase() {


        // create new database

        sd=openOrCreateDatabase("Dbname", Context.MODE_PRIVATE,null);
        //to create new table in database
        sd.execSQL("create table if not exists anurag(id integer primary key autoincrement,"+
        "name varchar(100),mobile varchar(13),email varchar(150));");
        Toast.makeText(getApplicationContext(),"table created",Toast.LENGTH_LONG).show();
    }
}
