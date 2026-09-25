// Test if the image is aploaded succesful in the blob storage
// image can be resizes and downloaded correctly
// Test that Blob storage event is parsed correctly
// Test that the generated thumbnail is uploaded to the thumbnails Blob Storage container.
// Test that the Azure Function executes when a new image is uploaded.
// Test that image processing completes without errors for a valid image.
// Test that an invalid  image
package com.anita;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageFunctionTest {

    private ImageFunction function;
    private ExecutionContext context;
    private OutputBinding<byte[]> thumbnail;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        function = new ImageFunction();
        context = mock(ExecutionContext.class);
        when(context.getLogger()).thenReturn(Logger.getLogger("test"));
        thumbnail = mock(OutputBinding.class);
    }

    // ---------- helpers ----------

    private static byte[] makeImage(int width, int height, String format) throws Exception {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, format, out);
        return out.toByteArray();
    }

    private static BufferedImage read(byte[] bytes) throws Exception {
        return ImageIO.read(new ByteArrayInputStream(bytes));
    }

    // ---------- run(): the trigger entry point ----------

    @Test
    void run_writesThumbnailToOutputBinding() throws Exception {
        byte[] original = makeImage(800, 600, "png");

        function.run(original, "photo.png", thumbnail, context);

        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(thumbnail, times(1)).setValue(captor.capture());

        BufferedImage result = read(captor.getValue());
        assertNotNull(result, "Output should be a valid image");
        assertTrue(result.getWidth() <= 200 && result.getHeight() <= 200);
    }

    @Test
    void run_throwsAndWritesNothing_whenContentIsNotAnImage() {
        byte[] garbage = "this is not an image".getBytes();

        assertThrows(RuntimeException.class,
                () -> function.run(garbage, "bad.txt", thumbnail, context));

        verify(thumbnail, never()).setValue(any());
    }

    @Test
    void run_throwsAndWritesNothing_whenContentIsEmpty() {
        assertThrows(RuntimeException.class,
                () -> function.run(new byte[0], "empty.png", thumbnail, context));

        verify(thumbnail, never()).setValue(any());
    }

    // ---------- createThumbnail() ----------

    @Test
    void createThumbnail_shrinksLandscapeImageKeepingAspectRatio() throws Exception {
        byte[] result = function.createThumbnail(makeImage(800, 400, "png"));

        BufferedImage img = read(result);
        assertEquals(200, img.getWidth());
        assertEquals(100, img.getHeight());
    }

    @Test
    void createThumbnail_shrinksPortraitImageKeepingAspectRatio() throws Exception {
        byte[] result = function.createThumbnail(makeImage(400, 800, "png"));

        BufferedImage img = read(result);
        assertEquals(100, img.getWidth());
        assertEquals(200, img.getHeight());
    }

    @Test
    void createThumbnail_keepsJpegFormat() throws Exception {
        byte[] result = function.createThumbnail(makeImage(600, 600, "jpg"));

        // JPEG files start with FF D8
        assertEquals((byte) 0xFF, result[0]);
        assertEquals((byte) 0xD8, result[1]);
    }

    @Test
    void createThumbnail_rejectsNullAndEmpty() {
        assertThrows(IllegalArgumentException.class, () -> function.createThumbnail(null));
        assertThrows(IllegalArgumentException.class, () -> function.createThumbnail(new byte[0]));
    }

    // ---------- uploadThumbnail() ----------

    @Test
    void uploadThumbnail_setsValueOnOutputBinding() {
        byte[] data = {1, 2, 3};

        function.uploadThumbnail(thumbnail, data);

        verify(thumbnail).setValue(data);
    }

    @Test
    void uploadThumbnail_rejectsEmptyContent() {
        assertThrows(IllegalArgumentException.class,
                () -> function.uploadThumbnail(thumbnail, new byte[0]));
        verify(thumbnail, never()).setValue(any());
    }
}