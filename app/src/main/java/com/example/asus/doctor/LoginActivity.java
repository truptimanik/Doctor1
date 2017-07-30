package com.example.asus.doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.doctor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextpassword;
    private TextView TextViewSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        //if uer i already logged in
        if(firebaseAuth.getCurrentUser()!=null)
        {
          // finish();
            startActivity(new Intent(getApplicationContext(),PatientList.class));
            //start profile activity

        }

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("pass");
    //    Toast.makeText(this, email+" ", Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, password+" ", Toast.LENGTH_SHORT).show();

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextpassword=(EditText)findViewById(R.id.editTextPassword);
        buttonSignin=(Button) findViewById(R.id.buttonSignin);
        TextViewSignup=(TextView)findViewById(R.id.TextViewSignUp);
        progressDialog = new ProgressDialog(this);
        buttonSignin.setOnClickListener(this);
        TextViewSignup.setOnClickListener(this);
    }

        private void UserLogin(){
            final String email = editTextEmail.getText().toString().trim();
            String password = editTextpassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                //email is empty
                Toast.makeText(this,"Please Enter your email",Toast.LENGTH_SHORT).show();
                //stopping the excution further
                return;
            }
            if(TextUtils.isEmpty(password)){
                //password is empty
                Toast.makeText(this,"Password cannot be Blank",Toast.LENGTH_SHORT).show();
                //stopping the excution further
                return;
            }
            progressDialog.setMessage("Logging in User...");
            progressDialog.show();
           firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   progressDialog.dismiss();
                   if(task.isSuccessful()){
             //          finish();
                       if(email.equals("doc@gmail.com"))

                     startActivity(new Intent(getApplicationContext(),PatientList.class));
                       else
                           return;
                       //start profile activity
                   }
               }
           });

        }
    @Override
    public void onClick(View v) {
        if(v==buttonSignin){
            UserLogin();
        }
        if(v==TextViewSignup){
          //  finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
