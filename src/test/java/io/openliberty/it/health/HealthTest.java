/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/

package io.openliberty.it.health;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

public class HealthTest {
    @Test
    public void testIfServicesAreUp() {
        HealthTestUtil.unsetMaintenanceMode();

        RestAssured.when()
                .get(HealthTestUtil.getBaseUrl() + "health")
                .then()
                .statusCode(200)
                .body("outcome", Matchers.equalTo("UP"));
    }

    @Test
    public void testIfServicesAreDown() {
        HealthTestUtil.setMaintenanceMode();

        RestAssured.when()
                .get(HealthTestUtil.getBaseUrl() + "health")
                .then()
                .statusCode(503)
                .body("outcome", Matchers.equalTo("DOWN"));
    }

    @After
    public void teardown() {
        HealthTestUtil.unsetMaintenanceMode();
    }
}
