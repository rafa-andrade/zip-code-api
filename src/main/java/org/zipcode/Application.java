package org.zipcode;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.zipcode.integration.postmon.PostmonIntegration;
import org.zipcode.integration.viacep.ViaCepIntegration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@RefreshScope
public class Application {

    @Value("${integration.viacep.uri}")
    private String viaCepUri;

    @Value("${integration.postmon.uri}")
    private String postmonUri;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @RefreshScope
    public ViaCepIntegration getViaCepIntegration() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ViaCepIntegration.class, viaCepUri);
    }

    @Bean
    @RefreshScope
    public PostmonIntegration getPostmonIntegration() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(PostmonIntegration.class, postmonUri);
    }
}