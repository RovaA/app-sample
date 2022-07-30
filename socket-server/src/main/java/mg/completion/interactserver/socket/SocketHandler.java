/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.completion.interactserver.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mg.completion.interact.socket.event.PushEvent;
import mg.completion.interact.socket.event.SendEvent;

/**
 *
 * @author rova
 */
public class SocketHandler implements Runnable {

    protected IsView view;
    protected ServerSocket serverSocket;
    protected List<Socket> sockets = new ArrayList<>();

    public SocketHandler(IsView view) {
        this.view = view;
    }

    public void run(int port) {
        try {
            serverSocket = new ServerSocket(port);
            new Thread(this).start();
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(PushEvent pushEvent) {
        sockets.forEach((socket) -> {
            try {
                final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(pushEvent);
                output.flush();
            } catch (IOException ex) {
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                final SocketListener socketListener = new SocketListener(socket);
                socketListener.setParent(SocketHandler.this);
                new Thread(socketListener).start();
                sockets.add(socket);
            } catch (IOException ex) {
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void receive(SendEvent event) {
        view.receive(event.getMessage());
        sockets.forEach((socket) -> {
            try {
                final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(new PushEvent(event.getMessage()));
                output.flush();
            } catch (IOException ex) {
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
