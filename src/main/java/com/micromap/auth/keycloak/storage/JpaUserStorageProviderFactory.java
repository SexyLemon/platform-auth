package com.micromap.auth.keycloak.storage;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

import javax.naming.InitialContext;

/**
 * @author limeng 2018/3/30
 */
public class JpaUserStorageProviderFactory implements UserStorageProviderFactory<JpaUserStorageProvider> {
    private static final Logger logger = Logger.getLogger(JpaUserStorageProviderFactory.class);


    @Override
    public JpaUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        try {
            InitialContext ctx = new InitialContext();
            JpaUserStorageProvider provider = (JpaUserStorageProvider) ctx.lookup(
                    "java:global/user-storage-jpa/"
                            + JpaUserStorageProvider.class.getSimpleName());
            provider.setModel(model);
            provider.setSession(session);
            return provider;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getId() {
        return "user-storage-jpa";
    }

    @Override
    public String getHelpText() {
        return "JPA User Storage Provider";
    }

    @Override
    public void close() {
        logger.info("<<<<<< Closing factory");
    }
}
