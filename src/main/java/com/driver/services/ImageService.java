package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();

        //blog exist

        Image image = new Image(); //create the image
        //set image attributes
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        //add the image to the blog
        blog.getImageList().add(image);

        //saving blog wil save the image too
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        //find the image
        Image image = imageRepository2.findById(id).get();

        //image exist so delete it
       imageRepository2.deleteById(id);

        //find the blog whose image is this
        Blog blog = image.getBlog();

        //remove the image from the blog
        blog.getImageList().remove(image);

    }

    public int countImagesInScreen(Integer id, String screenDimensions)  {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        int indOfX=screenDimensions.indexOf('X');

        int screenH=Integer.parseInt(screenDimensions.substring(0,indOfX));
        int screenW=Integer.parseInt(screenDimensions.substring(indOfX+1));

        //find the image
        Image image=imageRepository2.findById(id).get();

        //image is found , extract its wdith and height
        int indxOfXinImage=image.getDimensions().indexOf('X');

        int imageH=Integer.parseInt(image.getDimensions().substring(0,indxOfXinImage));
        int imageW=Integer.parseInt(image.getDimensions().substring(indxOfXinImage+1));

        return (screenH/imageH) * (screenW/imageW); //num of complete images that fit in the screen of given dimensions
    }
}
