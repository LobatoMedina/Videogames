package com.Lobato.Videogames.Controllers.APIControllers;

import com.Lobato.Videogames.Services.Implementations.ImageService;
import com.Lobato.Videogames.Services.Interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    IImageService img;
    @GetMapping("/{name:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String name){
        try{
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE).body(img.returnImageByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
