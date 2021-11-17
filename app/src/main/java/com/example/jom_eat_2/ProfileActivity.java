package com.example.jom_eat_2;
//profile page
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

public class ProfileActivity extends AppCompatActivity {

    TextView m_tv_sign_in_up;
    TextView mTvEmail, mTvPhoneNumber, mTvEmailVerify;
    FirebaseAuth mFirebaseAuth;
    Button btVerify;
    FirebaseFirestore mFirebaseFirestore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user();

        //if(mFirebaseAuth.getCurrentUser() != null){
         //  userInfo();
       // }

        privacyPolicy();
        bottomNavigation();
    }

    private void user(){

        m_tv_sign_in_up = findViewById(R.id.tv_sign_in_up);

        m_tv_sign_in_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MakeSelectionActivity.class));
            }
        });
    }


    private void userInfo(){

        userID = mFirebaseAuth.getCurrentUser().getUid();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        if (!user.isEmailVerified())
        {
            mTvEmailVerify.setVisibility(View.VISIBLE);
            btVerify.setVisibility(View.VISIBLE);

            btVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(ProfileActivity.this, "Verification email Has been Sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(ProfileActivity.this, "Failed to send verification email !" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
    }

    //privacy policy page
    private void privacyPolicy(){

        ConstraintLayout m_cl_policy;

        m_cl_policy = findViewById(R.id.cl_policy);

        m_cl_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, PagePrivacyPolicy.class));
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

        //click for random food page
        m_random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, RandomFoodActivity.class));
            }
        });

        //click for home page  activity
        m_home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });

        //click for  profile  page
        m_profile_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent profileActivityIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(profileActivityIntent);
            }
        });

        //click for  map page activity
        m_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,MapsActivity.class));
            }
        });

        //click for food  list activity
        m_food_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,FoodModelListActivity.class));
            }
        });
    }
}