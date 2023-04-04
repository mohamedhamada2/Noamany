package com.alatheer.noamany.Data.Remote.MemberVedio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberVedio {


  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("rkm_fk")
  @Expose
  private String rkmFk;
  @SerializedName("from_user_id")
  @Expose
  private String fromUserId;
  @SerializedName("to_member_code")
  @Expose
  private String toMemberCode;
  @SerializedName("video")
  @Expose
  private String video;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("v_image")
  @Expose
  private String vImage;
  @SerializedName("m_name")
  @Expose
  private String mName;
  @SerializedName("m_card")
  @Expose
  private String mCard;
  @SerializedName("m_tel")
  @Expose
  private String mTel;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRkmFk() {
    return rkmFk;
  }

  public void setRkmFk(String rkmFk) {
    this.rkmFk = rkmFk;
  }

  public String getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(String fromUserId) {
    this.fromUserId = fromUserId;
  }

  public String getToMemberCode() {
    return toMemberCode;
  }

  public void setToMemberCode(String toMemberCode) {
    this.toMemberCode = toMemberCode;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVImage() {
    return vImage;
  }

  public void setVImage(String vImage) {
    this.vImage = vImage;
  }

  public String getMName() {
    return mName;
  }

  public void setMName(String mName) {
    this.mName = mName;
  }

  public String getMCard() {
    return mCard;
  }

  public void setMCard(String mCard) {
    this.mCard = mCard;
  }

  public String getMTel() {
    return mTel;
  }

  public void setMTel(String mTel) {
    this.mTel = mTel;
  }

}
