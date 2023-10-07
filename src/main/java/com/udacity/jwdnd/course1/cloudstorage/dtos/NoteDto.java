package com.udacity.jwdnd.course1.cloudstorage.dtos;

public class NoteDto {
    private String _noteTitle;
    private String _noteDescription;
    private int _noteId;

    public NoteDto(int noteId, String noteTitle, String noteDescription) {
        this._noteId = noteId;
        this._noteTitle = noteTitle;
        this._noteDescription = noteDescription;
    }

    public int getNoteId() {
        return _noteId;
    }

    public void setNoteId(int noteId) {
        this._noteId = noteId;
    }

    public String getNoteTitle() {
        return _noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this._noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return _noteDescription;
    }

    public void setNoteDescription(String _noteDescription) {
        this._noteDescription = _noteDescription;
    }
}
