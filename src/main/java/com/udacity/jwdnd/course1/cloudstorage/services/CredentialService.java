package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dtos.CredDto;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.repositories.CredRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private final CredRepository _credRepo;
    private final EncryptionService _cryptoSvc;

    public CredentialService(CredRepository credRepo, EncryptionService cryptoSvc) {
        this._credRepo = credRepo;
        this._cryptoSvc = cryptoSvc;
    }

    public Set<CredDto> getAllUserCreds(int userId) {
        return Arrays
                .stream(_credRepo.getCredentials(userId))
                .map(cred -> {
                    String decryptedPassword = _cryptoSvc.decryptValue(cred.getPassword(), cred.getKey());
                    return new CredDto(
                            cred.getCredentialId(),
                            cred.getUrl(),
                            cred.getUserName(),
                            decryptedPassword,
                            cred.getPassword());
                }).collect(Collectors.toSet());
    }

    public Credential getCredentialById(int credentialId) {
        return _credRepo.getCredential(credentialId);
    }

    public boolean addUpdateUserCred(CredDto cred, int userId) {
        if (cred.getCredentialId() > 0) {
            var credential = new Credential(
                    cred.getCredentialId(),
                    0,
                    "",
                    "",
                    "",
                    cred.getPassword());
            doCryptoMagic(credential);
            return _credRepo.updateCredential(
                    credential.getCredentialId(),
                    credential.getKey(),
                    credential.getPassword()) > 0;
        } else {
            Credential userCred = new Credential(
                    0,
                    userId,
                    cred.getUrl(),
                    cred.getUsername(),
                    "",
                    cred.getPassword());
            doCryptoMagic(userCred);
            return _credRepo.createCredential(userCred) > 0;
        }
    }

    public boolean deleteCredential(int credentialId, int userId) {
        return _credRepo.deleteCredential(credentialId, userId) > 0;
    }

    private void doCryptoMagic(Credential cred) {
        String password = cred.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = _cryptoSvc.encryptValue(password, encodedKey);

        cred.setPassword(encryptedPassword);
        cred.setKey(encodedKey);
    }
}
