package com.xmhzj.xpForwardSmsMini.common.action.entity;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsMessage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xmhzj.xpForwardSmsMini.common.utils.SmsMessageUtils;

import java.text.Normalizer;
import java.util.Objects;

public class SmsMsg implements Parcelable {

    @SerializedName("id")
    private Long id;

    // Sender
    @Expose
    @SerializedName("sender")
    private String sender;

    // Message content
    @Expose
    @SerializedName("body")
    private String body;

    // Receive date
    @Expose
    @SerializedName("date")
    private long date;

    // subId
    @Expose
    @SerializedName("subId")
    private int subId;

    public static SmsMsg fromIntent(Intent intent) {
        SmsMessage[] smsMessageParts = SmsMessageUtils.fromIntent(intent);

        String sender = smsMessageParts[0].getDisplayOriginatingAddress();
        String body = SmsMessageUtils.getMessageBody(smsMessageParts);
        int subId = SmsMessageUtils.getSubId(smsMessageParts[0]);

        sender = Normalizer.normalize(sender, Normalizer.Form.NFC);
        body = Normalizer.normalize(body, Normalizer.Form.NFC);

        SmsMsg message = new SmsMsg();
        message.setSender(sender).setBody(body).setSubId(subId);
        return message;
    }

    public String getSender() {
        return sender;
    }

    public SmsMsg setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getBody() {
        return body;
    }

    public SmsMsg setBody(String body) {
        this.body = body;
        return this;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getSubId() {
        return subId;
    }

    public SmsMsg setSubId(int subId) {
        this.subId = subId;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private SmsMsg(Parcel source) {
        if (source.readByte() == 0) {
            id = null;
        } else {
            id = source.readLong();
        }
        sender = source.readString();
        body = source.readString();
        date = source.readLong();
    }

    public SmsMsg() {
    }

    public SmsMsg(Long id, String sender, String body, long date, int subId) {
        this.id = id;
        this.sender = sender;
        this.body = body;
        this.date = date;
        this.subId = subId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(sender);
        dest.writeString(body);
        dest.writeLong(date);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static final Parcelable.Creator<SmsMsg> CREATOR = new Parcelable.Creator<SmsMsg>() {

        @Override
        public SmsMsg createFromParcel(Parcel source) {
            return new SmsMsg(source);
        }

        @Override
        public SmsMsg[] newArray(int size) {
            return new SmsMsg[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SmsMsg))
            return false;
        SmsMsg smsMsg = (SmsMsg) o;
        return id.equals(smsMsg.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, body, date);
    }

    @Override
    public String toString() {
        return "SmsMsg{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
