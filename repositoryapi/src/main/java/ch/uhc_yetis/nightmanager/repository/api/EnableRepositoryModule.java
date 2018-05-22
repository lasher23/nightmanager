package ch.uhc_yetis.nightmanager.repository.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RepositoryModuleConfiguration.class)
@Configuration
public @interface EnableRepositoryModule {
}
