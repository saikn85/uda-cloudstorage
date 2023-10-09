package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.CredDto;
import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDto;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredController {
    private final CredentialService _credSvc;
    private final UserService _userSvc;

    public CredController(CredentialService credSvc, UserService userSvc) {
        this._credSvc = credSvc;
        this._userSvc = userSvc;
    }

    @GetMapping("/{credId}/delete")
    public String deleteCredential(@PathVariable int credId, Model model) {
        try {
            var cred = _credSvc.getCredentialById(credId);
            if (cred.getUserId() != _userSvc.getAuthUserId()) {
                model.addAttribute("result_key", "Error");
                model.addAttribute("result_message", "Unauthorized!");
            } else {
                if (_credSvc.deleteCredential(cred.getCredentialId(), cred.getUserId())) {
                    model.addAttribute("result_key", "Success");
                    model.addAttribute("result_message", "Credential Deleted!");
                } else {
                    model.addAttribute("result_key", "Failure");
                    model.addAttribute("result_message", "Credential was not Deleted!");
                }
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }

        return "result";
    }

    @PostMapping()
    public String post(@ModelAttribute("cred") CredDto cred, Model model) {
        try {
            if (_credSvc.addUpdateUserCred(cred, _userSvc.getAuthUserId())) {
                model.addAttribute("result_key", "Success");
                model.addAttribute("result_message", "Credential details were persisted!");
            } else {
                model.addAttribute("result_key", "Failure");
                model.addAttribute("result_message", "Credential details were not persisted!");
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }
        return "result";
    }
}
