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
public class OrderListener {
    // Map<String, Order> pendingOrders;
    // @Autowired
    // private KafkaTemplate<String, String> kafkaTemplate;

    // public OrderListener() {

    // }

    // public static void main(String[] args) {

    // System.out.println("Starting Listener");
    // Properties props = new Properties();
    // props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-event-listener");
    // props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    // props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());
    // props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());
    // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    // StreamsBuilder builder = new StreamsBuilder();

    // // Create a KTable from the "order-events" topic
    // KTable<String, String> ordersTable = builder.table("order-events");

    // // Use the KTable
    // ordersTable.toStream().foreach((key, value) -> {
    // System.out.println("Order ID: " + key + ", Order Details: " + value);
    // });

    // KafkaStreams streams = new KafkaStreams(builder.build(), props);
    // streams.start();
    // }
}