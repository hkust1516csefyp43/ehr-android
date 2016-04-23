package io.github.hkust1516csefyp43.easymed.pojo.patient_visit_edit;


import java.io.Serializable;

/**
 * Created by Louis on 6/4/16.
 */
public class Remark implements Serializable {
  private String remark;

  public Remark() {
    //empty constructor
  }

  public Remark(String remark) {
    this.remark = remark;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "Remark{" +
        "remark='" + remark + '\'' +
        '}';
  }
}
