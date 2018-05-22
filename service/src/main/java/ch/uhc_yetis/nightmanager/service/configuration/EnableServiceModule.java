package ch.uhc_yetis.nightmanager.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(ServiceModuleConfiguration.class)
@Documented
@Configuration
public @interface EnableServiceModule {
}
