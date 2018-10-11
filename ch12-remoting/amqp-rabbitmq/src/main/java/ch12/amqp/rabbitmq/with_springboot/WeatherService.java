package ch12.amqp.rabbitmq.with_springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class WeatherService {

    @RabbitListener(queues = "forecasts")
    public String getForecast(final String stateCode) {

        if (stateCode.equals("FL")) {
            return "Hot";
        } else if (stateCode.equals("MA")) {
            return "Cold";
        }

        return "unknown stateCode";
    }
}
