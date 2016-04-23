package io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit;

/**
 * Created by Louis on 6/4/16.
 */
public class ChiefComplain {
  private String chiefComplain;

  public ChiefComplain() {
    //empty constructor
  }

  public String getChiefComplain() {
    return chiefComplain;
  }

  public void setChiefComplain(String chiefComplain) {
    this.chiefComplain = chiefComplain;
  }

  @Override
  public String toString() {
    return "ChiefComplain{" +
        "chiefComplain='" + chiefComplain + '\'' +
        '}';
  }
}
