package com.example.asus.doctor;

/**
 * Created by ASUS on 16-07-2017.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu2 extends Fragment{


    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    private Button Add;
    private EditText editTextPillname;
    List<PatName> pillList;
    private ListView lv_List;
    private ArrayList<inputPill> array;
  //  private ArrayAdapter<inputPill> adapter;
    private AdapterPill adapter;
    View v;
    String M;
    String N;
    String E;
    private  String pillName;



    EditText EditTextpill;
    CheckBox mor,noon,eve;
    String inter;
    String USERID;
    Switch del;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        final String userid=bundle.getString("UID"); //getArguments().getString("UID");
     //   Toast.makeText(getContext(), "User id retrived"+ userid, Toast.LENGTH_LONG).show();

        inter = userid.replace('.','-');
        USERID = inter.replace('@','-');


    //    Toast.makeText(getContext(), "User id changed"+ USERID, Toast.LENGTH_LONG).show();


        String PillName;
        v = inflater.inflate(R.layout.fragment_menu_2,container,false);

        EditTextpill=(EditText)v.findViewById(R.id.editTextPillName);
         mor=(CheckBox)v.findViewById(R.id.checkBoxMorning);
        noon=(CheckBox)v.findViewById(R.id.checkBoxNoon);
         eve=(CheckBox)v.findViewById(R.id.checkBoxEve);
        Add=(Button) v.findViewById(R.id.ButtonAddPill);
        del = (Switch)v.findViewById(R.id.switch_Delete);
        lv_List=(ListView)v.findViewById(R.id.ListViewPill);


        array = new ArrayList<inputPill>();

        adapter = new AdapterPill(v.getContext(),R.layout.customlistviewpill,array,USERID,pillName);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        databaseReference.child("PATIENT").child(USERID).child("PILL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 array.clear();
                for(DataSnapshot pillSnapshot:dataSnapshot.getChildren()){

                    inputPill ip =pillSnapshot.getValue(inputPill.class);


                    array.add(ip);
                }
                lv_List.setAdapter(adapter);
                inputPill ip1 = new inputPill();
                String p =ip1.getPillName();
                adapter = new AdapterPill(getContext(),R.layout.customlistviewpill,array,USERID,p);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPill();





            }
        });





      //  adapter = new AdapterPill(this,R.layout.customlistviewpill,array);
        return v;

    }


    public void AddPill()
    {
        if(EditTextpill.getText().toString().equals(""))
        {
            AlertDialog.Builder builder= new AlertDialog.Builder(v.getContext());
            builder.setTitle("Message");
            builder.setMessage("Please enter Valid data....");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();


        }
        else
        {

          //  Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();
            pillName=EditTextpill.getText().toString().trim();
            boolean morning = mor.isChecked();

            if(morning==true){
                 M = "M";
            }
            else {
                M="_";
            }

            boolean afNoon = noon.isChecked();
          if(afNoon==true){
                N="N";
            }
            else {
                N="_";
            }

            boolean evening = eve.isChecked();
            if(evening==true)
            {
                E="E";
            }
            else
            {
                E="_";
            }

       //    inputPill pill = new inputPill(pillName,morning,afNoon,evening);
            inputPill pill = new inputPill(pillName,M,N,E);


//            FirebaseUser user = auth.getCurrentUser();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            String id = databaseReference.push().getKey();
            //databaseReference.child(user.getUid()).setValue(pill);
          //  databaseReference.child(user.getUid()).child("Pill").child(id).setValue(pill);
            databaseReference.child("PATIENT").child(USERID).child("PILL").child(pillName).setValue(pill);


            adapter.notifyDataSetChanged();
            EditTextpill.setText("");
            mor.isChecked();
            noon.isChecked();
            eve.isChecked();
            EditTextpill.requestFocus();





        }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    //  Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();





    }


}
