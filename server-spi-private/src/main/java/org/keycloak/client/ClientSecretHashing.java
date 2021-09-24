package org.keycloak.client;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;

public class ClientSecretHashing {

    public static final String CLIENT_SECRET_HASHING_ALGORITHM_KEY = "clientSecretHashingAlgorithm";

    private static final String SEPARATOR = "##";
    private static final String DEFAULT_ALGORITHM = "SHA256";
    private static final String PLAIN_ALGORITHM = "PLAIN";

    public static boolean validateSecret(KeycloakSession session, String inputSecret, String clientSecret) {
        String[] split = inputSecret.split(SEPARATOR);
        ClientSecretHashingProvider provider;
        if (split.length == 1) {
            provider = session.getProvider(ClientSecretHashingProvider.class, PLAIN_ALGORITHM);
        } else {
            provider = session.getProvider(ClientSecretHashingProvider.class, split[0]);
            inputSecret = split[1];
        }
        return provider.validateSecret(inputSecret, clientSecret);
    }

    public static String hashSecret(KeycloakSession session, String inputSecret) {
        RealmModel realm = session.getContext().getRealm();
        String algorithm = realm.getAttribute(CLIENT_SECRET_HASHING_ALGORITHM_KEY, DEFAULT_ALGORITHM);
        ClientSecretHashingProvider provider = session.getProvider(ClientSecretHashingProvider.class, algorithm);

        StringBuilder sb = new StringBuilder();
        sb.append(algorithm);
        sb.append(SEPARATOR);
        sb.append(provider.hash(inputSecret));

        return sb.toString();
    }

}
