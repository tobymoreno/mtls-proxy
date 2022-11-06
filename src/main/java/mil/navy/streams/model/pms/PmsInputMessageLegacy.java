package mil.navy.streams.model.pms;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ActivityCode",
    "Workcenter",
    "SerialNumber",
    "Type",
    "Title",
    "Urgent",
    "UrgentDatetime",
    "DateOut",
    "EquipmentNomenclature",
    "EquipmentSerialNumber",
    "EquipmentLocation",
    "EquipmentRIN",
    "EquipmentAPL",
    "EquipmentIdentifier",
    "EquipmentESWBS",
    "MIP",
    "MRC",
    "Remarks"
})
@Generated("jsonschema2pojo")
public class PmsInputMessageLegacy {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ActivityCode")
    private String activityCode;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Workcenter")
    private String workcenter;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("SerialNumber")
    private String serialNumber;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Type")
    private String type;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Title")
    private String title;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Urgent")
    private String urgent;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("UrgentDatetime")
    private Integer urgentDatetime;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("DateOut")
    private Integer dateOut;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentNomenclature")
    private String equipmentnomenclature;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentSerialNumber")
    private String equipmentSerialNumber;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentLocation")
    private String equipmentLocation;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentRIN")
    private String equipmentRIN;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentAPL")
    private String equipmentAPL;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentIdentifier")
    private String equipmentIdentifier;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentESWBS")
    private String equipmentESWBS;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MIP")
    private String mip;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MRC")
    private String mrc;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Remarks")
    private String remarks;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ActivityCode")
    public String getActivityCode() {
        return activityCode;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("ActivityCode")
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Workcenter")
    public String getWorkcenter() {
        return workcenter;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Workcenter")
    public void setWorkcenter(String workcenter) {
        this.workcenter = workcenter;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("SerialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("SerialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Urgent")
    public String getUrgent() {
        return urgent;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Urgent")
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("UrgentDatetime")
    public Integer getUrgentDatetime() {
        return urgentDatetime;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("UrgentDatetime")
    public void setUrgentDatetime(Integer urgentDatetime) {
        this.urgentDatetime = urgentDatetime;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("DateOut")
    public Integer getDateOut() {
        return dateOut;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("DateOut")
    public void setDateOut(Integer dateOut) {
        this.dateOut = dateOut;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentNomenclature")
    public String getEquipmentnomenclature() {
        return equipmentnomenclature;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentNomenclature")
    public void setEquipmentnomenclature(String equipmentnomenclature) {
        this.equipmentnomenclature = equipmentnomenclature;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentSerialNumber")
    public String getEquipmentSerialNumber() {
        return equipmentSerialNumber;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentSerialNumber")
    public void setEquipmentSerialNumber(String equipmentSerialNumber) {
        this.equipmentSerialNumber = equipmentSerialNumber;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentLocation")
    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentLocation")
    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentRIN")
    public String getEquipmentRIN() {
        return equipmentRIN;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentRIN")
    public void setEquipmentRIN(String equipmentRIN) {
        this.equipmentRIN = equipmentRIN;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentAPL")
    public String getEquipmentAPL() {
        return equipmentAPL;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentAPL")
    public void setEquipmentAPL(String equipmentAPL) {
        this.equipmentAPL = equipmentAPL;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentIdentifier")
    public String getEquipmentIdentifier() {
        return equipmentIdentifier;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentIdentifier")
    public void setEquipmentIdentifier(String equipmentIdentifier) {
        this.equipmentIdentifier = equipmentIdentifier;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentESWBS")
    public String getEquipmentESWBS() {
        return equipmentESWBS;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("EquipmentESWBS")
    public void setEquipmentESWBS(String equipmentESWBS) {
        this.equipmentESWBS = equipmentESWBS;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MIP")
    public String getMip() {
        return mip;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MIP")
    public void setMip(String mip) {
        this.mip = mip;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MRC")
    public String getMrc() {
        return mrc;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("MRC")
    public void setMrc(String mrc) {
        this.mrc = mrc;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Remarks")
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("Remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
