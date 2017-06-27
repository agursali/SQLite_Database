package com.example.amolgursali.sqlite_database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button insert,update,delete,select;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initialize();
        setUpViewAsAction();
    }
    private void initialize()
    {
        insert=(Button)findViewById(R.id.insert);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        select=(Button)findViewById(R.id.select);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
    }
    private void setUpViewAsAction()
    {
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,MainActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,UpdateData.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,DeleteData.class));
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,SelectData.class));
            }
        });
    }
}
