package com.latecurio.log4j1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LoggingController {

    private static final Logger LOG = LogManager.getLogger(LoggingController.class);

    @GetMapping("/info")
    public String logInfo(@RequestParam String message) throws InterruptedException {
        Thread.sleep(1000);
        ThreadContext.put("Jerry", "EAD");
        LOG.info(message);
        return message;
    }
}
