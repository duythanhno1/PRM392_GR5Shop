package com.example.se1606_prm392_group05.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se1606_prm392_group05.Controller.UserController;
import com.example.se1606_prm392_group05.Interface.LoginView;
import com.example.se1606_prm392_group05.R;
import com.example.se1606_prm392_group05.Data.RegisterHelper;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText usernameEditText;
    private EditText passwordEditText;
    TextView forgot;
    private Button loginButton;

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RegisterHelper registerHelper = new RegisterHelper(this); // Pass context to RegisterHelper
        userController = new UserController(registerHelper);

        Button signInButton = findViewById(R.id.btn_SignUp);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        forgot = findViewById(R.id.tv_forgotPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    showEmptyFieldsError();
                } else {
                    if (userController.authenticateUser(username, password)) {
                        showLoginSuccess();
                        boolean isAdmin = username.equals("admin") && password.equals("123");
                        if (isAdmin) {
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("USERNAME", username);
                                startActivity(intent);
                        }
                        finish();
                    } else {
                        showInvalidCredentials();
                    }
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidCredentials() {
        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
    }
}