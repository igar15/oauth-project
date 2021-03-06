package ru.javaprojects.userstorageprovider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    private KeycloakSession keycloakSession;
    private ComponentModel componentModel;
    private UsersApiService usersApiService;

    public RemoteUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel, UsersApiService usersApiService) {
        this.keycloakSession = keycloakSession;
        this.componentModel = componentModel;
        this.usersApiService = usersApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {
        return null;
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realmModel) {
        User user = usersApiService.getUserDetails(email);
        if (user != null) {
            return createUserModel(email, realmModel);
        }
        return null;
    }

    private UserModel createUserModel(String email, RealmModel realmModel) {
        return new AbstractUserAdapter(keycloakSession, realmModel, componentModel) {
            @Override
            public String getUsername() {
                return email;
            }

            @Override
            public String getEmail() {
                return email;
            }
        };
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialType) {
        if (!supportsCredentialType(credentialType)) {
            return false;
        }
        return !getCredentialStore().getStoredCredentialsByType(realmModel, userModel, credentialType).isEmpty();
    }

    private UserCredentialStore getCredentialStore() {
        return keycloakSession.userCredentialManager();
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = usersApiService.verifyPassword(userModel.getEmail(), credentialInput.getChallengeResponse());
        if (verifyPasswordResponse == null) {
            return false;
        }
        return verifyPasswordResponse.isResult();
    }
}
