/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package de.bischinger.vertx.quickstarts;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static java.net.InetAddress.getByName;
import static org.elasticsearch.client.transport.TransportClient.builder;
import static org.elasticsearch.common.settings.Settings.settingsBuilder;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class RestVerticle extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
   /* public static void main(String[] args) {
        Runner.runExample(RestVerticle.class);
    }*/

    private Map<String, JsonObject> products = new HashMap<>();

    @Override
    public void start() throws UnknownHostException {

        setUpInitialData();
     //   setupElasticsearchClient();
      //  vertx.deployVerticle(new PeriodicTimerVerticle(), new DeploymentOptions().setWorker(true));
     //   vertx.deployVerticle(new ElasticSearchVerticle(), new DeploymentOptions().setWorker(true));

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/products/:productID").handler(this::handleGetProduct);
        router.put("/products/:productID").handler(this::handleAddProduct);
        router.get("/products").handler(this::handleListProducts);

        router.route("/*").handler(StaticHandler.create());

        Integer port = config().getInteger("http.port", 8080);
        vertx.createHttpServer().requestHandler(router::accept).listen(port);
    }

    private void setupElasticsearchClient() throws UnknownHostException {
        Settings settings = settingsBuilder()
                .put("cluster.name", "elasticsearch_bischofa").build();
        Client client = builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(getByName("localhost"), 9300));

        SearchResponse searchResponse = client.prepareSearch().execute().actionGet();
        System.out.println("-->" + searchResponse.getHits());
    }

    private void handleGetProduct(RoutingContext routingContext) {
        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = products.get(productID);
            if (product == null) {
                sendError(404, response);
            } else {
                response.putHeader("content-type", "application/json").end(product.encodePrettily());
            }
        }
    }

    private void handleAddProduct(RoutingContext routingContext) {
        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = routingContext.getBodyAsJson();
            if (product == null) {
                sendError(400, response);
            } else {
                products.put(productID, product);
                response.end();
            }
        }
    }

    private void handleListProducts(RoutingContext routingContext) {
        JsonArray arr = new JsonArray();
        products.forEach((k, v) -> arr.add(v));
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }

    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }

    private void setUpInitialData() {
        addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
        addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
        addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
    }

    private void addProduct(JsonObject product) {
        products.put(product.getString("id"), product);
    }
}