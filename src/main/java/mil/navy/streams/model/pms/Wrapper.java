/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.streams.model.pms;

import java.util.List;

/**
 *
 * @author tmoreno
 */
public class Wrapper {
    private List<Records> records;

    /**
     * @return the records
     */
    public List<Records> getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(List<Records> records) {
        this.records = records;
    }

}
