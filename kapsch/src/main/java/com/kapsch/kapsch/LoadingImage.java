package com.kapsch.kapsch;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoadingImage {
    private ImageRepository profileService;

    public LoadingImage(ImageRepository profileService) {
        this.profileService = profileService;
    }
    @RequestMapping(value = "/imageController/{imageId}")
    @ResponseBody
    @GetMapping
    public void helloWorld(@PathVariable long imageId, HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        byte[] picture = profileService.findById(imageId).get().getImage();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(picture);
        outputStream.close();
    }
}
