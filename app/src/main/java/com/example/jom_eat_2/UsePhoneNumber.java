package com.example.jom_eat_2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UsePhoneNumber extends AppCompatActivity
{

    //Initialize variable
    EditText etPhoneNumber, etPhoneNumberCode , etOTPNumber;
    Button btRequestOTP , btVerify,btResend;
    TextView textViewLogIn;
    String phoneNum , verificationCode;
    FirebaseAuth mFirebaseAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    PhoneAuthProvider.ForceResendingToken mForceResendingToken;
    FirebaseFirestore mFirebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_phone_number);

        //Assign variable
        etOTPNumber = findViewById(R.id.et_otpNumber);
        etPhoneNumberCode = findViewById(R.id.countryCodePicker);
        etPhoneNumber = findViewById(R.id.et_phoneNumber);
        btRequestOTP = findViewById(R.id.btn_requestOTP);
        textViewLogIn = findViewById(R.id.tvLogIn);
        btVerify = findViewById(R.id.btn_verify);
        btResend = findViewById(R.id.btn_resend);

        btResend.setEnabled(false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        btRequestOTP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String phoneNumberCode = etPhoneNumberCode.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNumberCode))
                {
                    Toast.makeText(UsePhoneNumber.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phoneNumber))
                {
                    Toast.makeText(UsePhoneNumber.this, "Field cannot be empty !", Toast.LENGTH_SHORT).show();
                    return;
                }

                phoneNum = "+"+etPhoneNumberCode.getText().toString()+etPhoneNumber.getText().toString();

                verifyPhoneNumber(phoneNum);

                Toast.makeText(UsePhoneNumber.this, phoneNum, Toast.LENGTH_SHORT).show();
            }
        });

        btResend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                verifyPhoneNumber(phoneNum);
                btResend.setEnabled(false);
            }
        });

        btVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //get the OTP
                String OTPNumber = etOTPNumber.getText().toString().trim();

                if (TextUtils.isEmpty(OTPNumber))
                {
                    Toast.makeText(UsePhoneNumber.this, "Please enter OTP number !", Toast.LENGTH_SHORT).show();
                    return;
                }

                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationCode, etOTPNumber.getText().toString());
                user(phoneAuthCredential);

            }
        });

        textViewLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(UsePhoneNumber.this, LoginActivity.class);
                startActivity(i);
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                user((phoneAuthCredential));
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                Toast.makeText(UsePhoneNumber.this, "Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
            {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                mForceResendingToken = forceResendingToken;

                etPhoneNumberCode.setVisibility(View.GONE);
                etPhoneNumber.setVisibility(View.GONE);
                btRequestOTP.setVisibility(View.GONE);

                etOTPNumber.setVisibility(View.VISIBLE);
                btVerify.setVisibility(View.VISIBLE);
                btResend.setVisibility(View.VISIBLE);
                btResend.setEnabled(false);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s)
            {
                super.onCodeAutoRetrievalTimeOut(s);

                btResend.setEnabled(true);
            }
        };
    }

    public void verifyPhoneNumber(String phoneNumber)
    {
        //send OTP
        PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mFirebaseAuth)
                .setActivity(this)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
    }

    public void user(PhoneAuthCredential phoneAuthCredential)
    {
        mFirebaseAuth.signInWithCredential(phoneAuthCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>()
        {
            @Override
            public void onSuccess(AuthResult authResult)
            {
                //phoneNum = "+"+etPhoneNumberCode.getText().toString()+etPhoneNumber.getText().toString();
                Toast.makeText(UsePhoneNumber.this, "Register Successfully !", Toast.LENGTH_SHORT).show();
                userID = mFirebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = mFirebaseFirestore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("phoneNum",phoneNum);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Toast.makeText(UsePhoneNumber.this, "User Profile is created for" + userID, Toast.LENGTH_SHORT).show();
                    }
                });
                Intent i = new Intent(UsePhoneNumber.this, LogRegisterActivity.class);
                //i.putExtra("phoneNum", phoneNum);
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(UsePhoneNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Intent i = new Intent(UsePhoneNumber.this, LogRegisterActivity.class);
            i.putExtra("phoneNum", phoneNum);
            startActivity(i);
        }
    }
}