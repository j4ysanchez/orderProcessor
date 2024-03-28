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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import com.google.gson.Gson;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class OrderService {
    private KafkaStreams streams;
    private Map<String, Order> pendingOrders;



    @PostConstruct
    public void start() {

        pendingOrders = new HashMap<String, Order>();

        System.out.println("**** OrderListener start() called ****");
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-listener" + System.currentTimeMillis());
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // KStream<String, String> orderEvents = streamsBuilder.stream("order-events");
        // orderEvents.foreach((key, value) -> {
        //     System.out.println("*** Event detected: Key: " + key + " Value: " + value);
        // });
        

        // orderEvents.print(Printed.<String, String>toSysOut().withLabel("**Order Events"));

        KTable<String, String> orderEventsTable = streamsBuilder.table("order-events");

        // You can now use the KTable for various operations, for example:
        orderEventsTable.toStream().foreach((key, value) -> {
            System.out.println("-*- Event detected in KTable: Key: " + key + " Value: " + value);
        });

        KTable<String, String> paymentEventsTable = streamsBuilder.table("payment-events");


       
        KTable<String, String> joinedTable =  orderEventsTable.leftJoin(paymentEventsTable, (order, payment) -> {
            System.out.println("---Order: " + order + " Payment: " + payment);

            Gson gson = new Gson();
            Order pendingOrder = gson.fromJson(order, Order.class);

            if (payment == null ) {
                System.out.println("**** Payment not received");
                
                pendingOrders.put(pendingOrder.getId(), pendingOrder);
            }
            else 
            {
                System.out.println("**** Order has been paid");

                
                if (pendingOrders.containsKey(pendingOrder.getId())) {
                    pendingOrders.remove(pendingOrder.getId());
                }
            }
            // if (payment == null) {

            //     Gson gson = new Gson();
            //     Order orderObj = gson.fromJson(order, Order.class);
            //     pendingOrders.put(orderObj.getId(), order);
            //     System.out.println("**** pending orders: " + pendingOrders.size());
            // }
            return order + " " + payment;
        });

        

        streams = new KafkaStreams(streamsBuilder.build(), config);
        streams.start();


        // KStream<String, String> orderEvents = streams.stream("order-completed-events");
    }

    @PreDestroy
    public void stop() {
        streams.close();
    }

}