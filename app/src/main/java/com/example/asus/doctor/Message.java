package com.example.asus.doctor;

/**
 * Created by ASUS on 23-07-2017.
 */
import java.util.Date;
public class Message {
    private String mText;
    private String mSender;
    private Date mDate;
    private Long mTime;

    public Message(String mText, String mSender, Date mDate) {
        this.mText = mText;
        this.mSender = mSender;
        this.mDate = mDate;
        mTime = new Date().getTime();
    }

    public Long getmTime() {
        return mTime;
    }

    public void setmTime(Long mTime) {
        this.mTime = mTime;
    }

    public Message()

    {

    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }




}
