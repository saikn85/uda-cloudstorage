package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private int _noteid;
    private String _notetitle;
    private String _notedescription;
    private int _userid;

    public Note(int _noteid, String _notetitle, String _notedescription, int _userid) {
        this._noteid = _noteid;
        this._notetitle = _notetitle;
        this._notedescription = _notedescription;
        this._userid = _userid;
    }

    public int get_noteid() {
        return _noteid;
    }

    public void set_noteid(int _noteid) {
        this._noteid = _noteid;
    }

    public String get_notetitle() {
        return _notetitle;
    }

    public void set_notetitle(String _notetitle) {
        this._notetitle = _notetitle;
    }

    public String get_notedescription() {
        return _notedescription;
    }

    public void set_notedescription(String _notedescription) {
        this._notedescription = _notedescription;
    }

    public int get_userid() {
        return _userid;
    }

    public void set_userid(int _userid) {
        this._userid = _userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (_noteid != note._noteid) return false;
        if (_userid != note._userid) return false;
        if (!_notetitle.equals(note._notetitle)) return false;
        return _notedescription.equals(note._notedescription);
    }

    @Override
    public int hashCode() {
        int result = _noteid;
        result = 31 * result + _notetitle.hashCode();
        result = 31 * result + _notedescription.hashCode();
        result = 31 * result + _userid;
        return result;
    }
}
