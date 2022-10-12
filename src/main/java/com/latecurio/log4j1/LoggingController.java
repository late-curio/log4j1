package com.latecurio.log4j1;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    private static Logger LOG = Logger.getLogger(LoggingController.class);

    @GetMapping("/info")
    public String logInfo(@RequestParam String message) {
        LOG.info(message);
        return message;
    }
}
