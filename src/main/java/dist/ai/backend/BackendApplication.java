package dist.ai.backend;

import dist.ai.backend.database.VoteContext;
import dist.ai.backend.services.MQTTService;
import dist.ai.backend.services.TimerService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class BackendApplication {
    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public TimerService TimerService() {
        return new TimerService();
    }

    @Bean
    public VoteContext VoteContext() {
        return new VoteContext();
    }

    @Bean
    public MQTTService MQTTService() {
        try {
            return new MQTTService();
        } catch (MqttException e) {
            e.printStackTrace();
            System.out.println("Failed to create MQTT client");
            System.exit(-1);
            return null;
        }
    }

    @Bean
    public void StartSubscribe() {
        MQTTService mqttService = context.getBean(MQTTService.class);
        try {
            mqttService.subscribeToVotes();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void onDestroy() {
        MQTTService mqttService = context.getBean(MQTTService.class);
        mqttService.disconnect();
    }
}
