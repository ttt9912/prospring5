package ch17.websocket.p5.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * all queues and user destinations are prefixed with “/secured”
 * to make them require authentication
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        // listener?
        registry.enableSimpleBroker("/secured/user/queue/specific-user");
        // controller root?
        registry.setApplicationDestinationPrefixes("/app");

        /*
         * user destination (queue)
         * determines which endpoints are reserved for single users
         */
        registry.setUserDestinationPrefix("/secured/user");
    }

    // endpoint url http://localhost:8080/secured/room
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/secured/room").withSockJS();
    }
}
