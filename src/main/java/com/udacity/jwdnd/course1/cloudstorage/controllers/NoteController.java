package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final int _authUserId;
    private final NoteService _notesSvc;

    public NoteController(NoteService notesSvc, UserService userSvc) {
        this._notesSvc = notesSvc;
        this._authUserId = userSvc.getAuthUserId();
    }

    @GetMapping("/{noteId}")
    public String deleteNote(@PathVariable int noteId, Model model) {
        var note = _notesSvc.getNoteById(noteId);
        if (note.getUserId() != _authUserId) {
            model.addAttribute("result_key", "Failure");
            model.addAttribute("result_message", "Unauthorized!");
        } else {
            if(_notesSvc.deleteNote(note.getNoteId(), note.getUserId())){
                model.addAttribute("result_key", "Success");
                model.addAttribute("result_message", "Note Deleted!");
            } else {
                model.addAttribute("result_key", "Failure");
                model.addAttribute("result_message", "Note was not Deleted!");
            }
        }

        return "result";
    }

    @PostMapping()
    public String post(@ModelAttribute("note") Note note, Model model) {
        note.setUserId(this._authUserId);
        if (_notesSvc.addUpdateNote(note)) {
            model.addAttribute("result_key", "Success");
            model.addAttribute("result_message", "Note details were persisted!");
        } else {
            model.addAttribute("result_key", "Failure");
            model.addAttribute("result_message", "Note details were not persisted!");
        }

        return "result";
    }
}
