package com.example.liveasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyMobile extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mobile);


        TextInputLayout mobilenumber = findViewById(R.id.mobilenumber);
        TextView continueBtn = findViewById(R.id.Continue_Btn);
        ProgressBar pbar = findViewById(R.id.progress_bar_mobile);


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile = mobilenumber.getEditText().getText().toString().trim();



                if (!mobile.isEmpty())
                {
                    mobilenumber.setError(null);
                    mobilenumber.setErrorEnabled(false);


                            if ((mobile).length() == 10) {

                                pbar.setVisibility(View.VISIBLE);
                                continueBtn.setVisibility(View.INVISIBLE);


                                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mobile, 60, TimeUnit.SECONDS, VerifyMobile.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                                pbar.setVisibility(View.GONE);
                                                continueBtn.setVisibility(View.VISIBLE);


                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                                pbar.setVisibility(View.GONE);
                                                continueBtn.setVisibility(View.VISIBLE);
                                                Toast.makeText(VerifyMobile.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                super.onCodeSent(backendotp, forceResendingToken);
                                                pbar.setVisibility(View.GONE);
                                                continueBtn.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(getApplicationContext(),OTPVerify.class);
                                                intent.putExtra("mobile", mobile);
                                                intent.putExtra("backendotp", backendotp);
                                                startActivity(intent);



                                            }
                                        }

                                );

                            }

                            else {
                                pbar.setVisibility(View.INVISIBLE);
                                continueBtn.setVisibility(View.VISIBLE);

                                mobilenumber.setError("Invalid Mobile Number!");
                            }
                        }
                     else {
                    mobilenumber.setError("Mobile Number Required");
                }
            }

        });


    }
}