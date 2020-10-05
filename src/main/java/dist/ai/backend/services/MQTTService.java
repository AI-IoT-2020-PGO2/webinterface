package dist.ai.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dist.ai.backend.database.VoteContext;
import dist.ai.backend.models.SongInfo;
import dist.ai.backend.models.Vote;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class MQTTService {
    public static String VOTES_TOPIC = "votes";
    public static String SONGS_TOPIC = "songs";
    public static String CLIENT_ID = "ClubIoT_Backend";

    @Autowired
    private VoteContext voteContext;

    private final String broker = "tcp://broker.mqttdashboard.com";
    private final MqttClient client;

    ObjectMapper objectMapper;

    public MQTTService() throws MqttException {
        //clear MemoryPersistence
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, CLIENT_ID, persistence);

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: "+broker);
        client.connect(connOpts);
        System.out.println("Connected");

        objectMapper = new ObjectMapper();
    }

    public void publishNewSong(SongInfo songInfo) {
        try {
            String song = objectMapper.writeValueAsString(songInfo);
            publish(song);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void publish(String content){
        if (!client.isConnected()) {
            System.out.println("client not connected");
            return;
        }

        System.out.println("MQTT publish");

        try {
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(2);
            message.setRetained(true);
            client.publish(SONGS_TOPIC, message);
            System.out.println("Message published");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

    public void subscribeToVotes() throws MqttException {
        if (!client.isConnected()) {
            System.out.println("client not connected");
            return;
        }

        //subscribe to topic
        client.subscribe(VOTES_TOPIC, this::processMessage);
    }

    public void processMessage(String topic, MqttMessage msg) {
        if (topic.equals(MQTTService.VOTES_TOPIC)) {
            String message = new String(msg.getPayload());
            System.out.println("message arrived: " + message);
            try {
                Vote vote = objectMapper.readValue(message, Vote.class);
                voteContext.saveVote(vote);
            } catch (JsonProcessingException e) {
                System.out.println("Failed reading message: ");
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
            client.close();
            System.out.println("MQTT: Disconnected");
        } catch (MqttException e) {
            System.out.println("Failed to disconnect or close MQTT client");
            e.printStackTrace();
        }
    }
}
