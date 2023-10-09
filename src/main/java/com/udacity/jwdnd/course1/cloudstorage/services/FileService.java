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

    public File getFileById(int fileId){
        return _fileRepo.getFile(fileId);
    }

    public int addFile(File file){
        var existingFile =  _fileRepo.getFileByFileName(file.getFileName(), file.getUserId());
        if(existingFile != null)
            return -1;

        return _fileRepo.createFile(file);
    }

    public boolean deleteFile(int fileId, int userId){
        return _fileRepo.deleteFile(fileId, userId) > 0;
    }
}
