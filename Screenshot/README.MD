# 📸 Project Screenshots

Visual documentation of the **Azure Serverless Image Processing Pipeline**.

---

## 📥 Azure Blob Storage — Uploads Container

![Azure Uploads Container](azure-uploads-container.png)

> Upload container containing the original images submitted for processing.

---

## 📤 Azure Blob Storage — Thumbnails Container

![Azure Thumbnails Container](azure-thumbnails-container.png)

> Output container where the processed thumbnails are intended to be stored.

---

## ⚡ Azure Function Overview

![Azure Function Overview](azure-function-overview.png)

> Azure Function App running the `ImageFunction` serverless image-processing function.

---

## 🔗 Blob Trigger Integration

![Blob Trigger Integration](azure-function-integration.png)

> Blob Trigger configured to detect new images uploaded to `uploads/{name}` and send the processed image to `thumbnails/{name}`.

---

## ☁️ Azure Function Configuration

![Azure Function Configuration](azure-function-configuration.png)

> Function App configuration showing the Java 21 runtime and Azure Functions environment.

---

## 💻 Image Processing Code

![Image Function Code](image-function-code.png)

> Java `ImageFunction` using Thumbnailator to resize uploaded images to 200 × 200 pixels.

---

## 🧪 Automated Test Results

![Test Results](test-results.png)

> Automated tests covering image processing and error handling. The latest test run completed with **9 tests, 0 failures, and 0 errors**.

---

## 🚀 Azure Deployment

![Azure Deployment](azure-deployment.png)

> Maven deployment output showing the Java Azure Function successfully deployed to `ImageFunction-run`.

---

## 🏗️ Azure Architecture

![Azure Architecture](azure-architecture.png)

> Architecture showing the flow from the `uploads` Blob container through the Java Azure Function to the `thumbnails` container.
