package io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Louis on 8/4/16.
 */
public class ListOfCards implements Serializable {
  private static final long serialVersionUID = 1L;

  private ArrayList<Card> cardArrayList;

  public ListOfCards() {
    //empty constructor
  }

  public ListOfCards(ArrayList<Card> cardArrayList) {
    this.cardArrayList = cardArrayList;
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
        "cardArrayList=" + cardArrayList.toString() +
        '}';
  }
}
