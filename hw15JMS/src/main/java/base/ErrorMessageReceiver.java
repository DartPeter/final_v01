package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static base.ActiveMQConfig.QUEUE_ERROR;

@Component
public class ErrorMessageReceiver {

    private static Logger log = LoggerFactory.getLogger(ErrorMessageReceiver.class);

    @JmsListener(destination = QUEUE_ERROR)
    public void receiveMessage(@Payload ErrorMessage message) {
        log.error("Error message receiver {} - Message received: {} ", this, message);
    }

}
