package ch.uhc_yetis.nightmanager.infrastructure;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/adds/**")
                .addResourceLocations("classpath:/static/assets/adds/")
                .setCacheControl(CacheControl.maxAge(20, TimeUnit.DAYS));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(Arrays.stream(HttpMethod.values()).map(Object::toString).toArray(String[]::new))
                .allowedOrigins("*");
    }
}
