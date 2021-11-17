package com.example.jom_eat_2;
//profile activity (log in page)
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Initialize variable
    EditText etEmail , etPassword;
    Button btLogIn;
    TextView textViewRegister , textViewForgotPassword , textViewPhoneLogin;
    CheckBox cbShowPassword;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mFirebaseAuth = FirebaseAuth.getInstance();

        //when user haven't logout
        if (mFirebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(LoginActivity.this, LogRegisterActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assign variable
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btLogIn = findViewById(R.id.btn_login);
        textViewRegister = findViewById(R.id.tvRegister);
        textViewForgotPassword = findViewById(R.id.forgot_password);
        textViewPhoneLogin = findViewById(R.id.tvUsePhoneNumber);
        cbShowPassword = findViewById(R.id.cb_showPassword);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //click for user forget password
        textViewForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        //click for submit to login
        btLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                //validation when error typing or null and pop up message
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8)
                {
                    Toast.makeText(LoginActivity.this, "Password cannot less than 8 characters !", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate the user
                mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //when fail log in
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Log In Failed !", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //success log in
                            String email = etEmail.getText().toString();
                            Toast.makeText(LoginActivity.this, "Log In Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, LogRegisterActivity.class);
                            i.putExtra("email", email);
                            startActivity(i);

                        }

                    }
                });
            }
        });

        //click for when user didn't have register for this application
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        //user are allow  to use phone  number for login or  register within OTP
        textViewPhoneLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(LoginActivity.this, UsePhoneNumber.class);
                startActivity(i);
            }
        });
    }
}