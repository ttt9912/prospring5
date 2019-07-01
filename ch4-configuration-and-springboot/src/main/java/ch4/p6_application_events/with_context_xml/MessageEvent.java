package ch4.p6_application_events.with_context_xml;

import org.springframework.context.ApplicationEvent;

/*
 * Event Klasse erweitert ApplicationEvent
 */
class MessageEvent extends ApplicationEvent {
    private String msg;

    MessageEvent(final Object source, final String message) {
        super(source);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "msg='" + msg + '\'' +
                ", source=" + source +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
