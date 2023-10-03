package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.repositories.CredRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredRepository _credRepo;

    public CredentialService(CredRepository credRepo) {
        this._credRepo = credRepo;
    }

    public Credential[] getAllUserCreds(int userId){
        return _credRepo.getCredentials(userId);
    }
}
