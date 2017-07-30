package com.example.asus.doctor;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

/**
 * Created by ASUS on 23-07-2017.
 */

public class MessageDataSource {
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddmmss");
    private static final String TAG = "MessageDataSource";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_SENDER = "sender";
    private static final String COLUMN_TIME = "Time";
    private static DatabaseReference databaseReference;
    private FirebaseAuth auth;
    static String u;
    public static void saveMessage(Message message, String convoId,String usr){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Date date = message.getDate();
        String key = sDateFormat.format(date);
        HashMap<String, String> msg = new HashMap<>();
        msg.put(COLUMN_TEXT, message.getText());
        msg.put(COLUMN_SENDER,"Doc");
      //  msg.put(COLUMN_TIME,message.getmTime().toString());
         convoId = "Chat";
        Chat_Activity c = new Chat_Activity();
        String i = c.pat;
    u=usr;
      //  databaseReference.child("Chat").child(key).setValue(msg);
        databaseReference.child("Chat").child(usr).child(key).setValue(msg);

    }

    public static MessagesListener addMessagesListener(String convoId, final MessagesCallbacks callbacks){
        MessagesListener listener = new MessagesListener(callbacks);
     //   databaseReference.addChildEventListener(listener);
       // convoId = "Chat";
        databaseReference = FirebaseDatabase.getInstance().getReference();
           databaseReference.child("Chat").child(convoId).addChildEventListener(listener);
        return listener;

    }

    public static void stop(MessagesListener listener){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.removeEventListener(listener);
    }

    public static class MessagesListener implements ChildEventListener {
        private MessagesCallbacks callbacks;

        MessagesListener(MessagesCallbacks callbacks) {
            this.callbacks = callbacks;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap<String, String> msg = (HashMap) dataSnapshot.getValue();
            Message message = new Message();
            message.setSender(msg.get(COLUMN_SENDER));
            message.setText(msg.get(COLUMN_TEXT));
          //  message.setmTime(new Date().getTime());
            try {
                message.setDate(sDateFormat.parse(dataSnapshot.getKey()));
            } catch (Exception e) {
                Log.d(TAG, "Couldn't parse date" + e);
            }
            if (callbacks != null) {
                callbacks.onMessageAdded(message);
            }

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {


        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError firebaseError) {

        }
    }

    public interface MessagesCallbacks{
        public void onMessageAdded(Message message);
    }
}
