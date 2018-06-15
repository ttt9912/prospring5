package ch3.apps.p9_autowiring.with_configuration.related_beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
class FooOne implements Foo {
}
