package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.repositories.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository _noteRepo;

    public NoteService(NoteRepository noteRepo) {
        this._noteRepo = noteRepo;
    }

    public Note[] getAllUserNotes(int userId) {
        return _noteRepo.getNotes(userId);
    }

    public Note getNoteById(int noteId){
        return _noteRepo.getNote(noteId);
    }

    public boolean addUpdateNote(Note note) {
        if (note.getNoteId() > 0)
            return _noteRepo.updateNote(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription()) > 0;
        else
            return _noteRepo.createNote(new Note(
                    0,
                    note.getUserId(),
                    note.getNoteTitle(),
                    note.getNoteDescription())) > 0;
    }

    public boolean deleteNote(int noteId, int userId){
        return _noteRepo.deleteNote(noteId, userId) > 0;
    }
}
