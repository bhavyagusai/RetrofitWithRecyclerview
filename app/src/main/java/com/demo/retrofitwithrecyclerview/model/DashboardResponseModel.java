package com.demo.retrofitwithrecyclerview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardResponseModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DashboardResponse> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DashboardResponse> getData() {
        return data;
    }

    public void setData(List<DashboardResponse> data) {
        this.data = data;
    }

    public class DashboardResponse {

        @SerializedName("cd_id")
        @Expose
        private String cdId;
        @SerializedName("cd_email")
        @Expose
        private String cdEmail;
        @SerializedName("cd_from")
        @Expose
        private String cdFrom;
        @SerializedName("cd_provider_id")
        @Expose
        private String cdProviderId;
        @SerializedName("cd_phone_number")
        @Expose
        private String cdPhoneNumber;
        @SerializedName("cd_KPI")
        @Expose
        private String cdKPI;
        @SerializedName("prv_name")
        @Expose
        private String prvName;
        @SerializedName("prv_abbr")
        @Expose
        private String prvAbbr;
        @SerializedName("prv_abbr_color")
        @Expose
        private String prvAbbrColor;
        @SerializedName("prv_kpi_limit")
        @Expose
        private String prvKpiLimit;
        @SerializedName("prv_timezone")
        @Expose
        private String prvTimezone;
        @SerializedName("responsible_person")
        @Expose
        private String responsiblePerson;
        @SerializedName("cd_reason_for_call")
        @Expose
        private String cdReasonForCall;
        @SerializedName("cd_user_id")
        @Expose
        private String cdUserId;
        @SerializedName("cd_is_overdue")
        @Expose
        private Boolean cdIsOverdue;
        @SerializedName("cd_status")
        @Expose
        private String cdStatus;
        @SerializedName("modified_by")
        @Expose
        private String modifiedBy;
        @SerializedName("cd_created_date")
        @Expose
        private String cdCreatedDate;
        @SerializedName("cd_notes_count")
        @Expose
        private Integer cdNotesCount;

        public String getCdId() {
            return cdId;
        }

        public void setCdId(String cdId) {
            this.cdId = cdId;
        }

        public String getCdEmail() {
            return cdEmail;
        }

        public void setCdEmail(String cdEmail) {
            this.cdEmail = cdEmail;
        }

        public String getCdFrom() {
            return cdFrom;
        }

        public void setCdFrom(String cdFrom) {
            this.cdFrom = cdFrom;
        }

        public String getCdProviderId() {
            return cdProviderId;
        }

        public void setCdProviderId(String cdProviderId) {
            this.cdProviderId = cdProviderId;
        }

        public String getCdPhoneNumber() {
            return cdPhoneNumber;
        }

        public void setCdPhoneNumber(String cdPhoneNumber) {
            this.cdPhoneNumber = cdPhoneNumber;
        }

        public String getCdKPI() {
            return cdKPI;
        }

        public void setCdKPI(String cdKPI) {
            this.cdKPI = cdKPI;
        }

        public String getPrvName() {
            return prvName;
        }

        public void setPrvName(String prvName) {
            this.prvName = prvName;
        }

        public String getPrvAbbr() {
            return prvAbbr;
        }

        public void setPrvAbbr(String prvAbbr) {
            this.prvAbbr = prvAbbr;
        }

        public String getPrvAbbrColor() {
            return prvAbbrColor;
        }

        public void setPrvAbbrColor(String prvAbbrColor) {
            this.prvAbbrColor = prvAbbrColor;
        }

        public String getPrvKpiLimit() {
            return prvKpiLimit;
        }

        public void setPrvKpiLimit(String prvKpiLimit) {
            this.prvKpiLimit = prvKpiLimit;
        }

        public String getPrvTimezone() {
            return prvTimezone;
        }

        public void setPrvTimezone(String prvTimezone) {
            this.prvTimezone = prvTimezone;
        }

        public String getResponsiblePerson() {
            return responsiblePerson;
        }

        public void setResponsiblePerson(String responsiblePerson) {
            this.responsiblePerson = responsiblePerson;
        }

        public String getCdReasonForCall() {
            return cdReasonForCall;
        }

        public void setCdReasonForCall(String cdReasonForCall) {
            this.cdReasonForCall = cdReasonForCall;
        }

        public String getCdUserId() {
            return cdUserId;
        }

        public void setCdUserId(String cdUserId) {
            this.cdUserId = cdUserId;
        }

        public Boolean getCdIsOverdue() {
            return cdIsOverdue;
        }

        public void setCdIsOverdue(Boolean cdIsOverdue) {
            this.cdIsOverdue = cdIsOverdue;
        }

        public String getCdStatus() {
            return cdStatus;
        }

        public void setCdStatus(String cdStatus) {
            this.cdStatus = cdStatus;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getCdCreatedDate() {
            return cdCreatedDate;
        }

        public void setCdCreatedDate(String cdCreatedDate) {
            this.cdCreatedDate = cdCreatedDate;
        }

        public Integer getCdNotesCount() {
            return cdNotesCount;
        }

        public void setCdNotesCount(Integer cdNotesCount) {
            this.cdNotesCount = cdNotesCount;
        }
    }

}