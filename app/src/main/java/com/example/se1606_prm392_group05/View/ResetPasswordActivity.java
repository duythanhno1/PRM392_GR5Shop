package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1606_prm392_group05.Data.RegisterHelper;
import com.example.se1606_prm392_group05.R;

public class ResetPasswordActivity extends AppCompatActivity {
    TextView username;
    EditText pass, passConfirm;
    Button resetPassword;
    RegisterHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        username = findViewById(R.id.username_reset_text);
        pass = findViewById(R.id.etPasswordReset);
        passConfirm = findViewById(R.id.etPasswordResetConfirm);
        resetPassword = findViewById(R.id.btnConfirmResetPassword);
        DB = new RegisterHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String rePassword = passConfirm.getText().toString();
                if(password.equals(rePassword)){
                    Boolean checkPasswordUpdate = DB.updatePassword(user, password);
                    if(checkPasswordUpdate == true){
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetPasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Password updated unsuccessfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Password isn't matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}