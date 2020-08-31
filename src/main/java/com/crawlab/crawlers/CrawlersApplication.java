package com.crawlab.crawlers;

import com.crawlab.crawlers.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlersApplication implements CommandLineRunner {
    @Autowired
    private SpiderService spiderService;

    public static void main(String[] args) {
        SpringApplication.run(CrawlersApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        spiderService.run();
    }
}
