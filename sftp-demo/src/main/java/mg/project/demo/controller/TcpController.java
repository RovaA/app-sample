package mg.project.demo.controller;

import mg.project.demo.tcp.MessageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TcpController {

    @Autowired
    protected MessageGateway messageGateway;

    @RequestMapping(path = "message/{message}")
    public void sendMessage(@PathVariable() String message) {
        messageGateway.send(("CONNECT").getBytes());
        byte[] response = messageGateway.send((message).getBytes());
        messageGateway.send(("DISCONNECT").getBytes());
        System.out.println("RESPONSE : " + new String(response));
    }

}
