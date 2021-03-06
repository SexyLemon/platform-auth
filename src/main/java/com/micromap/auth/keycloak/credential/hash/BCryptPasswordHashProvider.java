package com.micromap.auth.keycloak.credential.hash;

import org.keycloak.common.util.Base64;
import org.keycloak.credential.CredentialModel;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.UserCredentialModel;

import java.io.IOException;

/**
 * @author limeng 2018/3/28
 */
public class BCryptPasswordHashProvider implements PasswordHashProvider {
    private final int defaultIterations;
    private final String providerId;

    public BCryptPasswordHashProvider(String providerId, int defaultIterations) {
        this.providerId = providerId;
        this.defaultIterations = defaultIterations;
    }

    @Override
    public boolean policyCheck(PasswordPolicy policy, CredentialModel credential) {
        int policyHashIterations = policy.getHashIterations();
        if (policyHashIterations == -1) {
            policyHashIterations = defaultIterations;
        }

        return credential.getHashIterations() == policyHashIterations && providerId.equals(credential.getAlgorithm());
    }

    @Override
    public String encode(String rawPassword, int iterations) {
        String salt = iterations == -1 ? BCrypt.gensalt(defaultIterations) : BCrypt.gensalt(iterations);
        return BCrypt.hashpw(rawPassword, salt);
    }

    @Override
    public void encode(String rawPassword, int iterations, CredentialModel credential) {
        if (iterations == -1) {
            iterations = defaultIterations;
        }

        String salt = BCrypt.gensalt(iterations);
        String password = BCrypt.hashpw(rawPassword, salt);

        credential.setAlgorithm(providerId);
        credential.setType(UserCredentialModel.PASSWORD);
        credential.setHashIterations(iterations);
        credential.setValue(password);
        try {
            credential.setSalt(Base64.decode(salt));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean verify(String rawPassword, CredentialModel credential) {
        return BCrypt.checkpw(rawPassword, credential.getValue());
    }
}
