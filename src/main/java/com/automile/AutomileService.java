package com.automile;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.automile.AutomileConfig.getMapper;

@Slf4j
public class AutomileService {

    public String getError(JsonNode tree) {
        try {
            return tree.get("error").asText("error");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        }
    }

    public String getError(String response) {
        try {
            return getError(getMapper().readTree(response));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new AutomileException(e);
        }
    }

}
