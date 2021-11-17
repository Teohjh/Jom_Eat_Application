package com.example.jom_eat_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MakeSelectionActivity extends AppCompatActivity {

    Button btEmailLogin, btEmailRegister , btUsePhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

        btEmailLogin = findViewById(R.id.btn_emailLogin);
        btEmailRegister = findViewById(R.id.btn_emailRegister);
        btUsePhoneNumber = findViewById(R.id.btn_usePhoneNumber);

        btEmailLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MakeSelectionActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btEmailRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MakeSelectionActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btUsePhoneNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MakeSelectionActivity.this, UsePhoneNumber.class);
                startActivity(i);
            }
        });
    }
}