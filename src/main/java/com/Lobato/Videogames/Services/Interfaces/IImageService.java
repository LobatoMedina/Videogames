package com.Lobato.Videogames.Services.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IImageService {
    Resource returnImageByName(String name);
}
