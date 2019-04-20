package com.example.hxx.login01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    MyDataBaseHelper dbHelper;
    EditText user;
    EditText password;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper=new MyDataBaseHelper(this,"USER.db",null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        user=(EditText) findViewById(R.id.user_change);
        password=(EditText) findViewById(R.id.password_change);
        change=(Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER=user.getText().toString();
                String PASSWORD=password.getText().toString();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("PASSWORD",PASSWORD);
                db.update("USER",values,"USER=?",new String[]{USER});
                Toast.makeText(ChangePassword.this,"修改成功"+USER+"用户",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
