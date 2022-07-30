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
public class SendEvent implements Serializable {
    
    protected String message;

    public SendEvent(String text) {
        message = text;
    }
    
    public String getMessage() {
        return message;
    }
    
}
