package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    private int _credentialId;
    private int _userId;
    private String _url;
    private String _userName;
    private String _key;
    private String _password;

    public Credential(int credentialId, int userId, String url, String userName, String key, String password) {
        this._credentialId = credentialId;
        this._userId = userId;
        this._url = url;
        this._userName = userName;
        this._key = key;
        this._password = password;
    }

    public int getCredentialId() {
        return _credentialId;
    }

    public void setCredentialId(int credentialId) {
        this._credentialId = credentialId;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        this._userId = userId;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        this._url = url;
    }

    public String getUsername() {
        return _userName;
    }

    public void setUsername(String userName) {
        this._userName = userName;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        this._key = key;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credential)) return false;

        Credential thatCred = (Credential) o;

        if (this._credentialId != thatCred._credentialId) return false;
        if (this._userId != thatCred._userId) return false;
        if (!this._url.equals(thatCred._url)) return false;
        if (!this._userName.equals(thatCred._userName)) return false;
        if (!this._key.equals(thatCred._key)) return false;
        return this._password.equals(thatCred._password);
    }

    @Override
    public int hashCode() {
        int result = _credentialId;
        result = 31 * result + _url.hashCode();
        result = 31 * result + _userName.hashCode();
        result = 31 * result + _key.hashCode();
        result = 31 * result + _password.hashCode();
        result = 31 * result + _userId;
        return result;
    }
}
