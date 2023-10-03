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

    public UserService(UserRepository userRepo, HashService hashService) {
        this._userRepo = userRepo;
        this._hashService = hashService;
    }

    public boolean validateUser(String userName, String password){
        User user = _userRepo.getUserByUsername(userName);
        if (user == null) return false;
        String encodedSalt = user.getSalt();
        String hashedPassword = _hashService.getHashedValue(password, encodedSalt);

        return user.getPassword().equals(hashedPassword);
    }

    public boolean checkUserNameAvailability(String userName){
        return _userRepo.getUserByUsername(userName) == null;
    }

    public int createUser(User user){
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
}
