package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Louis on 1/2/16.
 */
public class Card implements Serializable {
    @SerializedName("cardTitle")
    private String cardTitle;
    @SerializedName("cardDescription")
    private String cardDescription;

    public Card(String medicineName, String medicalDescription) {
        this.cardTitle = medicineName;
        this.cardDescription = medicalDescription;
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
}
