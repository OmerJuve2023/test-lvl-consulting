package omer.solutions.testlvlconsulting.utils;

import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Images {

    public static byte[] uploadImageDefault(String file) throws IOException {
        ClassPathResource resource = new ClassPathResource(file);
        BufferedImage image = ImageIO.read(resource.getInputStream());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
