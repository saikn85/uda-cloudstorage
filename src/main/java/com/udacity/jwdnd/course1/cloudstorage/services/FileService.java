package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository _fileRepo;

    public FileService(FileRepository fileRepo) {
        this._fileRepo = fileRepo;
    }

    public File[] getAllUserFiles(int userId){
        return _fileRepo.getFiles(userId);
    }
}
