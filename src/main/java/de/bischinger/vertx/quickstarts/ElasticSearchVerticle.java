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

import java.net.UnknownHostException;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class ElasticSearchVerticle extends AbstractVerticle {

    @Override
    public void start() throws UnknownHostException {

        vertx.setPeriodic(1000, id -> {
            // This handler will get called every second
            System.out.println("timer fired!");
        });
    }
}