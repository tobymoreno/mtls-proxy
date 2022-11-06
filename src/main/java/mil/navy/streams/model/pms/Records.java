/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.streams.model.pms;




/**
 *
 * @author tmoreno
 */
public class Records {

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }


    private String key;
    private PmsInputMessage value;

    /**
     * @return the value
     */
    public PmsInputMessage getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(PmsInputMessage value) {
        this.value = value;
    }

}
