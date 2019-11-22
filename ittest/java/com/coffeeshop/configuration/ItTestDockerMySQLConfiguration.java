package com.coffeeshop.configuration;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.configuration.ShutdownStrategy;
import com.palantir.docker.compose.connection.waiting.HealthChecks;

public class ItTestDockerMySQLConfiguration {

    /**
     * This library (com.palantir) requires the next two env variables to exist for Windows OS
     * Can be added on either OS level, or via Intellij -> Edit Configurations (for each run option) -> Environment variables
     *
     * Expected to work via maven without any manual set up
     *
     * DOCKER_COMPOSE_LOCATION=C:\Program Files\Docker\Docker\resources\bin\docker-compose.exe
     * DOCKER_LOCATION=C:\Program Files\Docker\Docker\resources\bin\docker.exe
     */

    public static DockerComposeRule getDockerComposeRule() {

        return DockerComposeRule.builder()
                .file("ittest/docker-compose-it-test.yml")
                .shutdownStrategy(ShutdownStrategy.GRACEFUL)
                .waitingForService("database", HealthChecks.toHaveAllPortsOpen())
                .build();
    }
}
