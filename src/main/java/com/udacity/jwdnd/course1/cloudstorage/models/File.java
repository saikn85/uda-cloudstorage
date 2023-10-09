package com.udacity.jwdnd.course1.cloudstorage.models;

import java.util.Arrays;

public class File {
    private int _fileId;
    private int _userId;
    private String _fileName;
    private String _contentType;
    private String _fileSize;
    private byte[] _fileData;

    public File(int fileId, int userId, String fileName, String contentType, String fileSize, byte[] fileData) {
        this._fileId = fileId;
        this._userId = userId;
        this._fileName = fileName;
        this._contentType = contentType;
        this._fileSize = fileSize;
        this._fileData = fileData;
    }

    public int getFileId() {
        return _fileId;
    }

    public void setFileId(int fileId) {
        this._fileId = fileId;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        this._userId = userId;
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        this._fileName = fileName;
    }

    public String getContentType() {
        return _contentType;
    }

    public void setContentType(String contentType) {
        this._contentType = contentType;
    }

    public String getFileSize() {
        return _fileSize;
    }

    public void setFileSize(String fileSize) {
        this._fileSize = fileSize;
    }

    public byte[] getFileData() {
        return _fileData;
    }

    public void setFileData(byte[] fileData) {
        this._fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File thatFile = (File) o;

        if (this._fileId != thatFile._fileId) return false;
        if (this._userId != thatFile._userId) return false;
        if (!this._fileName.equals(thatFile._fileName)) return false;
        if (!this._contentType.equals(thatFile._contentType)) return false;
        if (!this._fileSize.equals(thatFile._fileSize)) return false;
        return Arrays.equals(this._fileData, thatFile._fileData);
    }

    @Override
    public int hashCode() {
        int result = _fileId;
        result = 31 * result + _userId;
        result = 31 * result + _fileName.hashCode();
        result = 31 * result + _contentType.hashCode();
        result = 31 * result + _fileSize.hashCode();
        result = 31 * result + Arrays.hashCode(_fileData);
        return result;
    }
}
