package ca.mjsanchez.orderProcessor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import java.util.Properties;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class OrderInspector {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "join-example");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        StreamsBuilder builder = new StreamsBuilder();

        KTable<String, String> helloEvents = builder.table("hello-log", Materialized.as("hello-store"));
        KTable<String, String> byeEvents = builder.table("bye-log", Materialized.as("bye-store"));

        // Filter the KTables
        KTable<String, String> filteredHelloEvents = helloEvents.filter((key, value) -> {
            if (value != null) {

                System.out.println("hello-log: " + value);
                JsonObject json = JsonParser.parseString(value).getAsJsonObject();
                return json.has("orderid");
            }
            return false;
        });

        KTable<String, String> filteredByeEvents = byeEvents.filter((key, value) -> {
            if (value != null) {
                System.out.println("bye-log: " + value);
                JsonObject json = JsonParser.parseString(value).getAsJsonObject();
                return json.has("orderid");
            }
            return false;
        });

        // Perform the join operation
        KTable<String, String> joined = filteredHelloEvents.join(filteredByeEvents, (helloValue, byeValue) -> {
            JsonObject helloJson = JsonParser.parseString(helloValue).getAsJsonObject();
            JsonObject byeJson = JsonParser.parseString(byeValue).getAsJsonObject();

            if (helloJson.get("orderid").getAsString().equals(byeJson.get("orderid").getAsString())) {
                // return helloValue + ", " + byeValue;
                String joinTemplate = "{\"orderid\":\"%s\",\"data\":\"%s\"}";
                String joinValue = String.format(joinTemplate,
                        helloJson.get("orderid").getAsString(),
                        helloJson.get("data").getAsString()
                                + ", " + byeJson.get("data").getAsString());
                return joinValue;
            } else {
                return null;
            }
        });
        joined.toStream().to("joined-events");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }

    // private KafkaStreams streams;

    // @PostConstruct
    // public void start() {

    // System.out.println("OrderInspector starting.");
    // Properties props = new Properties();
    // props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-processor-service");
    // props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    // props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());
    // props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
    // Serdes.String().getClass());

    // StreamsBuilder builder = new StreamsBuilder();

    // // Create a KTable for the "order-events" topic
    // KTable<String, String> ordersTable = builder.table("order-events",
    // Materialized.as("orders-store"));

    // // Create a KTable for the "payment-events" topic
    // KTable<String, String> paymentsTable = builder.table("payment-events",
    // Materialized.as("payments-store"));

    // // Join the two KTables
    // KTable<String, String> joinedTable = ordersTable.join(paymentsTable, (order,
    // payment) -> {
    // // Here you can combine the order and payment information as needed
    // return order + ", " + payment;
    // });

    // // Filter the joined table for valid payments and write to "processed-orders"
    // // topic
    // joinedTable
    // .filter((orderId, orderPaymentInfo) ->
    // orderPaymentInfo.contains("\"payment_status\" : \"valid\""))
    // .toStream()
    // .to("processed-orders", Produced.with(Serdes.String(), Serdes.String()));

    // streams = new KafkaStreams(builder.build(), props);
    // streams.start();
    // }

    // @KafkaListener(topics = "order-events", groupId = "order-group")
    // public void consume(String message) {
    // System.out.println("Order Inspector seeing: " + message);
    // }

    // @PreDestroy
    // public void stop() {
    // streams.close();
    // }
}
