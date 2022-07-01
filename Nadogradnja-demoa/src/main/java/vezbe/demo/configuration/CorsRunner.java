package vezbe.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsRunner implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowCredentials(true)
                // Which primitive fields to release
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{
                        "GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*");
    }

}


