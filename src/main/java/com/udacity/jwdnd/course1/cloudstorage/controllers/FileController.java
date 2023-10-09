package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService _fileSvc;
    private final UserService _userSvc;

    public FileController(FileService fileSvc, UserService userSvc) {
        this._fileSvc = fileSvc;
        this._userSvc = userSvc;
    }

    @GetMapping("/{fileId}/view")
    public ResponseEntity<Resource> viewFile(@PathVariable int fileId, Model model) throws URISyntaxException {
        try {
            var file = _fileSvc.getFileById(fileId);
            // https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
            return ResponseEntity.ok()
                    .contentType(
                            MediaType.parseMediaType(file.getContentType()))
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(new ByteArrayResource(file.getFileData()));
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
            return (ResponseEntity<Resource>) ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .location(new URI("result"));
        }
    }

    @GetMapping("/{fileId}/delete")
    public String deleteFile(@PathVariable int fileId, Model model) {
        try {
            var file = _fileSvc.getFileById(fileId);
            if (file.getUserId() != _userSvc.getAuthUserId()) {
                model.addAttribute("result_key", "Error");
                model.addAttribute("result_message", "Unauthorized!");
            } else {
                if (_fileSvc.deleteFile(file.getFileId(), file.getUserId())) {
                    model.addAttribute("result_key", "Success");
                    model.addAttribute("result_message", "File Deleted!");
                } else {
                    model.addAttribute("result_key", "Failure");
                    model.addAttribute("result_message", "File was not Deleted!");
                }
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }

        return "result";
    }

    @PostMapping()
    public String post(@RequestParam("fileUpload") MultipartFile file, Model model) {
        try {
            var newfile = new File(
                    0,
                    _userSvc.getAuthUserId(),
                    file.getOriginalFilename(),
                    file.getContentType(),
                    String.format("%d", file.getSize()),
                    file.getBytes());
            int result = _fileSvc.addFile(newfile);

            if (result > 0) {
                model.addAttribute("result_key", "Success");
                model.addAttribute("result_message", "File details were persisted!");
                return "result";
            }

            if (result < 0) {
                model.addAttribute("result_key", "Failure");
                model.addAttribute("result_message", "File details were not persisted - duplicate file!");
                return "result";
            }
        } catch (Exception ex) {
            model.addAttribute("result_key", "Error");
            model.addAttribute("result_message", "Internal Server Error");
        }
        return "result";
    }
}
