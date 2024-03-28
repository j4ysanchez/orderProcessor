package ca.mjsanchez.orderProcessor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
// @EnableKafkaStreams
public class OrderListener {

    // public OrderListener() {
    //     Properties props = new Properties();
    //     props.put(StreamsConfig.APPLICATION_ID_CONFIG, "join-example");
    //     props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    //     props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //     props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //     props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    //     StreamsBuilder builder = new StreamsBuilder();
    //     KTable<String, String> helloEvents = builder.table("hello-events", Materialized.as("hello-store"));
    //     KTable<String, String> byeEvents = builder.table("bye-events", Materialized.as("bye-store"));

    //     // Filter the KTables
    //     KTable<String, String> filteredHelloEvents = helloEvents.filter((key, value) -> {
    //         if (value != null) {

    //             System.out.println("hello-events: " + value);
    //             JsonObject json = JsonParser.parseString(value).getAsJsonObject();
    //             return json.has("orderid");
    //         }
    //         return false;
    //     });

    //     KTable<String, String> filteredByeEvents = byeEvents.filter((key, value) -> {
    //         if (value != null) {
    //             System.out.println("bye-events: " + value);
    //             JsonObject json = JsonParser.parseString(value).getAsJsonObject();
    //             return json.has("orderid");
    //         }
    //         return false;
    //     });
    //     // Continue with your stream processing logic

    //     // Perform the join operation
    //     KTable<String, String> joined = filteredHelloEvents.join(filteredByeEvents, (helloValue, byeValue) -> {
    //         JsonObject helloJson = JsonParser.parseString(helloValue).getAsJsonObject();
    //         JsonObject byeJson = JsonParser.parseString(byeValue).getAsJsonObject();

    //         if (helloJson.get("orderid").getAsString().equals(byeJson.get("orderid").getAsString())) {
    //             return helloValue + ", " + byeValue;
    //         } else {
    //             return null;
    //         }
    //     });
    //     joined.toStream().to("joined-events");

    //     KafkaStreams streams = new KafkaStreams(builder.build(), props);
    //     streams.start();

    // }
}
