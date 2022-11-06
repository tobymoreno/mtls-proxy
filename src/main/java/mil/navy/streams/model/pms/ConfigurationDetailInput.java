package mil.navy.streams.model.pms;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ActivityCode",
    "WorkCenter",
    "FBRSerialNumber",
    "FeedbackType",
    "FeedbackTitle",
    "Urgent",
    "UrgentDateTime",
    "DateOut",
    "EquipmentNomenclature",
    "EquipmentSerialNum",
    "EquipmentLocation",
    "EquipmentRIN",
    "EquipmentAPL",
    "EquipmentESWBS",
    "MIP",
    "MRC",
    "Remarks",
    "3MCSignature",
    "3MCEmail",
    "OriginatorSignature",
    "OriginatorEmail"
})
@Generated("jsonschema2pojo")
public class ConfigurationDetailInput {


    @JsonProperty("ActivityCode")
    @HeaderElement("Activity Code")
    private String activityCode;
    @JsonProperty("WorkCenter")
    private String workCenter;
    @JsonProperty("FBRSerialNumber")
    private String fBRSerialNumber;
    @JsonProperty("FeedbackType")
    private String feedbackType;
    @JsonProperty("FeedbackTitle")
    private String feedbackTitle;
    @JsonProperty("Urgent")
    private String urgent;
    @JsonProperty("UrgentDateTime")
    private String urgentDateTime;
    @JsonProperty("DateOut")
    private String dateOut;
    @JsonProperty("EquipmentNomenclature")
    private String equipmentNomenclature;
    @JsonProperty("EquipmentSerialNum")
    private String equipmentSerialNum;
    @JsonProperty("EquipmentLocation")
    private String equipmentLocation;
    @JsonProperty("EquipmentRIN")
    private String equipmentRIN;
    @JsonProperty("EquipmentAPL")
    private String equipmentAPL;
    @JsonProperty("EquipmentESWBS")
    private String equipmentESWBS;
    @JsonProperty("MIP")
    private String mip;
    @JsonProperty("MRC")
    private String mrc;
    @JsonProperty("Remarks")
    private String remarks;
    @JsonProperty("3MCSignature")
    private String _3MCSignature;
    @JsonProperty("3MCEmail")
    private String _3MCEmail;
    @JsonProperty("OriginatorSignature")
    private String originatorSignature;
    @JsonProperty("OriginatorEmail")
    private String originatorEmail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ActivityCode")
    @HeaderElement(value = "Activity Code")    
    public String getActivityCode() {
        return activityCode;
    }

    @JsonProperty("ActivityCode")
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    @JsonProperty("WorkCenter")
    @HeaderElement(value = "Work Center")    
    public String getWorkCenter() {
        return workCenter;
    }

    @JsonProperty("WorkCenter")
    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    @JsonProperty("FBRSerialNumber")
    @HeaderElement(value = "FBR SerialNumber")   
    public String getFBRSerialNumber() {
        return fBRSerialNumber;
    }

    @JsonProperty("FBRSerialNumber")
    public void setFBRSerialNumber(String fBRSerialNumber) {
        this.fBRSerialNumber = fBRSerialNumber;
    }

    @JsonProperty("FeedbackType")
    @HeaderElement(value = "Feedback Type")   
    public String getFeedbackType() {
        return feedbackType;
    }

    @JsonProperty("FeedbackType")
    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    @JsonProperty("FeedbackTitle")
    @HeaderElement(value = "Feedback Title")   
    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    @JsonProperty("FeedbackTitle")
    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    @JsonProperty("Urgent")
    @HeaderElement(value = "Urgent")   
    public String getUrgent() {
        return urgent;
    }

    @JsonProperty("Urgent")
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    @JsonProperty("UrgentDateTime")
    @HeaderElement(value = "Urgent Datetime")   
    public String getUrgentDateTime() {
        return urgentDateTime;
    }

    @JsonProperty("UrgentDateTime")
    public void setUrgentDateTime(String urgentDateTime) {
        this.urgentDateTime = urgentDateTime;
    }

    @JsonProperty("DateOut")
    @HeaderElement(value = "Date Out")   
    public String getDateOut() {
        return dateOut;
    }

    @JsonProperty("DateOut")
    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    @JsonProperty("EquipmentNomenclature")
    @HeaderElement(value = "Equipment Nomenclature")   
    public String getEquipmentNomenclature() {
        return equipmentNomenclature;
    }

    @JsonProperty("EquipmentNomenclature")
    public void setEquipmentNomenclature(String equipmentNomenclature) {
        this.equipmentNomenclature = equipmentNomenclature;
    }

    @JsonProperty("EquipmentSerialNum")
    @HeaderElement(value = "Equipment SerialNum")   
    public String getEquipmentSerialNum() {
        return equipmentSerialNum;
    }

    @JsonProperty("EquipmentSerialNum")
    public void setEquipmentSerialNum(String equipmentSerialNum) {
        this.equipmentSerialNum = equipmentSerialNum;
    }

    @JsonProperty("EquipmentLocation")
    @HeaderElement(value = "Equipment Location")   
    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    @JsonProperty("EquipmentLocation")
    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }

    @JsonProperty("EquipmentRIN")
    @HeaderElement(value = "Equipment RIN")   
    public String getEquipmentRIN() {
        return equipmentRIN;
    }

    @JsonProperty("EquipmentRIN")
    public void setEquipmentRIN(String equipmentRIN) {
        this.equipmentRIN = equipmentRIN;
    }

    @JsonProperty("EquipmentAPL")
    @HeaderElement(value = "Equipment APL")   
    public String getEquipmentAPL() {
        return equipmentAPL;
    }

    @JsonProperty("EquipmentAPL")
    public void setEquipmentAPL(String equipmentAPL) {
        this.equipmentAPL = equipmentAPL;
    }

    @JsonProperty("EquipmentESWBS")
    @HeaderElement(value = "Equipment ESWBS")  
    public String getEquipmentESWBS() {
        return equipmentESWBS;
    }

    @JsonProperty("EquipmentESWBS")
    public void setEquipmentESWBS(String equipmentESWBS) {
        this.equipmentESWBS = equipmentESWBS;
    }

    @JsonProperty("MIP")
    @HeaderElement(value = "MIP")   
    public String getMIP() {
        return mip;
    }

    @JsonProperty("MIP")
    public void setMIP(String mip) {
        this.mip = mip;
    }

    @JsonProperty("MRC")
    @HeaderElement(value = "MRC")   
    public String getMRC() {
        return mrc;
    }

    @JsonProperty("MRC")
    public void setMRC(String mrc) {
        this.mrc = mrc;
    }

    @JsonProperty("Remarks")
    @HeaderElement(value = "Remarks")   
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("Remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("3MCSignature")
    @HeaderElement(value = "3MC Signature")   
    public String get3MCSignature() {
        return _3MCSignature;
    }

    @JsonProperty("3MCSignature")
    public void set3MCSignature(String _3MCSignature) {
        this._3MCSignature = _3MCSignature;
    }

    @JsonProperty("3MCEmail")
    @HeaderElement(value = "3MC Email")   
    public String get3MCEmail() {
        return _3MCEmail;
    }

    @JsonProperty("3MCEmail")
    public void set3MCEmail(String _3MCEmail) {
        this._3MCEmail = _3MCEmail;
    }

    @JsonProperty("OriginatorSignature")
    @HeaderElement(value = "Originator Signature")   
    public String getOriginatorSignature() {
        return originatorSignature;
    }

    @JsonProperty("OriginatorSignature")
    public void setOriginatorSignature(String originatorSignature) {
        this.originatorSignature = originatorSignature;
    }

    @JsonProperty("OriginatorEmail")
    @HeaderElement(last = true, value = "Originator Email")
    public String getOriginatorEmail() {
        return originatorEmail;
    }

    @JsonProperty("OriginatorEmail")
    public void setOriginatorEmail(String originatorEmail) {
        this.originatorEmail = originatorEmail;
    }

}
