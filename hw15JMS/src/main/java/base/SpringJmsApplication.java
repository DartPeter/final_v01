package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class SpringJmsApplication implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(SpringJmsApplication.class);
    private static Random random = new Random();
    private final static int N = 10;

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsApplication.class, args);
    }

    @Autowired
    private MessageSender sender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 1; i <= N; i++) {
            sender.sendRequest(new RequestMessage(i, random.nextInt(1000), random.nextInt(1000)));
        }

    }
}
