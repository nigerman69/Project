package com.example.project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        EditText usernameInput = findViewById(R.id.registerUsername);
        EditText passwordInput = findViewById(R.id.registerPassword);
        EditText confirmInput = findViewById(R.id.confirmPassword);
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString();
            String confirm = confirmInput.getText().toString();
            if (username.isEmpty()) {
                usernameInput.setError("Enter a username");
                return;
            }
            if (password.length() < 6) {
                passwordInput.setError("At least 6 characters");
                return;
            }
            if (!password.equals(confirm)) {
                confirmInput.setError("Passwords do not match");
                return;
            }
            Intent intent = new Intent(
                    RegisterActivity.this, LoginActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}