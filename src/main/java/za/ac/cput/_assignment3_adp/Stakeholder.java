
package za.ac.cput._assignment3_adp;

import java.io.Serializable;

/**
 *
 * @author Breyton Ernstzen (217203027)
 */
public class Stakeholder implements Serializable{
     private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }

}

