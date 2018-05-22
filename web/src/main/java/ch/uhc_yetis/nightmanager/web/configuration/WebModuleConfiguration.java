package ch.uhc_yetis.nightmanager.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ch.uhc_yetis.nightmanager.web.rest")
public class WebModuleConfiguration {
}
