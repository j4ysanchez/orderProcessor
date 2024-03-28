package ca.mjsanchez.orderProcessor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import com.google.gson.Gson;

@Service
public class OrderProcessorService {
    // Map<String, Order> pendingOrders;
    // Map<String, String> paidOrders;

    // @Autowired
    // private KafkaTemplate<String, String> kafkaTemplate;
    // private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // public OrderProcessorService() {

    //     Properties props = new Properties();
    //         props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-processor");
    //         props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    //         props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //         props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //         props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            
    //         StreamsBuilder builder = new StreamsBuilder();
    //     pendingOrders = new LinkedHashMap<String, Order>();
    //     paidOrders = new LinkedHashMap<String, String>();

    //     // check for pending orders every 30 seconds
    //     scheduler.scheduleAtFixedRate(this::checkPendingOrders, 0, 5, TimeUnit.SECONDS);

    // }

    // public void checkPendingOrders() {
    //     System.out.println("Checking pending orders");

    //     System.out.println("Pending orders: " + pendingOrders.size());

    //     // iterate through each item in pendingOrders
    //     for (Map.Entry<String, Order> entry : pendingOrders.entrySet()) {
    //         String orderId = entry.getKey();
    //         Order order = entry.getValue();

    //         if (paidOrders.containsKey(orderId)) {
    //             String paymentStatus = paidOrders.get(orderId);
    //             if (paymentStatus.equals("valid")) {
    //                 sendOrder(orderId);
    //             } else {
    //                 System.out.println("Payment not valid for: " + orderId);
    //                 cancelOrder(orderId);
    //             }
    //         }
    //     }
    //     // cleanup pending orders
    // }

    // @KafkaListener(topics = "order-events", groupId = "order-group")
    // public void consume(String message) {
    //     System.out.println("Consumed order message: " + message);

    //     Gson gson = new Gson();
    //     Map<String, String> orderJson = gson.fromJson(message, Map.class);

    //     Order order = new Order(
    //             (String) orderJson.get("id"),
    //             (String) orderJson.get("timestamp"),
    //             (String) orderJson.get("customerName"),
    //             (String) orderJson.get("customerAddress"),
    //             (String) orderJson.get("pizzaToppings"),
    //             (String) orderJson.get("pizzaSize"));

    //     order.setStatus("pending");
    //     pendingOrders.put(order.getId(), order);

    //     System.out.println("Storing order: " + order.getId());

    //     checkPendingOrders();

    // }

    // @KafkaListener(topics = "payment-events", groupId = "payment-group")
    // public void consumePaymentEvent(String message) {
    //     System.out.println("Consumed payment message: " + message);

    //     Gson gson = new Gson();
    //     Map<String, String> paymentJson = gson.fromJson(message, Map.class);

    //     String orderId = paymentJson.get("order_id");
    //     String paymentStatus = paymentJson.get("payment_status");

    //     // if (!pendingOrders.containsKey(orderId)) {
    //     // System.out.println("Order not found: " + orderId);
    //     // // TODO: get payment service to cancel payment
    //     // }

    //     // if (paymentStatus.equals("valid")) {
    //     // System.out.println("Payment valid for: " + orderId);
    //     // sendOrder(orderId);
    //     // }

    //     paidOrders.put(orderId, paymentStatus);

    //     checkPendingOrders();
    // }

    // public void cancelOrder(String orderId) {
    //     System.out.println(orderId + " order cancelled!");
    //     String topic = "order-canceled-events";
    //     Gson gson = new Gson();

    //     Order orderToBeSent = pendingOrders.get(orderId);
    //     orderToBeSent.setStatus("canceled");

    //     kafkaTemplate.send(topic, orderId, gson.toJson(orderToBeSent));

    //     pendingOrders.remove(orderId);

    // }

    // public void sendOrder(String orderId) {
    //     // kafkaTemplate.send("order-events", orderId, "Order Processed!");
    //     System.out.println(orderId + " order processed!");
    //     String topic = "order-completed-events";
    //     Gson gson = new Gson();

    //     Order orderToBeSent = pendingOrders.get(orderId);
    //     orderToBeSent.setStatus("completed");

    //     kafkaTemplate.send(topic, orderId, gson.toJson(orderToBeSent));

    //     pendingOrders.remove(orderId);

    // }
}