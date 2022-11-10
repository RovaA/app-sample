package mg.project.demo.tcp;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface MessageGateway {

    byte[] send(byte[] message);

}
