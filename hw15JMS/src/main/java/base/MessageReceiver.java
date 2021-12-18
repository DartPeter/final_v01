package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static base.ActiveMQConfig.QUEUE_ANSWER;
import static base.ActiveMQConfig.QUEUE_REQUEST;

@Component
public class MessageReceiver {

    private static Logger log = LoggerFactory.getLogger(MessageReceiver.class);
    private final int N = 5;
    private final Random random = new Random();

    private final List<AnswerMessage> answersPull = new ArrayList<>();

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = QUEUE_REQUEST)
    public void receiveMessage(@Payload RequestMessage message) {
        log.info("MessageReceiver {} - Message received: {} ", this, message);
        AnswerMessage answerMessage = new AnswerMessage(message.getCorrelationId(),
                !toInflate() ? message.getInt1() + message.getInt2() : random.nextInt(1000));
        answersPull.add(answerMessage);
        if(answersPull.size() >= N) {
            answersPull.stream().forEach(a -> sendAnswer(a));
            answersPull.clear();
        }
    }

    public void sendAnswer(AnswerMessage message) {
        log.info("Answering " + message);
        jmsTemplate.convertAndSend(QUEUE_ANSWER, message);
    }

    private boolean toInflate() {
        return random.nextInt(5) == 4;
    }

}
