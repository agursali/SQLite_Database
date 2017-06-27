package com.example.amolgursali.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by AmolGursali on 6/23/2017.
 */

public class SQLiteDatabaseop extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Employee";
    public static final int DATABASE_VERSION=2;
    public static final String NAME="Name";
    public static final String EMAILID="EmailID";
    public static final String MONO="Mono";
    public static final String AGE="Age";
    public static final String TABLE_NAME="empinfo";
    Context context;
    public SQLiteDatabaseop(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE="create table "+TABLE_NAME +"("+NAME+" VARCHAR,"+EMAILID+" VARCHAR,"+MONO+" VARCHAR,"+AGE+" VARCHAR"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addData(Pojoclass pojoclasses)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String q="select * from empinfo where EmailID='"+pojoclasses.getEmailID()+"'";
        Cursor c=database.rawQuery(q,null);
        if(c!=null)
            if(c.moveToFirst())
            {
                Toast.makeText(context, "Sorry Email ID is already exists ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(NAME,pojoclasses.getName());
                contentValues.put(EMAILID,pojoclasses.getEmailID());
                contentValues.put(MONO,pojoclasses.getMono());
                contentValues.put(AGE,pojoclasses.getAge());
                database.insert(TABLE_NAME,null,contentValues);
                Toast.makeText(context, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }

        database.close();
    }
    public void updateData(Pojoclass pojoclassses)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String q="select * from empinfo where EmailID='"+pojoclassses.getEmailID()+"'";
        Cursor c=database.rawQuery(q,null);
        if(c!=null)
            if(c.moveToFirst())
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put(NAME,pojoclassses.getName());
                contentValues.put(EMAILID,pojoclassses.getEmailID());
                contentValues.put(MONO,pojoclassses.getMono());
                contentValues.put(AGE,pojoclassses.getAge());
                database.update(TABLE_NAME,contentValues,EMAILID+"=?",new String[]{pojoclassses.getEmailID()});
                Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Sorry Email ID is not exists in Database", Toast.LENGTH_SHORT).show();
            }

        database.close();
    }
    public void delete(Pojoclass pojoclass)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String q="select * from empinfo where EmailID='"+pojoclass.getEmailID()+"'";
        Cursor c=database.rawQuery(q,null);
        if(c!=null)
            if(c.moveToFirst())
            {
                database.delete(TABLE_NAME,EMAILID+"=?",new String[]{pojoclass.getEmailID()});
                Toast.makeText(context, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Sorry Entered Data is not exists in database", Toast.LENGTH_SHORT).show();
            }

        database.close();
    }


    public Pojoclass select(Pojoclass emailID)
    {
        Pojoclass po = null;
        SQLiteDatabase database=this.getReadableDatabase();
        try
        {
//            String q="select * from empinfo "+"where EmailID='"+emailID+"'";
            String q="select * from empinfo where EmailID='"+emailID.getEmailID()+"'";
            Cursor c=database.rawQuery(q,null);
            if(c!=null)
                if(c.moveToFirst())
                {
                    po=new Pojoclass();

                    po.setName(c.getString(0));
                    po.setMono(c.getString(2));
                    po.setAge(c.getString(3));
                    Toast.makeText(context,"Name : ="+po.getName()+"Mobile Number:="+po.getMono()+"Age : "+po.getAge(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Sorry Data not exists in database", Toast.LENGTH_SHORT).show();
                }


        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        return po;

    }
}
