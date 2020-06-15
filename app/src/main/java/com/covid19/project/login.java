package com.covid19.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText EmailId, password;
    Button SignInButton;
    TextView SignUpTextView, tvForgot;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.loginPageEmailEditText);
        password = findViewById(R.id.loginPagePasswordEditText);
        SignUpTextView = findViewById(R.id.goToSignUpPage);
        SignInButton = findViewById(R.id.signInButton);



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                  /*  Toast.makeText(login.this, "you are successfully logged in", Toast.LENGTH_SHORT);
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                    finish();*/
                } else {
                    Toast.makeText(login.this, "please login first!", Toast.LENGTH_SHORT);
                }
            }
        };
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailId.getText().toString();
                String password = login.this.password.getText().toString();
                if (email.isEmpty()) {
                    EmailId.setError("plz check email id");
                    EmailId.requestFocus();
                } else if (password.isEmpty()) {
                    login.this.password.setError("plz enter your password");
                    login.this.password.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(login.this, "Please enter the information !", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        //Log.w("TAG", "signInWithEmail:failed", task.getException());

                                    } else {
                                        checkIfEmailVerified();
                                    }
                                    // ...
                                }
                            });
                    /*mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "login Error", Toast.LENGTH_SHORT);
                            } else {
                                Intent intToHome = new Intent(login.this, MainActivity.class);
                                startActivity(intToHome);
                                finish();
                            }
                        }
                    });*/
                } else {
                    Toast.makeText(login.this, "Error occurred!", Toast.LENGTH_SHORT);
                }
            }
        });
        SignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(login.this, SignUp.class);
                startActivity(intSignUp);
            }
        });

        tvForgot = findViewById(R.id.tvForgot);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, ForgotActivity.class);
                startActivity(i);

            }
        });

    }

    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            Intent intent =new Intent(login.this,MainActivity.class );

            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }

}

