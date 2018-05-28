package ch.uhc_yetis.nightmanager.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ch.uhc_yetis.nightmanager.service.impl")
public class ServiceModuleConfiguration {
}
