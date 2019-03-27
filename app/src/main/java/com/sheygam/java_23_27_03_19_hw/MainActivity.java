package com.sheygam.java_23_27_03_19_hw;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText inputEmail, inputPassword;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StoreProvider.getInstance().setContext(this);
        if(StoreProvider.getInstance().getToken()!=null){
            showNextActivity();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn) {
            StoreProvider.getInstance().login(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
            showNextActivity();
        }
    }

    private void showNextActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_CANCELED){
            finish();
        }
    }
}
