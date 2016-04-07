package io.github.hkust1516csefyp43.ehr.pojo.patient_visit;

import java.io.Serializable;

/**
 * Created by Louis on 8/4/16.
 */
public class Document implements Serializable {
  private String contentInHtml;

  public Document() {
    //empty constructor
  }

  public String getContentInHtml() {
    return contentInHtml;
  }

  public void setContentInHtml(String contentInHtml) {
    this.contentInHtml = contentInHtml;
  }

  @Override
  public String toString() {
    return "Document{" +
        "contentInHtml='" + contentInHtml + '\'' +
        '}';
  }
}
