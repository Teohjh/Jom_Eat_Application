package com.example.jom_eat_2;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //Initialize variable
    EditText etEmail, etUsername , etPassword, etRePassword;
    Button btRegister;
    TextView textViewLogIn, textViewPhoneLogin;
    CheckBox cbShowpassword;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestore;
    String userID;
//    FirebaseDatabase mFirebaseDatabase;
//    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Assign variable
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etRePassword = findViewById(R.id.et_reenter_password);
        btRegister = findViewById(R.id.btn_register);
        textViewLogIn = findViewById(R.id.tvLogIn);
        textViewPhoneLogin = findViewById(R.id.tvUsePhoneNumber);
        cbShowpassword = findViewById(R.id.cb_showPassword);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), LogRegisterActivity.class));
            finish();
        }

        cbShowpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(com.example.jom_eat_2.RegisterActivity.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username))
                {
                    Toast.makeText(com.example.jom_eat_2.RegisterActivity.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(com.example.jom_eat_2.RegisterActivity.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8)
                {
                    Toast.makeText(com.example.jom_eat_2.RegisterActivity.this, "Password cannot less than 8 characters !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rePassword))
                {
                    Toast.makeText(com.example.jom_eat_2.RegisterActivity.this, "Password and Confirm Password are not matching !", Toast.LENGTH_SHORT).show();
                    return;
                }

                //register the user in firebase
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (!task.isSuccessful())
                                {
                                    Toast.makeText(RegisterActivity.this, "Register Failed !" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    //sent verification link
                                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                    firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                                    {
                                        @Override
                                        public void onSuccess(Void aVoid)
                                        {
                                            Toast.makeText(RegisterActivity.this, "Verification email Has been Sent", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener()
                                    {
                                        @Override
                                        public void onFailure(@NonNull Exception e)
                                        {
                                            Toast.makeText(RegisterActivity.this, "Failed to send verification email !" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    //String email = etEmail.getText().toString();
                                    Toast.makeText(RegisterActivity.this, "Register Successfully !", Toast.LENGTH_SHORT).show();
                                    userID = mFirebaseAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = mFirebaseFirestore.collection("users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("username",username);
                                    user.put("email",email);
                                    user.put("password", password);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>()
                                    {
                                        @Override
                                        public void onSuccess(Void aVoid)
                                        {
                                            Toast.makeText(RegisterActivity.this, "User Profile is created for" + userID, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Intent i = new Intent(RegisterActivity.this, LogRegisterActivity.class);
                                    //i.putExtra("email", email);
                                    startActivity(i);
                                }
                            }
                        });
                {

                }
            }
        });

        textViewLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        textViewPhoneLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RegisterActivity.this, UsePhoneNumber.class);
                startActivity(i);
            }
        });

    }
}