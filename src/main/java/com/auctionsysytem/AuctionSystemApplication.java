package com.auctionsysytem;

import com.auctionsysytem.shared.BeanName;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class AuctionSystemApplication extends SpringBootServletInitializer {

    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", BeanName.APP_PROP_FILE);
        return props;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuctionSystemApplication.class)
                .sources(AuctionSystemApplication.class)
                .properties(getProperties())
                .run(args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
