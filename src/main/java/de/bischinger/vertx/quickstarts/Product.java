package de.bischinger.vertx.quickstarts;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

/**
 * Created by bischofa on 25/12/15.
 */
public class Product {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String price;
    @JsonProperty
    private String weight;

    public Product(String id, String name, String price, String weight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public static void main(String[] args) {
        System.out.println(new JsonObject(Json.encode(new Product("prod35699", "alex", "1.99", "150"))));
    }
}
