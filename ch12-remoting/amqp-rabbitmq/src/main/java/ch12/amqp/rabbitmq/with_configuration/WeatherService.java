package ch12.amqp.rabbitmq.with_configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/*
 * @RabbitListener: todo
 *
 * rabbitListenerContainerFactory: bean of type RabbitListenerContainerFactory
 * used to create a regular SimpleMessageListenerContainer
 */
@Service
class WeatherService {


    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = "forecasts")
    public String getForecast(final String stateCode) {

        if (stateCode.equals("FL")) {
            return "Hot";
        } else if (stateCode.equals("MA")) {
            return "Cold";
        }

        return "unknown stateCode";
    }
}
