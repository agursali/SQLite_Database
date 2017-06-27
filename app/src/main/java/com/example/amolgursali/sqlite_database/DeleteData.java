package com.example.amolgursali.sqlite_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteData extends AppCompatActivity {

    EditText email;
    Button delete;
    Toolbar toolbar;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabaseop db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        initialize();
        setUpAsAction();
    }

    private void initialize()
    {
        email=(EditText)findViewById(R.id.emailid);
        delete=(Button)findViewById(R.id.delete);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delete");
        db=new SQLiteDatabaseop(getApplicationContext());
    }

    private void setUpAsAction()
    {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              if(!validateEmail())
              {
                  return;
              }
                Pojoclass po=new Pojoclass();
                po.setEmailID(email.getText().toString());
                Log.d("DeletedData",po.getEmailID());
                db.delete(po);

            }
        });
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
}
