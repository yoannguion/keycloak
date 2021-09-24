package org.keycloak.client;

import org.keycloak.provider.Provider;

public interface ClientSecretHashingProvider extends Provider {

    String hash(String secret);

    boolean validateSecret(String inputSecret, String clientSecret);

}
