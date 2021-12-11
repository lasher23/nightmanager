package ch.uhc_yetis.nightmanager.infrastructure;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/adds/**")
        .addResourceLocations("classpath:/static/assets/adds/")
        .setCacheControl(CacheControl.maxAge(20, TimeUnit.DAYS));
  }

}
