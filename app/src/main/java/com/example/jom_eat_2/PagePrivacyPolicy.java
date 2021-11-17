package com.example.jom_eat_2;

//privacy profile view at profile page
//policy had be register
//url: https://app-privacy-policy-generator.firebaseapp.com/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PagePrivacyPolicy extends AppCompatActivity {

    ImageView  m_previous_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_privacy_policy);

        m_previous_click = findViewById(R.id. previous_click);

        //back to profile page
        m_previous_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PagePrivacyPolicy.this, ProfileActivity.class));
            }
        });
    }
}