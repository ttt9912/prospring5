package ch16.websocket.p1.app;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 * TextWebSocketHandler: deal with String based messages
 *
 * TextMessage.getPayload(): content of the received websocket message
 */
public class EchoHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        // simply echos received message
        session.sendMessage(new TextMessage(message.getPayload()));
    }
}
