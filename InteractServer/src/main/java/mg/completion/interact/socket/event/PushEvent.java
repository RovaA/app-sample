/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.completion.interact.socket.event;

import java.io.Serializable;

/**
 *
 * @author rova
 */
public class PushEvent implements Serializable {
    
    protected String message;
    
    public PushEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }    
}
