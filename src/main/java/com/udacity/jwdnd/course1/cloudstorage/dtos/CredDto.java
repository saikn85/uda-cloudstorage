package com.udacity.jwdnd.course1.cloudstorage.dtos;

public class CredDto {
    private int _credentialId;
    private String _url;
    private String _userName;
    private String _password;
    private final String _cryptoPassword;

    public CredDto(int credentialId, String url, String userName, String password, String cryptoPassword) {
        this._credentialId = credentialId;
        this._url = url;
        this._userName = userName;
        this._password = password;
        this._cryptoPassword = cryptoPassword;
    }

    public int getCredentialId() {
        return _credentialId;
    }

    public void setCredentialId(int credentialId) {
        this._credentialId = credentialId;
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

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getCryptoPassword() {
        return this._cryptoPassword;
    }
}
