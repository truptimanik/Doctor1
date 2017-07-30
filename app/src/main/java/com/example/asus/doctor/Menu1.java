package com.example.asus.doctor;

/**
 * Created by ASUS on 16-07-2017.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Menu1 extends Fragment{

    private EditText EditTextname,EditTextage,EditTextweight,EditTextcondition,EditTextallergy;
    private Button submit;
private DatabaseReference databaseReference;
    String Name,Age,Weight,Condition,Allergy;
   private FirebaseAuth auth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
      //  Toast.makeText(getContext(),"this is working",Toast.LENGTH_LONG).show();



        //CATCH THE VALUE OF USER ID FROM ACTIVITY Patient.java
        Bundle bundle = this.getArguments();
           final String userid=bundle.getString("UID"); //getArguments().getString("UID");
     //   Toast.makeText(getContext(), "User id retrived"+ userid, Toast.LENGTH_LONG).show();

        String inter = userid.replace('.','-');
        final String USERID = inter.replace('@','-');


     //   Toast.makeText(getContext(), "User id changed"+ USERID, Toast.LENGTH_LONG).show();


        View v = inflater.inflate(R.layout.fragment_menu_1,container,false);
      //  View innerview = v.findViewById()
        auth=FirebaseAuth.getInstance();
//        final String id = databaseReference.child("PATIENT").push().getKey();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        EditTextname = (EditText)v.findViewById(R.id.editTextname);
        EditTextage = (EditText)v.findViewById(R.id.editTextage);
        EditTextweight = (EditText)v.findViewById(R.id.editTextweight);
        EditTextcondition = (EditText)v.findViewById(R.id.editTextcondition);
        EditTextallergy = (EditText)v.findViewById(R.id.editTextallergy);
        //Toast.makeText(getContext(), allergy.toString(), Toast.LENGTH_SHORT).show();
        submit=(Button)v.findViewById(R.id.Buttonsubmit);
        System.out.println("=====submit"+submit);
        //submit.setOnClickListener((View.OnClickListener) this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String name, age, weight, condition, allergy;

                    name = EditTextname.getText().toString().trim();
                    age = EditTextage.getText().toString().trim();
                    weight = EditTextweight.getText().toString().trim();
                    condition = EditTextcondition.getText().toString().trim();
                    allergy = EditTextallergy.getText().toString().trim();
                    final inputPatientHistory inputPatientHistory = new inputPatientHistory(name, age, weight, condition, allergy);
                    FirebaseUser user = auth.getCurrentUser();

                System.out.println("====get name==="+name);
         //     String id = databaseReference.child("PATIENT").push().getKey();
              //  databaseReference.child(id).equalTo(userid);


                databaseReference.child("PATIENT").child(USERID).child("PATIENT HISTORY").setValue(inputPatientHistory);





             //   databaseReference.child(user.getUid()).child("Medical History").setValue(inputPatientHistory);


              // databaseReference.child(id).setValue(inputPatientHistory);
               // databaseReference.child("Medical History").setValue(inputPatientHistory);

                 //   databaseReference.child(user.getUid()).setValue(inputPatientHistory);
                    Toast.makeText(getContext(), "Info Saved...", Toast.LENGTH_LONG).show();

                }

        });




        DatabaseReference d = FirebaseDatabase.getInstance().getReference().child("PATIENT").child(USERID).child("PATIENT HISTORY");
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                if(dataSnapshot.exists()){
                    Map <String, String> map = (Map)dataSnapshot.getValue(genericTypeIndicator);
                    Name = map.get("name");
                    Age=map.get("age");
                    Weight=map.get("weight");
                    Condition=map.get("condition");
                    Allergy=map.get("allergy");
                    EditTextname.setText(Name);
                    EditTextage.setText(Age);
                    EditTextweight.setText(Weight);
                    EditTextcondition.setText(Condition);
                    EditTextallergy.setText(Allergy);



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return v;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



   //   Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();

        //you can set the title for your toolbar here for different fragments different titles
     //   getActivity().setTitle("Menu 1");



    }


}
