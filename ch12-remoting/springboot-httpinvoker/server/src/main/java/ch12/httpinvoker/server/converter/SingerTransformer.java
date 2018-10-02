package ch12.httpinvoker.server.converter;

import ch12.httpinvoker.api.element.SingerApiElement;
import ch12.httpinvoker.server.data.Singer;
import org.springframework.core.convert.converter.Converter;

// ApiElement -> Entity
public class SingerTransformer implements Converter<SingerApiElement, Singer> {


    @Override
    public Singer convert(final SingerApiElement singerApiElement) {
        final Singer singer = new Singer();
        singer.setFirstName(singerApiElement.getFirstName());
        singer.setLastName(singerApiElement.getLastName());
        singer.setBirthDate(singerApiElement.getBirthDate());
        return singer;
    }
}
