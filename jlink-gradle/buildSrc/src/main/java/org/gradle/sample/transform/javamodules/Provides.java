package org.gradle.sample.transform.javamodules;

import java.io.Serializable;

/**
 * Models a "provides 'service' with 'provider'" directive in a 'module-info.java' file.
 */
public record Provides(String service, String provider) implements Serializable {
}
