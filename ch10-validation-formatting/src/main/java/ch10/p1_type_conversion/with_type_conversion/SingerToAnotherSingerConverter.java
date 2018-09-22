package ch10.p1_type_conversion.with_type_conversion;

import ch10.AnotherSinger;
import ch10.Singer;
import org.springframework.core.convert.converter.Converter;

class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger> {

    @Override
    public AnotherSinger convert(final Singer singer) {
        final AnotherSinger anotherSinger = new AnotherSinger();
        anotherSinger.setFirstName(singer.getLastName());
        anotherSinger.setLastName(singer.getFirstName());
        anotherSinger.setBirthDate(singer.getBirthDate());
        anotherSinger.setPersonalSite(singer.getPersonalSite());
        return anotherSinger;
    }
}
