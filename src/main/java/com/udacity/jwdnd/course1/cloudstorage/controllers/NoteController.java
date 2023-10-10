package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDto;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final UserService _userSvc;
    private final NoteService _notesSvc;

    public NoteController(NoteService notesSvc, UserService userSvc) {
        this._notesSvc = notesSvc;
        this._userSvc = userSvc;
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable int noteId, Model model) {
        try {
            var note = _notesSvc.getNoteById(noteId);
            if (note.getUserId() != _userSvc.getAuthUserId()) {
                model.addAttribute("result_key", "Error");
                model.addAttribute("result_message", "Unauthorized!");
            } else {
                if (_notesSvc.deleteNote(note.getNoteId(), note.getUserId())) {
                    model.addAttribute("result_key", "Success");
                    model.addAttribute("result_message", "Note Deleted!");
                } else {
                    model.addAttribute("result_key", "Failure");
                    model.addAttribute("result_message", "Note was not Deleted!");
                }
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }

        return "result";
    }

    @PostMapping()
    public String post(@ModelAttribute("note") NoteDto note, Model model) {
        try {
            if (_notesSvc.addUpdateNote(note, _userSvc.getAuthUserId())) {
                model.addAttribute("result_key", "Success");
                model.addAttribute("result_message", "Note details were persisted!");
            } else {
                model.addAttribute("result_key", "Failure");
                model.addAttribute("result_message", "Note details were not persisted!");
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }
        return "result";
    }
}
