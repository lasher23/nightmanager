package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.repository.api.EnableRepositoryModule;
import ch.uhc_yetis.nightmanager.service.configuration.EnableServiceModule;
import ch.uhc_yetis.nightmanager.web.EnableWebModule;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableWebModule
@EnableServiceModule
@EnableRepositoryModule
public class Application {
    public static void main(String... args){
        SpringApplication.run(Application.class);
    }
}
