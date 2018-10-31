package ch17.websocket.p5.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/*
 * We want our client to subscribe to a queue using a URL mapping
 * that conforms to the following pattern: "/user/queue/updates"
 *
 * This mapping will be automatically transformed by UserDestinationMessageHandler
 * into the user-session-specific address.
 *
 * If we have a user named “user123”, the corresponding address
 * would be: "/queue/updates-user123"
 *
 * The server sends the user-specific response using the following
 * URL mapping pattern: "/user/{username}/queue/updates"
 */
@Controller
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /*
     * @Header:  allows access to headers exposed by the inbound message,
     * we can grab the current sessionId without the need for complicated interceptors.
     */
    public void sendSpecific(@Payload Message msg,
                             Principal user,
                             @Header("simpSessionId") String sessionId) {
        log.info("received message: {}", msg);
        log.info("user: {}", user.getName());
        log.info("session id: {}", sessionId);
        log.info("sending message to: {}", msg.getTo());
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/secured/user/queue/specific-user", msg);
    }


}
