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
               // Khởi tạo các phần tử giao diện

        username = findViewById(R.id.etUsername);
        reset = findViewById(R.id.btnResetPassword);
                 // Khởi tạo trợ lý cơ sở dữ liệu
        DB = new RegisterHelper(this);
                // Đặt người nghe sự kiện cho nút "reset"
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy tên người dùng nhập vào
                String user = username.getText().toString();
                // Kiểm tra xem tên người dùng có tồn tại trong cơ sở dữ liệu không
                Boolean checkUser = DB.checkUsername(user);
                // Nếu tên người dùng tồn tại, chuyển đến ResetPasswordActivity
                if(checkUser==true){
                    Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else{
                    // Nếu tên người dùng không tồn tại, hiển thị thông báo
                    Toast.makeText(ForgotPasswordActivity.this,   "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}