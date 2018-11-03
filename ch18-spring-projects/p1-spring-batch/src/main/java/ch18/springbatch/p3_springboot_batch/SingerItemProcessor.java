package ch18.springbatch.p3_springboot_batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {
    private static Logger log = LoggerFactory.getLogger(SingerItemProcessor.class);

    @Override
    public Singer process(final Singer singer) throws Exception {

        final Singer transformed = new Singer();
        transformed.setFirstName(singer.getFirstName().toUpperCase());
        transformed.setLastName(singer.getLastName().toUpperCase());
        transformed.setSong(singer.getSong().toUpperCase());

        log.info("transformed singer {} into {}", singer, transformed);
        return transformed;
    }
}
