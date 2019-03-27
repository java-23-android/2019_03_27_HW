package com.sheygam.java_23_27_03_19_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameTxt, lastNameTxt,phoneTxt,cityTxt;
    EditText inputName, inputLastName, inputPhone, inputCity;
    ViewGroup textWrapper, inputWrapper;
    Button logoutBtn, editBtn, saveBtn;
    Profile curr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        curr = StoreProvider.getInstance().getProfile();
        if(curr == null){
            curr = new Profile();
        }

        nameTxt = findViewById(R.id.name_txt);
        lastNameTxt = findViewById(R.id.last_name_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        cityTxt = findViewById(R.id.city_txt);

        inputName = findViewById(R.id.input_name);
        inputLastName = findViewById(R.id.input_last_name);
        inputPhone = findViewById(R.id.input_phone);
        inputCity = findViewById(R.id.input_city);

        textWrapper = findViewById(R.id.text_wrapper);
        inputWrapper = findViewById(R.id.input_wrapper);

        logoutBtn = findViewById(R.id.logout_btn);
        editBtn = findViewById(R.id.edit_btn);
        saveBtn = findViewById(R.id.save_btn);

        logoutBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        showViewMode();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                StoreProvider.getInstance().logout();
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.edit_btn:
                showEditMode();
                break;
            case R.id.save_btn:
                curr.name = inputName.getText().toString();
                curr.lastName = inputLastName.getText().toString();
                curr.phone = inputPhone.getText().toString();
                curr.city = inputCity.getText().toString();
                StoreProvider.getInstance().putProfile(curr);
                showViewMode();
                break;
        }
    }

    private void showEditMode(){
        inputName.setText(curr.name);
        inputLastName.setText(curr.lastName);
        inputPhone.setText(curr.phone);
        inputCity.setText(curr.city);
        textWrapper.setVisibility(View.GONE);
        logoutBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.GONE);
        inputWrapper.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.VISIBLE);
    }

    private void showViewMode(){
        nameTxt.setText(curr.name);
        lastNameTxt.setText(curr.lastName);
        phoneTxt.setText(curr.phone);
        cityTxt.setText(curr.city);
        inputWrapper.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);
        textWrapper.setVisibility(View.VISIBLE);
        logoutBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.VISIBLE);
    }
}
