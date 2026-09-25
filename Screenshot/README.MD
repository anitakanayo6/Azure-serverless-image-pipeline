# Project Screenshots

Visual documentation of the **Azure Serverless Image Processing Pipeline**.

---

## Azure Resources and Subscription

![img.png](img.png)

## Azure Blob Storage —  Containers

![Screenshot_23-9-2026_235638_portal.azure.com.jpeg](../../../Downloads/Screenshot_23-9-2026_235638_portal.azure.com.jpeg)


## Azure Blob Storage — Uploads Container

![img_1.png](img_1.png)![Azure Uploads Container](azure-uploads-container.png)

> Upload container containing the original images submitted for processing.

---

## Azure Blob Storage — Thumbnails Container

![img_2.png](img_2.png)![Azure Thumbnails Container](azure-thumbnails-container.png)

> Output container where the processed thumbnails are intended to be stored.

---

##  Azure Function Overview

![img_3.png](img_3.png)![Azure Function Overview](azure-function-overview.png)

> Azure Function App running the `ImageFunction` serverless image-processing function.

---

## Blob Trigger Integration

![img_5.png](img_5.png)![img_4.png](img_4.png)![Blob Trigger Integration](azure-function-integration.png)

> Blob Trigger configured to detect new images uploaded to `uploads/{name}` and send the processed image to `thumbnails/{name}`.



