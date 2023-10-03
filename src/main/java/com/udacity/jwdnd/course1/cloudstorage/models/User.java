package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
    private int _userId;
    private String _username;
    private String _salt;
    private String _password;
    private String _firstName;
    private String _lastName;

    public User(int userid, String username, String salt, String password, String firstName, String lastName) {
        this._userId = userid;
        this._username = username;
        this._salt = salt;
        this._password = password;
        this._firstName = firstName;
        this._lastName = lastName;
    }

    public int getUserId() {
        return _userId;
    }
    public void setUserId(int userId) {
        this._userId = userId;
    }

    public String getUsername() {
        return _username;
    }

    public String getSalt() {
        return _salt;
    }

    public String getPassword() {
        return _password;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User thatUser = (User) o;

        if (this._userId != thatUser._userId) return false;
        if (!this._username.equals(thatUser._username)) return false;
        if (!this._salt.equals(thatUser._salt)) return false;
        if (!this._password.equals(thatUser._password)) return false;
        if (!this._firstName.equals(thatUser._firstName)) return false;
        return this._lastName.equals(thatUser._lastName);
    }

    @Override
    public int hashCode() {
        int result = _userId;
        result = 31 * result + _username.hashCode();
        result = 31 * result + _salt.hashCode();
        result = 31 * result + _password.hashCode();
        result = 31 * result + _firstName.hashCode();
        result = 31 * result + _lastName.hashCode();
        return result;
    }
}
