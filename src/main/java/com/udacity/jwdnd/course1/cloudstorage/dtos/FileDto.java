package com.udacity.jwdnd.course1.cloudstorage.dtos;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
    private MultipartFile _file;

    public MultipartFile getFile() {
        return _file;
    }

    public void setFile(MultipartFile file) {
        this._file = file;
    }
}
