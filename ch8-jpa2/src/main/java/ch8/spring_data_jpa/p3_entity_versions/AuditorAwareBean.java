package ch8.spring_data_jpa.p3_entity_versions;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
 * The AuditorAwareBean provides the user information.
 * (in real applications, this should be an instance of user information
 * provided by the underlying security infrastructure)
 */
@Component
public class AuditorAwareBean implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("thts");
    }
}
