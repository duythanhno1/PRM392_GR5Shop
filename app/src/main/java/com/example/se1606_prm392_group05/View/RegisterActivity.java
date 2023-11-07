package com.example.se1606_prm392_group05.View;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se1606_prm392_group05.Data.RegisterHelper;
import com.example.se1606_prm392_group05.Model.User;
import com.example.se1606_prm392_group05.R;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<User> userList;
    // Khai báo các EditText để nhập thông tin đăng ký, button đăng ký và button quay lại màn hình đăng nhập.
    private EditText etUsername, etPassword, etPasswordConfirm, etEmail, etPhoneNum, etAddress;
    private Button btnRegister;

    // Khởi tạo RegisterHelper để truy cập database.
    private RegisterHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNum = findViewById(R.id.etPhoneNum);
        etAddress = findViewById(R.id.etAddress);
        btnRegister = findViewById(R.id.btnRegister);
        Button signInButton = findViewById(R.id.btnSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Để kết thúc hoạt động của trang Register
            }
        });


        databaseHelper = new RegisterHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirm = etPasswordConfirm.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNum = etPhoneNum.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || email.isEmpty() || phoneNum.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show(); // Báo lỗi nếu trống
        } else if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(".*@gmail\\.com") || email.length() > 20) {
            Toast.makeText(this, "Please enter a valid Gmail address (max 20 characters)", Toast.LENGTH_SHORT).show(); // Báo lỗi nếu mật khẩu không khớp
        } else if (phoneNum.length() != 10) {
            Toast.makeText(this, "Please enter a 10-digit phone number", Toast.LENGTH_SHORT).show(); // Kiểm tra định dạng email, số điện thoại
        } else if (!phoneNum.matches("\\d+")) {
            Toast.makeText(this, "Please enter a numeric phone number", Toast.LENGTH_SHORT).show();
        } else {
            // Tạo đối tượng User từ thông tin người dùng
            User newUser = new User(username, password);

            // Thêm người dùng vào cơ sở dữ liệu
            boolean isSuccess = addUserToDatabase(newUser);
            if (isSuccess) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show(); // Hiển thị thông báo thành công
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); // Mở LoginActivity
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to register", Toast.LENGTH_SHORT).show(); // Hiển thị thông báo lỗi
            }
        }
    }

    // Gọi phương thức addUserToDatabase() để thêm vào database
    private boolean addUserToDatabase(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // Lấy writable database
        // Khởi tạo ContentValues để chứa dữ liệu người dùng
        ContentValues values = new ContentValues();
        // Insert vào bảng user
        values.put(RegisterHelper.COLUMN_USERNAME, user.getUsername());
        values.put(RegisterHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(RegisterHelper.COLUMN_EMAIL, user.getEmail());
        values.put(RegisterHelper.COLUMN_PHONE, user.getPhone());
        values.put(RegisterHelper.COLUMN_ADDRESS, user.getAddress());
        // Đóng database
        long result = db.insert(RegisterHelper.TABLE_USER, null, values);
        db.close();
        // Trả về kết quả thành công/thất bại
        return result != -1;
    }

}