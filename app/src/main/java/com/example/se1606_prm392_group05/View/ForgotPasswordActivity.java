package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se1606_prm392_group05.Data.RegisterHelper;
import com.example.se1606_prm392_group05.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText username;
    Button reset;
    RegisterHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.etUsername);
        reset = findViewById(R.id.btnResetPassword);

        DB = new RegisterHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();

                Boolean checkUser = DB.checkUsername(user);
                if(checkUser==true){
                    Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else{
                    Toast.makeText(ForgotPasswordActivity.this,   "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}