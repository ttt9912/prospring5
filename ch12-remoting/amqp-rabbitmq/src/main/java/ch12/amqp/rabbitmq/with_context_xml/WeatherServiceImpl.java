package ch12.amqp.rabbitmq.with_context_xml;

import org.springframework.stereotype.Component;

@Component
class WeatherServiceImpl implements WeatherService {


    @Override
    public String getForecast(final String stateCode) {

        if (stateCode.equals("FL")) {
            return "Hot";
        } else if (stateCode.equals("MA")) {
            return "Cold";
        }

        return "unknown stateCode";
    }
}
