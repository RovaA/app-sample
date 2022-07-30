/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.completion.interactserver.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mg.completion.interact.socket.event.SendEvent;

/**
 *
 * @author rova
 */
public class SocketListener implements Runnable {

    protected Socket socket;
    protected SocketHandler socketHandler;

    public SocketListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                final SendEvent event = (SendEvent) input.readObject();
                if (event == null) {
                    return;
                }
                socketHandler.receive(event);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setParent(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

}
