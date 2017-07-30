package com.example.asus.doctor;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends ListActivity {
        ListView litview;
    List<PatName> patnm;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        litview = (ListView)findViewById(R.id.litview);

        patnm = new ArrayList<>();

        litview = getListView();

       litview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextView textView = (TextView)view.findViewById(R.id.patientname);
               String nm = textView.getText().toString();

               Intent intent = new Intent(PatientList.this,Patient.class);
               intent.putExtra("user",nm);
               startActivity(intent);
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
    //    Toast.makeText(PatientList.this,"List Loading",Toast.LENGTH_LONG).show();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("PATIENT");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               patnm.clear();
                for(DataSnapshot patientsnapshot: dataSnapshot.getChildren()){


                    PatName patname = patientsnapshot.getValue(PatName.class);
                    patnm.add(patname);

                }

                PatientListAdapter adapter = new PatientListAdapter(PatientList.this, patnm);
                litview.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
