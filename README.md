 Automated Event-Driven Image Processing Pipeline on Microsoft Azure

![Azure](https://img.shields.io/badge/Microsoft-Azure-0078D4?logo=microsoftazure&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Azure Functions](https://img.shields.io/badge/Azure%20Functions-Serverless-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)

---

 Overview

This project demonstrates the implementation of a **serverless, event-driven image processing pipeline** using **Microsoft Azure** and **Java**.

When an image is uploaded to **Azure Blob Storage**, a **Java Azure Function** is automatically triggered to generate a thumbnail and store it in a separate Blob Storage container.

The solution showcases how cloud services can automate repetitive tasks using **serverless computing**, **event-driven architecture**, and **cloud storage integration**, eliminating the need for manual image processing.

---

Project Objective

The objective of this project is to demonstrate how Microsoft Azure can be used to automate image processing using a serverless architecture.

Rather than manually creating thumbnails after every image upload, the application automatically detects newly uploaded images, processes them using Azure Functions, and stores the generated thumbnails in a dedicated container.

This project highlights the practical application of cloud computing principles including:

- Serverless Computing
- Event-Driven Architecture
- Cloud Storage
- Cloud Automation
- Java Cloud Development

---

 Problem Statement

Many applications store large image files uploaded by users. Displaying these images directly can increase loading times and consume unnecessary bandwidth.

Creating thumbnails manually is:

- Time-consuming
- Repetitive
- Difficult to scale
- Error-prone

This project solves that problem by automatically generating thumbnails whenever a new image is uploaded.

---

 Solution

The application automatically performs the following workflow:

1. User uploads an image to Azure Blob Storage.
2. Azure Blob Storage raises a Blob Created event.
3. A Java Azure Function is triggered automatically.
4. The image is resized into a thumbnail.
5. The thumbnail is uploaded to a separate Blob Storage container.
6. Azure Monitor records the execution logs.

No manual intervention is required.

---

 Architecture

I will add the (Architecture diagram  once the project is complete )

```
User
 │
 ▼
Azure Blob Storage (uploads)
 │
 ▼
Blob Trigger
 │
 ▼
Azure Function (Java)
 │
 ▼
Thumbnail Generator
 │
 ▼
Azure Blob Storage (thumbnails)
 │
 ▼
Azure Monitor


---

 Features

- Serverless Architecture
- Event-Driven Processing
- Automatic Thumbnail Generation
- Azure Blob Storage Integration
- Java Azure Functions
- Azure Monitor Logging
- Scalable Cloud Workflow
- Automated Image Processing



 Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Application Development |
| Azure Functions | Serverless Compute |
| Azure Blob Storage | Image Storage |
| Azure Storage SDK | Blob Operations |
| Thumbnailator | Image Resizing |
| Maven | Dependency Management |
| Azure Monitor | Logging & Monitoring |
| Git & GitHub | Version Control |

---

 Project Structure

Azure-serverless-image-pipeline/
│
├── function_app/
│   ├── pom.xml
│   ├── host.json
│   ├── local.settings.json
│   │
│   └── src/
│       └── main/
│           └── java/
│               └── com/
│                   └── anita/
│                       └── function/
│                           ├── ImageProcessingFunction.java
│                           ├── ThumbnailService.java
│                           ├── StorageService.java
│                           └── ImageUtils.java
│
├── docs/
│
├── screenshots/
│
└── README.md


 Processing Workflow


Image Upload

        │

        ▼

Azure Blob Storage

        │

        ▼

Blob Trigger Event

        │

        ▼

Azure Function (Java)

        │

        ▼

Generate Thumbnail

        │

        ▼

Store Thumbnail

        │

        ▼

Azure Monitor Logs


 Azure Services Used

- Azure Blob Storage
- Azure Functions
- Azure Monitor
- Azure Resource Group



 Screenshots

The following screenshots will be included as development progresses:

- Azure Resource Group
- Azure Storage Account
- Uploads Container
- Thumbnails Container
- Azure Function
- Azure Monitor Logs
- Successful Thumbnail Generation



 Future Improvements

- Support multiple image formats
- Generate multiple thumbnail sizes
- Image compression
- Watermark images
- Metadata extraction
- REST API for image retrieval



Learning Outcomes

Through this project I am gaining practical experience in:

- Microsoft Azure
- Serverless Computing
- Event-Driven Architecture
- Java Cloud Development
- Azure Blob Storage
- Azure Functions
- Cloud Automation
- Monitoring Cloud Applications

WTC-XB2JSFAL
