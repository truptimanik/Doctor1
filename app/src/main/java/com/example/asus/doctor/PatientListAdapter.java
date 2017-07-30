package com.example.asus.doctor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 22-07-2017.
 */

public class PatientListAdapter extends ArrayAdapter<PatName> {
    private Activity context;
    private List<PatName> patnm;
    public PatientListAdapter(Activity context,List<PatName> patnm){

       super(context,R.layout.custom_patient_list,patnm);
        this.context=context;
        this.patnm = patnm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemPat;


       listViewItemPat= inflater.inflate(R.layout.custom_patient_list,null,true);
        TextView textviewPatName = (TextView)listViewItemPat.findViewById(R.id.patientname);
        PatName patName = patnm.get(position);
        textviewPatName.setText(patName.getType());
        return  listViewItemPat;
    }
}
