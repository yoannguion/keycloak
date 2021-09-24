package org.keycloak.client;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.Provider;

import java.security.MessageDigest;

public class PlainClientSecretHashingProvider implements ClientSecretHashingProviderFactory, ClientSecretHashingProvider {

    @Override
    public String hash(String secret) {
        return secret;
    }

    @Override
    public boolean validateSecret(String inputSecret, String clientSecret) {
        return MessageDigest.isEqual(inputSecret.getBytes(), clientSecret.getBytes());
    }

    @Override
    public Provider create(KeycloakSession session) {
        return this;
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "PLAIN";
    }

}
