/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.streams.model.pms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"MessageId",
"MessageType",
"MessageDate",
"ConfigurationDetailInput"
})
@Generated("jsonschema2pojo")
public class ConfigurationInput {

@JsonProperty("MessageId")
private String messageId;
@JsonProperty("MessageType")
private String messageType;
@JsonProperty("MessageDate")
private String messageDate;
@JsonProperty("ConfigurationDetailInput")
private List<ConfigurationDetailInput> configurationDetailInput = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("MessageId")
public String getMessageId() {
return messageId;
}

@JsonProperty("MessageId")
public void setMessageId(String messageId) {
this.messageId = messageId;
}

@JsonProperty("MessageType")
public String getMessageType() {
return messageType;
}

@JsonProperty("MessageType")
public void setMessageType(String messageType) {
this.messageType = messageType;
}

@JsonProperty("MessageDate")
public String getMessageDate() {
return messageDate;
}

@JsonProperty("MessageDate")
public void setMessageDate(String messageDate) {
this.messageDate = messageDate;
}

@JsonProperty("ConfigurationDetailInput")
public List<ConfigurationDetailInput> getConfigurationDetailInput() {
return configurationDetailInput;
}

@JsonProperty("ConfigurationDetailInput")
public void setConfigurationDetailInput(List<ConfigurationDetailInput> configurationDetailInput) {
this.configurationDetailInput = configurationDetailInput;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}