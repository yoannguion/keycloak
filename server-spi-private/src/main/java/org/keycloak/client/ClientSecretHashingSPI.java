package org.keycloak.client;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

public class ClientSecretHashingSPI implements Spi {

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public String getName() {
        return "clientSecretHashing";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return null;
    }

    @Override
    public Class<? extends ProviderFactory> getProviderFactoryClass() {
        return null;
    }


}
