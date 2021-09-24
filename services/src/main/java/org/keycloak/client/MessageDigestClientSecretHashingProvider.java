package org.keycloak.client;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.Provider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class MessageDigestClientSecretHashingProvider implements ClientSecretHashingProviderFactory, ClientSecretHashingProvider {

    @Override
    public String hash(String secret) {
        return Base64.getEncoder().encodeToString(encode(secret));
    }

    @Override
    public boolean validateSecret(String input, String hashed) {
        return MessageDigest.isEqual(encode(input), Base64.getDecoder().decode(hashed));
    }

    byte[] encode(String secret) {
        try {
            return MessageDigest.getInstance(getAlgorithm()).digest(secret.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Provider create(KeycloakSession session) {
        return this;
    }

    public void init(Config.Scope config) {
    }

    public void postInit(KeycloakSessionFactory factory) {
    }

    public void close() {
    }

    abstract String getAlgorithm();

}
