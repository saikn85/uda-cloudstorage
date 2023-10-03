package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private String _noteTitle;
    private String _noteDescription;
    private int _noteId;
    private int _userId;

    public Note(int noteId, int userId, String noteTitle, String noteDescription) {
        this._noteId = noteId;
        this._userId = userId;
        this._noteTitle = noteTitle;
        this._noteDescription = noteDescription;
    }

    public int getNoteId() {
        return _noteId;
    }

    public void setNoteId(int noteId) {
        this._noteId = noteId;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        this._userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note thatNote = (Note) o;

        if (this._noteId != thatNote._noteId) return false;
        if (this._userId != thatNote._userId) return false;
        if (!this._noteTitle.equals(thatNote._noteTitle)) return false;
        return this._noteDescription.equals(thatNote._noteDescription);
    }

    @Override
    public int hashCode() {
        int result = _noteId;
        result = 31 * result + _userId;
        result = 31 * result + _noteTitle.hashCode();
        result = 31 * result + _noteDescription.hashCode();
        return result;
    }
}
