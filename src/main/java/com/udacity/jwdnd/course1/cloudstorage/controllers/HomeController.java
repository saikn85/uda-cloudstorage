package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/home")
public class HomeController {
    private final CredentialService _credSvc;
    private final FileService _fileSvc;
    private final NoteService _noteSvc;
    private final UserService _userSvc;

    public HomeController(CredentialService credSvc, FileService fileSvc, NoteService noteSvc, UserService userSvc) {
        _credSvc = credSvc;
        _fileSvc = fileSvc;
        _noteSvc = noteSvc;
        _userSvc = userSvc;
    }

    @GetMapping()
    public String get(Model model) {

        model.addAttribute("credentials", _credSvc.getAllUserCreds(_userSvc.getAuthUserId()));
        model.addAttribute("files", _fileSvc.getAllUserFiles(_userSvc.getAuthUserId()));
        model.addAttribute("notes", _noteSvc.getAllUserNotes(_userSvc.getAuthUserId()));

        return "home";
    }
}
