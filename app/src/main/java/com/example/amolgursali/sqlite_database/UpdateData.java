package com.example.amolgursali.sqlite_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class UpdateData extends AppCompatActivity {
    Toolbar toolbar;
    EditText name,email,mono,age;
    Button submit;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<Pojoclass> arrayList;
    SQLiteDatabaseop sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        initialize();
        setUpUIAction();
    }
    private void initialize()
    {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.emailid);
        mono=(EditText)findViewById(R.id.mono);
        age=(EditText)findViewById(R.id.age);
        submit=(Button)findViewById(R.id.submit);
        setSupportActionBar(toolbar);
        arrayList=new ArrayList<>();
        sqlite=new SQLiteDatabaseop(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update");
    }
    private void setUpUIAction()
    {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!validateName())
                {
                    return;
                }
                if(!validateEmail())
                {
                    return;
                }
                if(!validateMono())
                {
                    return;
                }
                if(!validateAge())
                {
                    return;
                }
                Pojoclass p=new Pojoclass();
                p.setName(name.getText().toString());
                p.setEmailID(email.getText().toString());
                p.setMono(mono.getText().toString());
                p.setAge(age.getText().toString());
                Log.d("UPDATEDATA",p.getName()+""+p.getEmailID()+""+p.getMono()+""+p.getAge());
                sqlite.updateData(p);
            }
        });
    }
    public boolean validateName()
    {
        if(name.getText().toString().equals("") || name.getText().toString().length()==0)
        {
            name.setError("Please Insert Name");
            name.requestFocus();
            return false;
        }
        else
        {
            name.setError(null);
            return true;
        }
    }
    public boolean validateEmail()
    {
        if(!email.getText().toString().matches(emailPattern))
        {
            email.setError("Please Insert Valid Email ID");
            email.requestFocus();
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }
    }
    public boolean validateMono()
    {
        if(mono.getText().toString().length()!=10)
        {
            mono.setError("Please Insert Valid Mobile Number");
            mono.requestFocus();
            return false;
        }
        else
        {
            mono.setError(null);
            return true;
        }
    }
    public boolean validateAge()
    {
        if(age.getText().toString().length()<=0)
        {
            age.setError("Please Insert Valid Mobile Number");
            age.requestFocus();
            return false;
        }
        else
        {
            age.setError(null);
            return true;
        }
    }
}
