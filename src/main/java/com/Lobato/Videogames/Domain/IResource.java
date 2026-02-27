package com.Lobato.Videogames.Domain;

public interface IResource {
    boolean saveResource();
    String loadResource();
    String createUrl(String resourceName);

}
