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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editPassword;
    private TextView textViewSignin;
    private EditText editTextToken;
    String  email,emailid,email1;
    String password;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

     /* if(firebaseAuth.getCurrentUser()!=null)
        {
          // finish();
          startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            //start profile activity

        }*/

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail= (EditText)findViewById(R.id.editTextEmail);
        editPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignin=(TextView)findViewById(R.id.TextViewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser()
    {
        email = editTextEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();

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

        //if validations are ok show progress bar
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //can start profile activity here
                        Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();

                        // To check the tokens are correct, if correct than redirect to activity else back to Login screen.

                        emailid=email.replace('.','-');
                        email1 = emailid.replace('@','-');

                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        //if(databaseReference.child("Doctor").equalTo(email1)==null )
                        if(email!="doc@gmail.com")
                        {
                            Toast.makeText(MainActivity.this,"This Doctor is Still not Approved",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            return;

                        }



                        progressDialog.dismiss();



                      startActivity(new Intent(getApplicationContext(),PatientList.class));
                        //finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Fail Try Again",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            });
            /*firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //user succefully registered
                        // so the activity after succesfull regestration
                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                          //  finish();
                        //    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            //start profile activity


                    }
                    else{
                        Toast.makeText(MainActivity.this,"Failed to Register",Toast.LENGTH_SHORT).show();
                      //  finish();
                    }
                    progressDialog.dismiss();
                }
            });*/

    }

    @Override
    public void onClick(View v) {

        if(v==buttonRegister){
            registerUser();
        }

       if(v==textViewSignin){
            //open login activity
         Intent intent = new Intent(MainActivity.this,LoginActivity.class);
           intent.putExtra("email",email);
           intent.putExtra("pass",password);
           startActivity(intent);

        }
    }
}
