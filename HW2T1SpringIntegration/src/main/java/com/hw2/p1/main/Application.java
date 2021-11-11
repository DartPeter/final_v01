package com.hw2.p1.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.hw2.p1.integration.IOrderGateway;
import com.hw2.p1.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Starts the application by initializing Spring container.
 * 
 * @author Eren Avsarogullari
 * @since 10 Dec 2014
 * @version 1.0.0
 */
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		IOrderGateway orderGateway = ctx.getBean(IOrderGateway.class);
		getOrderListFromFile().forEach(order -> orderGateway.processOrderRequest(MessageBuilder.withPayload(order).build()));
	}

    private static List<Order> getOrderListFromFile() {
	    List<Order> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.csv"));) {
            bufferedReader.readLine();
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.trim().split(",");
                Integer.parseInt(data[0].trim());
                Order.OrderState.valueOf(data[1].trim());
                Order order = new Order(
                        Integer.parseInt(data[0].trim()),
                        Order.OrderState.valueOf(data[1].trim()));
                result.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
