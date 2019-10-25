package br.com.vendaapi.vendaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VendaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendaApiApplication.class, args);
    }
}
