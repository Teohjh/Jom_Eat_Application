package com.example.jom_eat_2;
//profile page (set when forget password)
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity
{
    //Initialize variable
    EditText etEmail;
    Button btnNext;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mFirebaseAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Assign variable
        etEmail = findViewById(R.id.et_fpEmail);
        btnNext = findViewById(R.id.btn_fpNext);

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Extract the email and send reset link
                String email = etEmail.getText().toString();
                mFirebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        //when email sent successful
                        Toast.makeText(ForgotPasswordActivity.this, "Password Reset Link have been sent to your email!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        //when fail send link to email
                        Toast.makeText(ForgotPasswordActivity.this, "Error ! Password reset link will not send to your email!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}