package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private int _noteId;
    private int _userId;
    private String _noteTitle;
    private String _noteDescription;

    public Note(int noteId, int userId, String noteTitle, String noteDescription) {
        this._noteId = noteId;
        this._userId = userId;
        this._noteTitle = noteTitle;
        this._noteDescription = noteDescription;
    }

    public int getNoteId() {
        return _noteId;
    }

    public int getUserId() {
        return _userId;
    }

    public String getNoteTitle() {
        return _noteTitle;
    }

    public String getNoteDescription() {
        return _noteDescription;
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
