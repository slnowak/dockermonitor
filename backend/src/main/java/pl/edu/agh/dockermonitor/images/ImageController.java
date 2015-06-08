package pl.edu.agh.dockermonitor.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/docker")
public class ImageController {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public Collection<DockerImage> sayHello() {
        return imageRepository.loadImages();
    }
}
