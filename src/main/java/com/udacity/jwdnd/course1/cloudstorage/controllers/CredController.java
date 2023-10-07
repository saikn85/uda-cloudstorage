package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredController {
    private final CredentialService _credSvc;
    private int _authUserId;

    public CredController(CredentialService _credSvc, UserService userSvc) {
        this._credSvc = _credSvc;
        this._authUserId = userSvc.getAuthUserId();
    }

    @PostMapping()
    public String post(){

        return "result";
    }
}
