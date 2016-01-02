package de.bischinger.vertx.quickstarts;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {

    public static void main(String[] args) throws IOException {
        Vertx vertx = Vertx.vertx();
        String config = new String(readAllBytes(get("src/main/conf/my-application-conf.json")));
        DeploymentOptions options = new DeploymentOptions();
        options.setConfig(new JsonObject(config));
        vertx.deployVerticle(RestVerticle.class.getName(),
                options);
        Integer port = options.getConfig().getInteger("http.port", 8080);
        System.out.println(port);
    }

}