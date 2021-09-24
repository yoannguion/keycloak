package org.keycloak.client;

public class SHA512ClientSecretHashingProvider extends MessageDigestClientSecretHashingProvider {

    @Override
    public String getId() {
        return "SHA256";
    }

    @Override
    String getAlgorithm() {
        return "SHA-256";
    }

}
