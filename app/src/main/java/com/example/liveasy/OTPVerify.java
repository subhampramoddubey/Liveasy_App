package com.example.liveasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerify extends AppCompatActivity {

    EditText otp1,otp2,otp3,otp4,otp5,otp6 ;
    TextView button;
    String getotpbackend;
    ProgressBar Pbarbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ProgressBar Pbarbutton = findViewById(R.id.progress_bar);
        button = findViewById(R.id.continueOtp);
        otp1 = findViewById(R.id.otp_1);
        otp2 = findViewById(R.id.otp_2);
        otp3 = findViewById(R.id.otp_3);
        otp4 = findViewById(R.id.otp_4);
        otp5 = findViewById(R.id.otp_5);
        otp6 = findViewById(R.id.otp_6);

        TextView text =findViewById(R.id.blank_space);
        text.setText(String.format("Code is sent to +91 %s",getIntent().getStringExtra("mobile")));
        getotpbackend =getIntent().getStringExtra("backendotp");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!otp1.getText().toString().isEmpty() && !otp2.getText().toString().isEmpty() && !otp3.getText().toString().isEmpty() && !otp4.getText().toString().isEmpty() && !otp5.getText().toString().isEmpty() && !otp6.getText().toString().isEmpty()) {
                    String enteredotp = otp1.getText().toString()
                            + otp2.getText().toString()
                            + otp3.getText().toString()
                            + otp4.getText().toString()
                            + otp5.getText().toString()
                            + otp6.getText().toString();
                    if (getotpbackend != null) {
                        Pbarbutton.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneauthcredendial = PhoneAuthProvider.getCredential(getotpbackend, enteredotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneauthcredendial).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Pbarbutton.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Please Recheck Your Internet Connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter The OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });


        numMove();


        findViewById(R.id.resendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, OTPVerify.this,

                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newbackendotp, forceResendingToken);

                                getotpbackend = newbackendotp;
                                Toast.makeText(OTPVerify.this, "OTP Resend Successful", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });


    }




    private void numMove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty())
                {
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty())
                {
                    otp3.requestFocus();
                }else otp1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty())
                {
                    otp4.requestFocus();
                }else otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty())
                {
                    otp5.requestFocus();
                }else otp3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty())
                {
                    otp6.requestFocus();
                }else otp4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().trim().isEmpty()) {

                    otp5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



}