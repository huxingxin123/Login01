package com.example.hxx.login01;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.webkit.WebHistoryItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button regester;
    CheckBox checkBox;
    private MyDataBaseHelper dbHelper;
    EditText user;
    EditText password;
     SharedPreferences.Editor editor;
    SharedPreferences sp;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper=new MyDataBaseHelper(this,"USER.db",null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        sp=getSharedPreferences("data",MODE_PRIVATE);
        user=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.password);
        regester=(Button) findViewById(R.id.regester);
        checkBox=(CheckBox) findViewById(R.id.checkbox);
        boolean isRemember=sp.getBoolean("remember_password",false);
        if (isRemember){
            String user0=sp.getString("user","");
            String password0=sp.getString("password","");
            user.setText(user0);
            password.setText(password0);
            checkBox.setChecked(true);
        }
        regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegesterActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);

        final String USER=user.getText().toString();
        final String PASSWORD=user.getText().toString();
        user.setText(sharedPreferences.getString("USER",""));
        checkBox=(CheckBox) findViewById(R.id.checkbox);
        login=(Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("USER",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    while (cursor.moveToNext()){
                        String user = cursor.getString(cursor.getColumnIndex("USER"));
                        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                        Log.e("MainActivity", user);
                        Log.e("MainActivity", password);
                        if (user.equals(USER)&&password.equals(PASSWORD)) {
                            Log.e("MainActivity", "有问题");
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            editor = sp.edit();
                            if (checkBox.isChecked()) {
                                editor.putBoolean("remember_password", true);
                                editor.putString("user", USER);
                                editor.putString("password", PASSWORD);
                            } else {
                                editor.clear();
                            }
                            editor.apply();

                        }
                        else if (user.equals(USER)){
                            Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }else if (user!=USER){
                            Toast.makeText(MainActivity.this, "这个账号没有注册嗷", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                }
            });
    change=(Button) findViewById(R.id.change);
    change.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,ChangePassword.class);
            startActivity(intent);
        }
    });
    }
}
