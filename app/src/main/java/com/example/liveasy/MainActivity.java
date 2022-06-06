package com.example.liveasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView nextBtn;
    private FirebaseAuth mAuth;
    TextInputLayout language;
    String lang;



    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Intent i = new Intent(getApplicationContext(),HomePage.class);
            startActivity(i);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBtn = findViewById(R.id.next_btn);
        language = findViewById(R.id.language);

        String[] languages = {"English"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , R.layout.dropdown,languages);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.select_language);
        autoCompleteTextView.setAdapter(adapter);



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!autoCompleteTextView.getText().toString().isEmpty()) {
                    language.setError(null);
                    language.setErrorEnabled(false);

                    Intent i = new Intent(getApplicationContext(), VerifyMobile.class);
                    startActivity(i);
                }else{
                    language.setError("Select Language");
                }
            }
        });


    }
}