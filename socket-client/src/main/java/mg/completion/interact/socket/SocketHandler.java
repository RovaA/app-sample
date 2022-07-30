/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.completion.interact.socket;

import mg.completion.interact.socket.event.SendEvent;
import mg.completion.interact.socket.event.PushEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rova
 */
public class SocketHandler implements Runnable {

    protected IsView view;
    protected Socket socket;

    public SocketHandler(IsView view) {
        this.view = view;
    }

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            new Thread(this).start();
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(SendEvent sendEvent) {
        try {
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(sendEvent);
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                final PushEvent pushEvent = (PushEvent) input.readObject();
                if (pushEvent == null) {
                    return;
                }
                view.receive(pushEvent.getMessage());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
