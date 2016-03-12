package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 1/2/16.
 */
public class Card implements Serializable, Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
    @SerializedName("cardTitle")
    private String cardTitle;
    @SerializedName("cardDescription")
    private String cardDescription;

    public Card(String name, @Nullable String description) {
        this.cardTitle = name;
        this.cardDescription = description;
    }

    protected Card(Parcel in) {
        cardTitle = in.readString();
        cardDescription = in.readString();
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardTitle);
        dest.writeString(cardDescription);
    }
}