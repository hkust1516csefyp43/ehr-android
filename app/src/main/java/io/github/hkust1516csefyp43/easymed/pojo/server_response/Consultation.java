package io.github.hkust1516csefyp43.easymed.pojo.server_response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis on 20/4/16.
 */
public class Consultation implements Serializable{
  @SerializedName("consultation_id")                String id;
  @SerializedName("user_id")                  String userId;
  @SerializedName("visit_id")                 String visitId;
  @SerializedName("triage_id")                String triageId;
  @SerializedName("start_timestamp")          Date startTime;
  @SerializedName("end_timestamp")            Date endTime;
  @SerializedName("pe_cardio")                Boolean peCardio;
  @SerializedName("pe_ent")                   Boolean peEnt;
  @SerializedName("pe_gastro")                Boolean peGastro;
  @SerializedName("pe_general")               Boolean peGeneral;
  @SerializedName("pe_genital")               Boolean peGenital;
  @SerializedName("pe_other")                 Boolean peOther;
  @SerializedName("pe_respiratory")           Boolean peRespiratory;
  @SerializedName("pe_skin")                  Boolean peSkin;
  @SerializedName("preg_breast_feeding")      Boolean pregBreastFeeding;
  @SerializedName("preg_contraceptive")       Boolean pregContraceptive;
  @SerializedName("preg_curr_preg")           Boolean pregCurrPreg;
  @SerializedName("preg_gestation")           Integer pregGestation;
  @SerializedName("preg_lmp")                 Date pregLmp;
  @SerializedName("preg_num_abortion")        Integer pregNumAbortion;
  @SerializedName("preg_num_live_birth")      Integer pregNumLiveBirth;
  @SerializedName("preg_num_miscarriage")     Integer pregNumMiscarriage;
  @SerializedName("preg_num_preg")            Integer pregNumPreg;
  @SerializedName("preg_num_still_birth")     Integer pregNumStillBirth;
  @SerializedName("preg_remark")              String pregRemark;
  @SerializedName("remark")                   String remark;
  @SerializedName("rf_alertness")             Boolean rfAlertness;
  @SerializedName("rf_breathing")             Boolean rfBreathing;
  @SerializedName("rf_circulation")           Boolean rfCirculation;
  @SerializedName("rf_defg")                  Boolean rfDefg;
  @SerializedName("rf_dehydration")           Boolean rfDehydration;
  @SerializedName("ros_cardio")               Boolean rosCardio;
  @SerializedName("ros_ent")                  Boolean rosEnt;
  @SerializedName("ros_ga")                   Boolean rosGa;
  @SerializedName("ros_gastro")               Boolean rosGastro;
  @SerializedName("ros_genital")              Boolean rosGenital;
  @SerializedName("ros_other")                Boolean rosOther;
  @SerializedName("ros_respi")                Boolean rosRespi;
  @SerializedName("ros_skin")                 Boolean rosSkin;

  public Consultation() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getVisitId() {
    return visitId;
  }

  public void setVisitId(String visitId) {
    this.visitId = visitId;
  }

  public String getTriageId() {
    return triageId;
  }

  public void setTriageId(String triageId) {
    this.triageId = triageId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Boolean getPeCardio() {
    return peCardio;
  }

  public void setPeCardio(Boolean peCardio) {
    this.peCardio = peCardio;
  }

  public Boolean getPeEnt() {
    return peEnt;
  }

  public void setPeEnt(Boolean peEnt) {
    this.peEnt = peEnt;
  }

  public Boolean getPeGastro() {
    return peGastro;
  }

  public void setPeGastro(Boolean peGastro) {
    this.peGastro = peGastro;
  }

  public Boolean getPeGeneral() {
    return peGeneral;
  }

  public void setPeGeneral(Boolean peGeneral) {
    this.peGeneral = peGeneral;
  }

  public Boolean getPeGenital() {
    return peGenital;
  }

  public void setPeGenital(Boolean peGenital) {
    this.peGenital = peGenital;
  }

  public Boolean getPeOther() {
    return peOther;
  }

  public void setPeOther(Boolean peOther) {
    this.peOther = peOther;
  }

  public Boolean getPeRespiratory() {
    return peRespiratory;
  }

  public void setPeRespiratory(Boolean peRespiratory) {
    this.peRespiratory = peRespiratory;
  }

  public Boolean getPeSkin() {
    return peSkin;
  }

  public void setPeSkin(Boolean peSkin) {
    this.peSkin = peSkin;
  }

  public Boolean getPregBreastFeeding() {
    return pregBreastFeeding;
  }

  public void setPregBreastFeeding(Boolean pregBreastFeeding) {
    this.pregBreastFeeding = pregBreastFeeding;
  }

  public Boolean getPregContraceptive() {
    return pregContraceptive;
  }

  public void setPregContraceptive(Boolean pregContraceptive) {
    this.pregContraceptive = pregContraceptive;
  }

  public Boolean getPregCurrPreg() {
    return pregCurrPreg;
  }

  public void setPregCurrPreg(Boolean pregCurrPreg) {
    this.pregCurrPreg = pregCurrPreg;
  }

  public Integer getPregGestation() {
    return pregGestation;
  }

  public void setPregGestation(Integer pregGestation) {
    this.pregGestation = pregGestation;
  }

  public Date getPregLmp() {
    return pregLmp;
  }

  public void setPregLmp(Date pregLmp) {
    this.pregLmp = pregLmp;
  }

  public Integer getPregNumAbortion() {
    return pregNumAbortion;
  }

  public void setPregNumAbortion(Integer pregNumAbortion) {
    this.pregNumAbortion = pregNumAbortion;
  }

  public Integer getPregNumLiveBirth() {
    return pregNumLiveBirth;
  }

  public void setPregNumLiveBirth(Integer pregNumLiveBirth) {
    this.pregNumLiveBirth = pregNumLiveBirth;
  }

  public Integer getPregNumMiscarriage() {
    return pregNumMiscarriage;
  }

  public void setPregNumMiscarriage(Integer pregNumMiscarriage) {
    this.pregNumMiscarriage = pregNumMiscarriage;
  }

  public Integer getPregNumPreg() {
    return pregNumPreg;
  }

  public void setPregNumPreg(Integer pregNumPreg) {
    this.pregNumPreg = pregNumPreg;
  }

  public Integer getPregNumStillBirth() {
    return pregNumStillBirth;
  }

  public void setPregNumStillBirth(Integer pregNumStillBirth) {
    this.pregNumStillBirth = pregNumStillBirth;
  }

  public String getPregRemark() {
    return pregRemark;
  }

  public void setPregRemark(String pregRemark) {
    this.pregRemark = pregRemark;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Boolean getRfAlertness() {
    return rfAlertness;
  }

  public void setRfAlertness(Boolean rfAlertness) {
    this.rfAlertness = rfAlertness;
  }

  public Boolean getRfBreathing() {
    return rfBreathing;
  }

  public void setRfBreathing(Boolean rfBreathing) {
    this.rfBreathing = rfBreathing;
  }

  public Boolean getRfCirculation() {
    return rfCirculation;
  }

  public void setRfCirculation(Boolean rfCirculation) {
    this.rfCirculation = rfCirculation;
  }

  public Boolean getRfDefg() {
    return rfDefg;
  }

  public void setRfDefg(Boolean rfDefg) {
    this.rfDefg = rfDefg;
  }

  public Boolean getRfDehydration() {
    return rfDehydration;
  }

  public void setRfDehydration(Boolean rfDehydration) {
    this.rfDehydration = rfDehydration;
  }

  public Boolean getRosCardio() {
    return rosCardio;
  }

  public void setRosCardio(Boolean rosCardio) {
    this.rosCardio = rosCardio;
  }

  public Boolean getRosEnt() {
    return rosEnt;
  }

  public void setRosEnt(Boolean rosEnt) {
    this.rosEnt = rosEnt;
  }

  public Boolean getRosGa() {
    return rosGa;
  }

  public void setRosGa(Boolean rosGa) {
    this.rosGa = rosGa;
  }

  public Boolean getRosGastro() {
    return rosGastro;
  }

  public void setRosGastro(Boolean rosGastro) {
    this.rosGastro = rosGastro;
  }

  public Boolean getRosGenital() {
    return rosGenital;
  }

  public void setRosGenital(Boolean rosGenital) {
    this.rosGenital = rosGenital;
  }

  public Boolean getRosOther() {
    return rosOther;
  }

  public void setRosOther(Boolean rosOther) {
    this.rosOther = rosOther;
  }

  public Boolean getRosRespi() {
    return rosRespi;
  }

  public void setRosRespi(Boolean rosRespi) {
    this.rosRespi = rosRespi;
  }

  public Boolean getRosSkin() {
    return rosSkin;
  }

  public void setRosSkin(Boolean rosSkin) {
    this.rosSkin = rosSkin;
  }

  @Override
  public String toString() {
    return "Consultation{" +
        "id='" + id + '\'' +
        ", userId='" + userId + '\'' +
        ", visitId='" + visitId + '\'' +
        ", triageId='" + triageId + '\'' +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", peCardio=" + peCardio +
        ", peEnt=" + peEnt +
        ", peGastro=" + peGastro +
        ", peGeneral=" + peGeneral +
        ", peGenital=" + peGenital +
        ", peOther=" + peOther +
        ", peRespiratory=" + peRespiratory +
        ", peSkin=" + peSkin +
        ", pregBreastFeeding=" + pregBreastFeeding +
        ", pregContraceptive=" + pregContraceptive +
        ", pregCurrPreg=" + pregCurrPreg +
        ", pregGestation=" + pregGestation +
        ", pregLmp=" + pregLmp +
        ", pregNumAbortion=" + pregNumAbortion +
        ", pregNumLiveBirth=" + pregNumLiveBirth +
        ", pregNumMiscarriage=" + pregNumMiscarriage +
        ", pregNumPreg=" + pregNumPreg +
        ", pregNumStillBirth=" + pregNumStillBirth +
        ", pregRemark='" + pregRemark + '\'' +
        ", remark='" + remark + '\'' +
        ", rfAlertness=" + rfAlertness +
        ", rfBreathing=" + rfBreathing +
        ", rfCirculation=" + rfCirculation +
        ", rfDefg=" + rfDefg +
        ", rfDehydration=" + rfDehydration +
        ", rosCardio=" + rosCardio +
        ", rosEnt=" + rosEnt +
        ", rosGa=" + rosGa +
        ", rosGastro=" + rosGastro +
        ", rosGenital=" + rosGenital +
        ", rosOther=" + rosOther +
        ", rosRespi=" + rosRespi +
        ", rosSkin=" + rosSkin +
        '}';
  }
}

