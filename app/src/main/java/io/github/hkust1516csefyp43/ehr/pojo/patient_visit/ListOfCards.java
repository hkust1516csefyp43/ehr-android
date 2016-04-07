package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Louis on 8/4/16.
 */
public class ListOfCards implements Serializable {
  private ArrayList<Card> cardArrayList;

  public ListOfCards() {
    //empty constructor
  }

  public ArrayList<Card> getCardArrayList() {
    return cardArrayList;
  }

  public void setCardArrayList(ArrayList<Card> cardArrayList) {
    this.cardArrayList = cardArrayList;
  }

  @Override
  public String toString() {
    return "ListOfCards{" +
        "cardArrayList=" + cardArrayList +
        '}';
  }
}
