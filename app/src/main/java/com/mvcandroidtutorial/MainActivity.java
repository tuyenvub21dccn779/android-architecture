package com.mvcandroidtutorial;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements LoginInterface{

    private EditText editEmail, editPassword;
    private TextView tvMessage;
    private Button btnLogin;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mLoginPresenter = new LoginPresenter(this);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        tvMessage = findViewById(R.id.tv_message);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String strEmail = editEmail.getText().toString().trim();
        String strPassword = editPassword.getText().toString().trim();

        User user = new User(strEmail, strPassword);
        mLoginPresenter.login(user);
    }

    @Override
    public void loginSuccess() {
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText("Login success");
        tvMessage.setTextColor(getResources().getColor(R.color.teal_200));

    }

    @Override
    public void loginError() {
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText("Email or Password invalid");
        tvMessage.setTextColor(Color.RED);
    }
}