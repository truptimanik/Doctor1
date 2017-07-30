package com.example.asus.doctor;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by ASUS on 19-07-2017.
 */

public class AdapterPill extends ArrayAdapter<inputPill>  {

    private Context context;
    private int id;
    String uid;
    String pilln;
    DatabaseReference  databaseReference;

    ArrayList<inputPill> array;

    public AdapterPill(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<inputPill> objects,String s,String p) {
        super(context,resource,objects);
        uid = s;
        pilln=p;
        //super(context, resource, objects);
        this.context=context;
        this.id=resource;
        this.array=objects;
    }



    public class ViewHolder {
        TextView pillname,schedule,noon,eve;
        Switch delete;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        inputPill i = getItem(position);
        if(convertView==null){

         //   Toast.makeText(context, "Adapter pill", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customlistviewpill,parent,false);
            holder.pillname = (TextView)convertView.findViewById(R.id.txt_PillName);
            holder.schedule = (TextView)convertView.findViewById(R.id.txt_M_N_E);
            holder.noon=(TextView)convertView.findViewById(R.id.txt_N);
            holder.eve=(TextView)convertView.findViewById(R.id.txt_E);
            holder.delete = (Switch)convertView.findViewById(R.id.switch_Delete);


         /*   holder.delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true){


                  //     databaseReference = FirebaseDatabase.getInstance().getReference().child("PATIENT").child(uid).child("PILL").child(pilln);
                      //  databaseReference.removeValue();

                        Toast.makeText(getContext(),"DELETE! ",Toast.LENGTH_SHORT).show();
                    }
                }
            }); */

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }


      //  databaseReference = FirebaseDatabase.getInstance().getReference().child("PATIENT").child(userid).child("PILL");


        holder.pillname.setText(i.getPillName());
        holder.schedule.setText(i.getMorning());
        holder.noon.setText(i.getNoon());
        holder.eve.setText(i.getEve());






            return convertView;
       // return super.getView(position, convertView, parent);
    }
}
