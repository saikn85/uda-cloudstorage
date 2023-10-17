package com.udacity.jwdnd.course1.cloudstorage.exmiddleware;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExMiddleware {

    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class})
    public String handleMaxSizeException(Model model){
        model.addAttribute("result_key", "Error");
        model.addAttribute("result_message", "File upload failed - Size Limit Breached!");
        return "error";
    }
}
