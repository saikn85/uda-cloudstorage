package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    private int _credentialId;
    private String _url;
    private  String _username;
    private  String _key;
    private String _password;
    private int _userid;

    public Credential(int _credentialId, String _url, String _username, String _key, String _password, int _userid) {
        this._credentialId = _credentialId;
        this._url = _url;
        this._username = _username;
        this._key = _key;
        this._password = _password;
        this._userid = _userid;
    }

    public int get_credentialId() {
        return _credentialId;
    }

    public void set_credentialId(int _credentialId) {
        this._credentialId = _credentialId;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_key() {
        return _key;
    }

    public void set_key(String _key) {
        this._key = _key;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
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
        if (!(o instanceof Credential)) return false;

        Credential that = (Credential) o;

        if (_credentialId != that._credentialId) return false;
        if (_userid != that._userid) return false;
        if (!_url.equals(that._url)) return false;
        if (!_username.equals(that._username)) return false;
        if (!_key.equals(that._key)) return false;
        return _password.equals(that._password);
    }

    @Override
    public int hashCode() {
        int result = _credentialId;
        result = 31 * result + _url.hashCode();
        result = 31 * result + _username.hashCode();
        result = 31 * result + _key.hashCode();
        result = 31 * result + _password.hashCode();
        result = 31 * result + _userid;
        return result;
    }
}
