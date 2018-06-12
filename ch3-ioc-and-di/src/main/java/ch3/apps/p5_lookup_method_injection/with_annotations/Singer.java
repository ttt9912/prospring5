package ch3.apps.p5_lookup_method_injection.with_annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
class Singer {

    private String lyric = "Hello Goodbye";

    public void sing(){
        // commented out because it pollutes the output
//        System.out.println(lyric);
    }
}
