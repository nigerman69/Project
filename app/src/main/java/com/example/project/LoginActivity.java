package com.example.project;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.CheckBox;
import android.content.SharedPreferences;



public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember_me";

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        TextView registerText = findViewById(R.id.registerText);
        CheckBox cbRememberMe = findViewById(R.id.cbRememberMe);
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUsername = getIntent().getStringExtra("username");
        String savedPassword = getIntent().getStringExtra("password");
        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString();
                    if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
            }
                    else if (savedUsername != null && savedPassword != null
                            && username.equals(savedUsername)
                            && password.equals(savedPassword)) {
                        if (cbRememberMe.isChecked()) {
                            saveCredentials(username, password);
                        }
                        Toast.makeText(this, "Login successful!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        registerText.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void saveCredentials(String username, String password) {

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_REMEMBER, true);

        editor.apply();
    }

}
