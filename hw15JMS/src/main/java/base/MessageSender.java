package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static base.ActiveMQConfig.*;

@Service
public class MessageSender {

    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    private final Map<Integer, RequestMessage> sendedUnchecked = new ConcurrentHashMap<>();

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendRequest(RequestMessage message) {
        log.info("Sending " + message);
        sendedUnchecked.put(message.getCorrelationId(), message);
        jmsTemplate.convertAndSend(QUEUE_REQUEST, message);
    }

    public void sendErrorMessage(ErrorMessage message) {
        log.error("Reporting error: " + message);
        jmsTemplate.convertAndSend(QUEUE_ERROR, message);
    }

    @JmsListener(destination = QUEUE_ANSWER)
    public void receiveAnswer(@Payload AnswerMessage message) {

        RequestMessage requestMessage = sendedUnchecked.get(message.getCorrelationId());
        if(requestMessage == null) return;
        if (!message.getSum().equals(requestMessage.getInt1() + requestMessage.getInt2())) {
            log.error("MessageSender {} Error result while processing answer {} for {}", this, message, requestMessage);
            sendErrorMessage(new ErrorMessage(requestMessage.getCorrelationId(), requestMessage.getInt1(),
                    requestMessage.getInt2(), message.getSum()));
        } else {
            log.info("MessageSender {} - Answer received: {} result is OK", this, message);
            sendedUnchecked.remove(message.getCorrelationId());
        }
    }

}
