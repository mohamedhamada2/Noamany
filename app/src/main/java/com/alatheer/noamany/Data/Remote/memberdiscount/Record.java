package com.alatheer.noamany.Data.Remote.memberdiscount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("emp_id_fk")
    @Expose
    private String empIdFk;
    @SerializedName("member_code")
    @Expose
    private String memberCode;
    @SerializedName("code_id_fk")
    @Expose
    private String codeIdFk;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("ended_date_ar")
    @Expose
    private String endedDateAr;
    @SerializedName("ended_date")
    @Expose
    private String endedDate;
    @SerializedName("approved")
    @Expose
    private String approved;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("nesba")
    @Expose
    private String nesba;
    @SerializedName("m_name")
    @Expose
    private String mName;
    @SerializedName("employee")
    @Expose
    private String employee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpIdFk() {
        return empIdFk;
    }

    public void setEmpIdFk(String empIdFk) {
        this.empIdFk = empIdFk;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getCodeIdFk() {
        return codeIdFk;
    }

    public void setCodeIdFk(String codeIdFk) {
        this.codeIdFk = codeIdFk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEndedDateAr() {
        return endedDateAr;
    }

    public void setEndedDateAr(String endedDateAr) {
        this.endedDateAr = endedDateAr;
    }

    public String getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(String endedDate) {
        this.endedDate = endedDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNesba() {
        return nesba;
    }

    public void setNesba(String nesba) {
        this.nesba = nesba;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
