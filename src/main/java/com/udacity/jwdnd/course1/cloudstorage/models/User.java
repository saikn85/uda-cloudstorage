package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
    private int _userid;
    private String _username;
    private String _salt;
    private String _password;
    private String _firstname;
    private String _lastname;

    public User(int _userid, String _username, String _salt, String _password, String _firstname, String _lastname) {
        this._userid = _userid;
        this._username = _username;
        this._salt = _salt;
        this._password = _password;
        this._firstname = _firstname;
        this._lastname = _lastname;
    }

    public int get_userid() {
        return _userid;
    }

    public void set_userid(int _userid) {
        this._userid = _userid;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_salt() {
        return _salt;
    }

    public void set_salt(String _salt) {
        this._salt = _salt;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (_userid != user._userid) return false;
        if (!_username.equals(user._username)) return false;
        if (!_salt.equals(user._salt)) return false;
        if (!_password.equals(user._password)) return false;
        if (!_firstname.equals(user._firstname)) return false;
        return _lastname.equals(user._lastname);
    }

    @Override
    public int hashCode() {
        int result = _userid;
        result = 31 * result + _username.hashCode();
        result = 31 * result + _salt.hashCode();
        result = 31 * result + _password.hashCode();
        result = 31 * result + _firstname.hashCode();
        result = 31 * result + _lastname.hashCode();
        return result;
    }
}
