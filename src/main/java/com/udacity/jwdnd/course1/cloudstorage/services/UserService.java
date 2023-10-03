package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final UserRepository _userRepo;
    private final HashService _hashService;
    private int _authUserId;

    public UserService(UserRepository userRepo, HashService hashService) {
        this._userRepo = userRepo;
        this._hashService = hashService;
        this._authUserId = -1;
    }

    public boolean validateUser(String userName, String password) {
        User user = _userRepo.getUserByUsername(userName);
        if (user == null) return false;

        String encodedSalt = user.getSalt();
        String hashedPassword = _hashService.getHashedValue(password, encodedSalt);

        if (user.getPassword().equals(hashedPassword)) {
            this._authUserId = user.getUserId();
            return true;
        }

        return false;
    }

    public boolean checkUserNameAvailability(String userName) {
        return _userRepo.getUserByUsername(userName) == null;
    }

    public int createUser(User user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = _hashService.getHashedValue(user.getPassword(), encodedSalt);
        return _userRepo.createUser(new User(
                0,
                user.getUsername(),
                encodedSalt,
                hashedPassword,
                user.getFirstName(),
                user.getLastName()));
    }

    public int getAuthUserId() {
        return this._authUserId;
    }
}
