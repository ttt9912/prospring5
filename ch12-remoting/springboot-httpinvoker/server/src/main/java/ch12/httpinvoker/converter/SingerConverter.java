package ch12.httpinvoker.converter;

import ch12.httpinvoker.api.element.SingerApiElement;
import ch12.httpinvoker.data.Singer;
import org.springframework.core.convert.converter.Converter;

// Entity -> ApiElement
public class SingerConverter implements Converter<Singer, SingerApiElement> {

    @Override
    public SingerApiElement convert(final Singer singer) {
        return new SingerApiElement(
                singer.getFirstName(),
                singer.getLastName(),
                singer.getBirthDate());
    }
}
