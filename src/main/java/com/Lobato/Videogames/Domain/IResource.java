package com.Lobato.Videogames.Domain;

public interface IResource {
    boolean saveResource();
    String loadResource(String nameResource);
    String getUrl(String resourceName);

}
