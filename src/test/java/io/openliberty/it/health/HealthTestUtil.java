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

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class HealthTestUtil {

  private static String port;
  private static String baseUrl;
  private final static String HEALTH_ENDPOINT = "health";
  public static final String INV_MAINTENANCE_FALSE = "{\"config_ordinal\":500,\"io_openliberty_sample_system_inMaintenance\":false,\"io_openliberty_sample_testConfigOverwrite\":\"CustomSource\"}";
  public static final String INV_MAINTENANCE_TRUE = "{\"config_ordinal\":500,\"io_openliberty_sample_system_inMaintenance\":true,\"io_openliberty_sample_testConfigOverwrite\":\"CustomSource\"}";

  static {
    baseUrl = "http://localhost:9080/";
  }

  public static String getBaseUrl() {
    return baseUrl;
  }

  public static void setMaintenanceMode() {
    setMaintenanceMode(INV_MAINTENANCE_TRUE);
  }

  public static void unsetMaintenanceMode() {
    setMaintenanceMode(INV_MAINTENANCE_FALSE);
  }

  public static void setMaintenanceMode(String value) {
    try {
      Files.write(Paths.get(System.getProperty("user.dir").split("target")[0] + "/resources/CustomConfigSource.json"), value.getBytes(), TRUNCATE_EXISTING);
      Thread.sleep(600);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
