package com.udacity.jwdnd.course1.cloudstorage.models;

import java.util.Arrays;

public class File {
    private int _fileId;
    private String _filename;
    private String _contenttype;
    private String _filesize;
    private int _userid;
    private byte[] _filedata;

    public File(int _fileId, String _filename, String _contenttype, String _filesize, int _userid, byte[] _filedata) {
        this._fileId = _fileId;
        this._filename = _filename;
        this._contenttype = _contenttype;
        this._filesize = _filesize;
        this._userid = _userid;
        this._filedata = _filedata;
    }

    public int get_fileId() {
        return _fileId;
    }

    public void set_fileId(int _fileId) {
        this._fileId = _fileId;
    }

    public String get_filename() {
        return _filename;
    }

    public void set_filename(String _filename) {
        this._filename = _filename;
    }

    public String get_contenttype() {
        return _contenttype;
    }

    public void set_contenttype(String _contenttype) {
        this._contenttype = _contenttype;
    }

    public String get_filesize() {
        return _filesize;
    }

    public void set_filesize(String _filesize) {
        this._filesize = _filesize;
    }

    public int get_userid() {
        return _userid;
    }

    public void set_userid(int _userid) {
        this._userid = _userid;
    }

    public byte[] get_filedata() {
        return _filedata;
    }

    public void set_filedata(byte[] _filedata) {
        this._filedata = _filedata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (_fileId != file._fileId) return false;
        if (_userid != file._userid) return false;
        if (!_filename.equals(file._filename)) return false;
        if (!_contenttype.equals(file._contenttype)) return false;
        if (!_filesize.equals(file._filesize)) return false;
        return Arrays.equals(_filedata, file._filedata);
    }

    @Override
    public int hashCode() {
        int result = _fileId;
        result = 31 * result + _filename.hashCode();
        result = 31 * result + _contenttype.hashCode();
        result = 31 * result + _filesize.hashCode();
        result = 31 * result + _userid;
        result = 31 * result + Arrays.hashCode(_filedata);
        return result;
    }
}
