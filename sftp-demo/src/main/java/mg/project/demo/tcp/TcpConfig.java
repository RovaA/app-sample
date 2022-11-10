package mg.project.demo.tcp;

import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.CachingClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayElasticRawDeserializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLfSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@EnableIntegration
@IntegrationComponentScan(basePackages = {"mg.project.demo.tcp", "mg.project.demo.sftp" })
@Configuration
public class TcpConfig {

    @Bean
    public AbstractClientConnectionFactory tcpNioClientConnectionFactory() {
        TcpNetClientConnectionFactory client = new TcpNetClientConnectionFactory("localhost", 8888);
        client.setSingleUse(false);
        client.setSoKeepAlive(true);
        client.setSerializer(new ByteArrayLfSerializer());
        client.setDeserializer(new ByteArrayLfSerializer());
        return client;
    }

    /*
    @Bean
    public CachingClientConnectionFactory cachingClientConnectionFactory() {
        CachingClientConnectionFactory cachingClientConnectionFactory = new CachingClientConnectionFactory(tcpNioClientConnectionFactory(), 5);
        cachingClientConnectionFactory.setSingleUse(false);
        cachingClientConnectionFactory.setSoKeepAlive(true);
        cachingClientConnectionFactory.setLeaveOpen(true);
        return cachingClientConnectionFactory;
    }*/

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PollableChannel receiver() {
        return new QueueChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public TcpOutboundGateway tcpOutboundGateway() {
        TcpOutboundGateway tcpOutboundGateway = new TcpOutboundGateway();
        tcpOutboundGateway.setConnectionFactory(tcpNioClientConnectionFactory());
        return tcpOutboundGateway;
    }

    /*
    @Bean
    @ServiceActivator(inputChannel = "outputSendingChannel")
    public TcpSendingMessageHandler outboundTcpSendingMessageHandler(AbstractClientConnectionFactory clientConnectionFactory) {
        TcpSendingMessageHandler tcpOutboundGateway = new TcpSendingMessageHandler();
        tcpOutboundGateway.setConnectionFactory(clientConnectionFactory);
        return tcpOutboundGateway;
    }

    @Bean
    public TcpReceivingChannelAdapter outboundTcpReceivingMessageHandler(AbstractClientConnectionFactory clientConnectionFactory) {
        TcpReceivingChannelAdapter tcpOutboundGateway = new TcpReceivingChannelAdapter();
        tcpOutboundGateway.setConnectionFactory(clientConnectionFactory);
        tcpOutboundGateway.setClientMode(true);
        tcpOutboundGateway.setOutputChannel(receiver());
        return tcpOutboundGateway;
    }*/

}
