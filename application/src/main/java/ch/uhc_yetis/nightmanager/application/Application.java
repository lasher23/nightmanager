package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.repository.api.EnableRepositoryModule;
import ch.uhc_yetis.nightmanager.service.configuration.EnableServiceModule;
import ch.uhc_yetis.nightmanager.web.EnableWebModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableWebModule
@EnableServiceModule
@EnableRepositoryModule
@RestController
@RequestMapping("/")
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class);
    }

    @GetMapping("/")
    public String index() {
        return "Hello world";
    }
}
