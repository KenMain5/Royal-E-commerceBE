package com.Royal.Main.service;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.util.List;

public interface MerchandiseImagesService {
    List<Resource> retrieveImages(int[] idArray);
    Resource getSingleImage(int imageId) throws MalformedURLException;
}
