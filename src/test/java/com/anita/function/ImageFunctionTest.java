package com.anita.function;

import com.anita.ImageFunction;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public  class ImageFunctionTest {

    @Test
    void shouldCreate200x200Thumbnail() throws Exception {

        BufferedImage original = new BufferedImage(
                800,
                600,
                BufferedImage.TYPE_INT_RGB
        );

        ByteArrayOutputStream inputOutput = new ByteArrayOutputStream();
        ImageIO.write(original, "jpg", inputOutput);

        ImageFunction function = new ImageFunction();

        byte[] thumbnail = function.createThumbnail(inputOutput.toByteArray());

        BufferedImage result = ImageIO.read(
                new ByteArrayInputStream(thumbnail)
        );

        assertEquals(200, result.getWidth());
        assertEquals(200, result.getHeight());
    }
}