package com.kapsch.kapsch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class EndpointImage {

    public EndpointImage(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    ImageRepository imageRepository;

    @GetMapping
    String showInputPage(){
        return "insertImage";
    }

    @ModelAttribute("newphoto")
    Image giveObject(){
        return new Image();
    }

    @PostMapping("/addphoto")
    String addParticipant(@RequestParam("file") MultipartFile file, Image image, RedirectAttributes redirectAttributes) {
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
            imageRepository.save(image);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
            return "You failed to upload because " + " => " + e.getMessage();
        }
        return "redirect:/";
    }

    @ModelAttribute("images")
    List<Image> show(){
        return imageRepository.findAll();
    }




}
