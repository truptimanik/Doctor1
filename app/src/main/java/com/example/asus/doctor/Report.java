package com.example.asus.doctor;

/**
 * Created by ASUS on 16-07-2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Report extends Fragment{



    private Button submit;
    private Button cam;
    private ImageView imageView;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private StorageReference mStore;
    private static final int GALLERY_INTENT = 2;
    private static final int CAMERA_REQUEST_CODE = 1;

     String USERID;
    private ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        mStore = FirebaseStorage.getInstance().getReference();
        //CATCH THE VALUE OF USER ID FROM ACTIVITY Patient.java
        Bundle bundle = this.getArguments();
           final String userid=bundle.getString("UID"); //getArguments().getString("UID");
      //  Toast.makeText(getContext(), "User id retrived"+ userid, Toast.LENGTH_LONG).show();

        String inter = userid.replace('.','-');
        USERID = inter.replace('@','-');


     //   Toast.makeText(getContext(), "User id changed"+ USERID, Toast.LENGTH_LONG).show();

        mStore = FirebaseStorage.getInstance().getReference();
        View v = inflater.inflate(R.layout.report,container,false);

        auth=FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        submit=(Button)v.findViewById(R.id.buttonReport);
       // System.out.println("=====submit"+submit);
        cam =(Button)v.findViewById(R.id.buttonCamera);
        imageView=(ImageView)v.findViewById(R.id.imageReport);

        progressDialog = new ProgressDialog(getContext());

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1,CAMERA_REQUEST_CODE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                   // FirebaseUser user = auth.getCurrentUser();
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);





                  //  Toast.makeText(getContext(), "Info Saved...", Toast.LENGTH_LONG).show();

                }

        });

        return v;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT || requestCode==CAMERA_REQUEST_CODE){

            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            Uri uri = data.getData();
          //  String id = databaseReference.push().getKey();
            StorageReference filepath = mStore.child("REPORTS").child(USERID);

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloaduri =taskSnapshot.getDownloadUrl();
                  String s=  downloaduri.toString();
                    url url =new url(s);
                    databaseReference.child("Report").child(USERID).setValue(url);
                    Picasso.with(getContext()).load(downloaduri).fit().into(imageView);
                    Toast.makeText(getContext(), "Report Uploaded...", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });


        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }


}
