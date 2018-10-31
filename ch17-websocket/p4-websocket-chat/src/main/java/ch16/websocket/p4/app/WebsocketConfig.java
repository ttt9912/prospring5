package ch16.websocket.p4.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {

        // enable an in-memory message broker to carry the messages back
        // to the client on destinations prefixed with “/topic”
        registry.enableSimpleBroker("/topic");

        // filter destinations targeting application annotated methods
        // (via @MessageMapping) - applications are sent to /app/chat
        registry.setApplicationDestinationPrefixes("/app");
    }

    // registers the “/chatroom” endpoint, enabling Spring’s STOMP support
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/chatroom").withSockJS();
        // endpoint that works without the SockJS for the sake of elasticity
        registry.addEndpoint("/chatroom");
    }
}
