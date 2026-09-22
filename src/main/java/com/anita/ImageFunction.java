// write the java code that will trigger the azure function when the image is aploaded  to the uploads Blob Storage container. 

// Methods :
// run() - to be triggered when the image is being uploaded to the storage container
// createThumbnail() - resize the image to thumbnail 
// uploadThumbnail() - upoload the thumbnail in the blob storage container

// wwrite Tests for the actualy code for triggering

package com.anita;

import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageFunction {

    @FunctionName("ImageFunction")
    public void run(

            @BlobTrigger(
                    name = "image",
                    path = "uploads/{name}",
                    dataType = "binary",
                    connection = "AzureWebJobsStorage"
            ) byte[] image,

            @BlobOutput(
                    name = "thumbnail",
                    path = "thumbnails/{name}",
                    connection = "AzureWebJobsStorage"
            ) OutputBinding<byte[]> thumbnailOutput

    ) throws Exception {

        byte[] thumbnail = createThumbnail(image);

        thumbnailOutput.setValue(thumbnail);
    }

    public byte[] createThumbnail(byte[] image) throws Exception {

        ByteArrayInputStream input = new ByteArrayInputStream(image);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Thumbnails.of(input)
                .forceSize(200, 200)
                .toOutputStream(output);

        return output.toByteArray();
    }
}