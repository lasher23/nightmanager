package ch.uhc_yetis.nightmanager.repository.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "ch.uhc_yetis.nightmanager.repository.api")
@EntityScan(basePackages = "ch.uhc_yetis.nightmanager.repository.api")
@EnableJpaRepositories("ch.uhc_yetis.nightmanager.repository.api")
public class RepositoryModuleConfiguration {
}
