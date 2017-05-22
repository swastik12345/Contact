package com.example.kannu.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Kannu on 8/25/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static String dbName="Contact.db";
    public static int dbVersion=1;
    public static String tableName="data";
    public static String tableName1="image";
    public static String query="create table data(_id integer primary key autoincrement,name text,contact text,email text)";
    public static String query1="create table image(image blob)";
    public static String column1="name";
    public static String column2="contact";
    public static String column3="email";
    public static String column4="image";
    SQLiteDatabase db;

    public DBHelper(Context context)
    {
        super(context,dbName,null,dbVersion);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
         db.execSQL(this.query); db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long save(String name,String contact,String email)
    {
        db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(column1,name);
        cv.put(column2,contact);
        cv.put(column3,email);
        long num=db.insert(tableName,null,cv);
        db.close();
        return num;

    }

    public ArrayList viewAll()
    {
        db=getReadableDatabase();
        ArrayList<String> arrayList=new ArrayList();

        Cursor c=db.query(tableName, new String[]{"name","contact"},null,null,null,null,"name");
        int count=c.getColumnCount();
        if(c!=null)
        {
            while(c.moveToNext())
            {
                String uname=c.getString(0);
                arrayList.add(uname);
            }
        }
        db.close();
        return arrayList;
    }


    public ArrayList searchRecord(String uname)
    {
        db=getReadableDatabase();
        ArrayList alist=new ArrayList();
        Cursor c=db.query(tableName, null, "name=?", new String[]{uname}, null, null, null);
        if(c!=null)
        {
            c.moveToNext();
            String id=c.getString(0);
            String name=c.getString(1);
            String contact=c.getString(2);
            String Email=c.getString(3);
            alist.add(id);
            alist.add(name);
            alist.add(contact);
            alist.add(Email);
        }
        db.close();
        return alist;
    }
    public int deleteRecord(String name)
    {
        db=getReadableDatabase();
        int i=db.delete(tableName,"name=?",new String[]{name});
        db.close();
        return i;
    }
    public int updateRecord(String name,String contact,String email)
    {
        db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(column1,name);
        cv.put(column2,contact);
        cv.put(column3,email);
        int i=db.update(tableName,cv,"name=?",new String[]{name});
        db.close();
        return i;

    }
}
