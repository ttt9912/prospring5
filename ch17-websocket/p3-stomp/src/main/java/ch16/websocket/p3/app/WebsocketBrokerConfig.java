package ch16.websocket.p3.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/*
 * Configure STOMP Broker
 *
 * AbstractWebSocketMessageBrokerConfigurer: provides
 * optional methods for message handling
 *
 * @EnableWebSocketMessageBroker: enables broker-backed
 * messaging over Websocket using a subprotocol (STOMP)
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    // configure stomp endpoint
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    // configure message broker
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
