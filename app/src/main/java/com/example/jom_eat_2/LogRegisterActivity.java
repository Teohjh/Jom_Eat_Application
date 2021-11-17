package com.example.jom_eat_2;
//log in and register page activity
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LogRegisterActivity extends AppCompatActivity {

    //initialise variable
    TextView mTvEmail, mTvPhoneNumber, mTvEmailVerify,m_tv_sign_in_up;
    FirebaseAuth mFirebaseAuth;
    Button btVerify;
    FirebaseFirestore mFirebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        userID = mFirebaseAuth.getCurrentUser().getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_register);

        mTvEmail = findViewById(R.id.tv_email);
        mTvPhoneNumber = findViewById(R.id.tv_phoneNumber);
        m_tv_sign_in_up = findViewById(R.id.tv_sign_in_up);
        btVerify = findViewById(R.id.btn_verify);
        mTvEmailVerify = findViewById(R.id.tv_emailNotVerify);

        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        //when  email haven't verify by user
        //it wiil send a verify email to user
        if (!user.isEmailVerified())
        {
            mTvEmailVerify.setVisibility(View.VISIBLE);
            btVerify.setVisibility(View.VISIBLE);

            //user click for verify email
            btVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(LogRegisterActivity.this, "Verification email Has been Sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(LogRegisterActivity.this, "Failed to send verification email !" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }

        DocumentReference documentReference = mFirebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
            {
                mTvPhoneNumber.setText(value.getString("phoneNum"));
                mTvEmail.setText(value.getString("email"));
            }
        });

        privacyPolicy();
        bottomNavigation();
    }

    //button for log out
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
    }


    //privacy policy page
    private void privacyPolicy(){

        ConstraintLayout m_cl_policy;

        m_cl_policy = findViewById(R.id.cl_policy);

        m_cl_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogRegisterActivity.this, PagePrivacyPolicy.class));
            }
        });

    }
    //bottom navigation bar
    private void bottomNavigation(){

        FloatingActionButton m_random_btn = findViewById(R.id.random_btn);
        LinearLayout m_home_btn = findViewById(R.id.home_btn);
        LinearLayout m_profile_btn = findViewById(R.id.profile_btn);
        LinearLayout m_map_btn = findViewById(R.id.map_btn);
        LinearLayout m_food_list_btn = findViewById(R.id.food_btn);

        m_random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LogRegisterActivity.this, RandomFoodActivity.class));
            }
        });

        m_home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LogRegisterActivity.this,MainActivity.class));
            }
        });

        m_profile_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent profileActivityIntent = new Intent(LogRegisterActivity.this, LogRegisterActivity.class);
                startActivity(profileActivityIntent);
            }
        });

        m_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogRegisterActivity.this,MapsActivity.class));
            }
        });

        m_food_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogRegisterActivity.this,FoodModelListActivity.class));
            }
        });
    }
}