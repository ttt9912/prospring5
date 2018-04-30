package ch3.apps.p3_field_injection.with_annotations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Inspiration {

    @Value("Break on thru")
    private String lyric;

    public Inspiration(@Value("the other side") String lyric) {
        this.lyric = lyric;
    }

    public String getLyric() {
        return lyric;
    }
}
