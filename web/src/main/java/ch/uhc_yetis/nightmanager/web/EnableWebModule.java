package ch.uhc_yetis.nightmanager.web;

import ch.uhc_yetis.nightmanager.web.configuration.WebModuleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(WebModuleConfiguration.class)
@Configuration
public @interface EnableWebModule {
}
