package com.example.hxx.login01;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegesterActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    Button regester;
    private MyDataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        user=(EditText) findViewById(R.id.user_reg);
        password=(EditText) findViewById(R.id.password_reg);
        dbHelper=new MyDataBaseHelper(this,"USER.db",null,1);
        regester=(Button) findViewById(R.id.regester_reg);
        regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String USER=user.getText().toString();
                String PASSWORD=password.getText().toString();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();


                Cursor  cursor=db.query("USER",null,null,null,null,null,null,null);

                if (cursor.moveToFirst()){
                    do {
                        String user=cursor.getString(cursor.getColumnIndex("USER"));
                        String password=cursor.getString(cursor.getColumnIndex("PASSWORD"));
                        Log.e("MainActivity",user);
                        Log.e("MainActivity",password);
                        if (user.equals(USER)){
                            Log.e("MainActivity","有问题");
                            Toast.makeText(RegesterActivity.this,"该账号已被注册，换个名字嘛",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            Toast.makeText(RegesterActivity.this,"注册成功，好兄弟嗷",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegesterActivity.this,MainActivity.class);
                            startActivity(intent);
                            SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                            editor.putString("user",USER);
                            editor.putString("password",PASSWORD);
                        }
                    }while (cursor.moveToNext());
                    values.put("USER",USER);
                    values.put("PASSWORD",PASSWORD);
                    db.insert("USER",null,values);
                }
            }
        });
    }
}
