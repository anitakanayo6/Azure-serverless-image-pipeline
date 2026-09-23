package com.anita;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class ImageFunction {

    static final int THUMBNAIL_SIZE = 200;

    /**
     * Triggered when a blob is added to the "uploads" container.
     * Creates a thumbnail and writes it to the "thumbnails" container
     * (same blob name) through the output binding.
     */
    @FunctionName("ImageFunction")
    public void run(
            @BlobTrigger(name = "image", path = "uploads/{name}",
                    dataType = "binary", connection = "AzureWebJobsStorage") byte[] content,
            @BindingName("name") String name,
            @BlobOutput(name = "thumbnail", path = "thumbnails/{name}",
                    connection = "AzureWebJobsStorage") OutputBinding<byte[]> thumbnail,
            final ExecutionContext context) {

        Logger log = context.getLogger();
        log.info("Blob trigger fired for: " + name
                + " (" + (content == null ? 0 : content.length) + " bytes)");

        try {
            byte[] thumbnailBytes = createThumbnail(content);
            uploadThumbnail(thumbnail, thumbnailBytes);
            log.info("Thumbnail created for: " + name);
        } catch (Exception e) {
            log.severe("Failed to create thumbnail for " + name + ": " + e.getMessage());
            // Rethrow so the failure shows up in Invocations and the
            // blob trigger's retry / poison-blob handling kicks in.
            throw new RuntimeException("Thumbnail creation failed for " + name, e);
        }
    }

    /**
     * Resizes the image to fit within 200x200, keeping the aspect ratio.
     * The output keeps the original image format (jpg, png, ...).
     */
    public byte[] createThumbnail(byte[] image) throws IOException {
        if (image == null || image.length == 0) {
            throw new IllegalArgumentException("Image content is empty");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Thumbnails.of(new ByteArrayInputStream(image))
                .size(THUMBNAIL_SIZE, THUMBNAIL_SIZE)
                .toOutputStream(output);

        return output.toByteArray();
    }

    /**
     * Hands the thumbnail to the blob output binding. The Functions host
     * writes it to thumbnails/{name} when the function completes successfully.
     */
    public void uploadThumbnail(OutputBinding<byte[]> thumbnail, byte[] thumbnailBytes) {
        if (thumbnailBytes == null || thumbnailBytes.length == 0) {
            throw new IllegalArgumentException("Thumbnail content is empty");
        }
        thumbnail.setValue(thumbnailBytes);
    }
}