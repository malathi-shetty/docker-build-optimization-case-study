# Docker Build Optimization & DevSecOps CI/CD Case Study

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-orange)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI%2FCD-black)
![GitLab CI](https://img.shields.io/badge/GitLab-CI%2FCD-orange)
![SonarCloud](https://img.shields.io/badge/SonarCloud-Code_Quality-blue)
![Trivy](https://img.shields.io/badge/Trivy-Security-green)
![Nexus](https://img.shields.io/badge/Nexus-Artifacts-purple)
![Tomcat](https://img.shields.io/badge/Tomcat-Deployment-yellow)

---

# Overview

This project was developed as part of a **real-world Docker Build Optimization case study** where the objective was to improve Docker build performance, reduce Docker image size, accelerate deployments, and design a reusable containerization strategy for enterprise applications.

The business scenario involves a software company deploying **20–30 applications daily**. As deployment frequency increased, Docker image build times became slower, image sizes grew significantly, storage costs increased, and deployments became less efficient.

Initially, the project focused on **Docker Build Optimization**, but it gradually evolved into a complete **DevSecOps CI/CD solution** by integrating automated builds, code quality analysis, security scanning, artifact management, container registry publishing, and automated deployment.

The final solution demonstrates how Docker optimization techniques combined with DevSecOps best practices improve:

- Docker image optimization
- Build performance
- Deployment consistency
- Code quality
- Security validation
- Artifact management
- Release automation
- Enterprise deployment workflows

---

# Assignment / Business Case

## Client Build Optimization Challenge

### Business Problem

A software company builds and deploys **20–30 applications every day**.

As the number of deployments increased:

- Docker build times became slower.
- Docker image sizes increased.
- Storage costs continued to grow.
- Image push and pull operations became slower.
- Deployments took longer.
- Manual quality validation delayed releases.
- Manual security checks increased operational effort.

---

## My Role

As a **DevOps Engineer**, my responsibility was to optimize the Docker build process and automate the software delivery pipeline.

---

## Objectives

- Analyze the existing Docker build process
- Reduce Docker image build time
- Reduce Docker image size
- Improve deployment speed
- Create a reusable Docker strategy
- Automate software delivery using CI/CD
- Introduce automated quality gates
- Integrate automated security scanning

---

## Deliverables

The final solution includes:

- Optimized Dockerfile
- Multi-Stage Docker Build
- Docker Compose Configuration
- Docker BuildKit Integration
- Build-Time Comparison Report
- Docker Image Size Comparison Report
- SonarCloud Code Analysis
- Trivy Security Scan
- Nexus Artifact Repository
- Docker Hub Image Publishing
- Apache Tomcat Deployment
- Jenkins Pipeline
- GitHub Actions Workflow
- GitLab CI/CD Pipeline
- Best Practices Documentation

---

# Business Problem

## Existing Challenges

- Slow Docker image builds
- Large Docker images
- Increased storage consumption
- Slow image push and pull operations
- Delayed deployments
- Manual code quality validation
- Manual security validation
- Manual artifact management
- Manual deployment process

---

## Goals

The solution was designed to achieve the following goals:

- Reduce Docker image size
- Improve build performance
- Improve deployment speed
- Standardize Docker builds
- Automate CI/CD pipelines
- Introduce DevSecOps practices
- Improve deployment reliability
- Build a reusable Docker strategy for future projects

---

# Solution Architecture

```text
┌─────────────────────┐
│     Developer       │
│  Code Push/Commit   │
└──────────┬──────────┘
           │
           ▼

┌─────────────────────────────┐
│ GitHub / GitLab Repository  │
└──────────┬──────────────────┘
           │
           ▼

┌────────────────────────────────────────────┐
│            CI/CD Pipeline Layer            │
│                                            │
│  • Jenkins Pipeline                        │
│  • GitHub Actions Workflow                 │
│  • GitLab CI/CD Pipeline                   │
└───────────────┬────────────────────────────┘
                │
                ▼

┌──────────────────────────┐
│      Maven Build         │
└──────────┬───────────────┘
           │
           ▼

┌──────────────────────────┐
│      Unit Testing        │
└──────────┬───────────────┘
           │
           ▼

┌──────────────────────────────┐
│      SonarCloud Analysis      │
│                               │
│ • Code Quality Analysis       │
│ • Code Smell Detection        │
│ • Quality Gate Validation     │
└──────────┬────────────────────┘
           │
           ▼

┌──────────────────────────────┐
│        Docker Build          │
│                              │
│ • Docker Layer Caching       │
│ • Docker BuildKit            │
│ • Multi-Stage Docker Build   │
└──────────┬───────────────────┘
           │
           ▼

┌──────────────────────────────┐
│        Trivy Scan            │
│                              │
│ • HIGH Vulnerabilities       │
│ • CRITICAL Vulnerabilities   │
└──────────┬───────────────────┘
           │
           ▼

  ┌────────────┬─────────────┬
  ▼            ▼             ▼

┌──────────┐ ┌────────────┐ ┌───────────────┐
│ Nexus    │ │ Docker Hub │ │ Build Reports │
│ Artifact │ │ Registry   │ │ Artifacts     │
│ Storage  │ │            │ │               │
└────┬─────┘ └─────┬──────┘ └───────────────┘
     │             │
     ▼             ▼

┌───────────────────────────────┐
│      Apache Tomcat            │
│                               │
│  WAR Deployment               │
│  Port : 8080                  │
└──────────────┬────────────────┘
               │
               ▼

┌───────────────────────────────┐
│     Application Available     │
│        To End Users           │
└───────────────────────────────┘
```

---

# Project Structure

```text
docker-build-optimization/

├── app/
│   ├── pom.xml
│   └── src/
│
├── Dockerfile.v1
├── Dockerfile.v2
├── Dockerfile.multistage
│
├── Jenkinsfile
├── .github/
│   └── workflows/
│
├── .gitlab-ci.yml
│
├── docker-compose.yml
│
├── reports/
│   ├── build-comparison.md
│   ├── image-size-comparison.md
│   ├── sonar-report
│   └── trivy-report
│
├── images/
│
└── README.md
```

---

# Project Implementation Roadmap

The project was implemented in three major phases:

## Phase 1

Docker Build Optimization

- Dockerfile.v1
- Dockerfile.v2
- Dockerfile.multistage
- Docker Layer Caching
- Docker BuildKit
- Docker Compose

---

## Phase 2

DevSecOps Integration

- SonarCloud
- Trivy
- Nexus Repository
- Docker Hub

---

## Phase 3

CI/CD Automation

- Jenkins Pipeline
- GitHub Actions Workflow
- GitLab CI/CD Pipeline
- Apache Tomcat Deployment

---

The following sections explain each implementation phase in detail.

# Optimization Journey

The project was implemented incrementally to understand the impact of each Docker optimization technique before integrating a complete DevSecOps CI/CD pipeline.

---

# Phase 1 – Docker Build Optimization

The first phase focused on analyzing the existing Docker build process and reducing Docker image size while improving build efficiency.

---

## Dockerfile.v1 – Baseline Build

### Approach

The initial implementation used a traditional **single-stage Docker build**.

The application was compiled and executed inside the same container.

### Characteristics

- Single-stage Docker build
- Maven included in the final image
- JDK included in the final image
- Source code copied into the runtime image
- Build dependencies remained in production
- Larger runtime footprint

### Limitations

- Large Docker image
- Slower image download
- Higher storage consumption
- Slower deployments
- Less secure production image

### Image Size

**856 MB**

---

## Dockerfile.v2 – Docker Layer Optimization

The second implementation focused on optimizing Docker layers to improve cache utilization.

### Improvements

- Better Docker layer ordering
- Improved cache utilization
- Reduced rebuild time
- Cleaner Dockerfile structure
- Smaller runtime image

### Benefits

- Faster incremental builds
- Better Docker caching
- Reduced image size
- Improved developer productivity

### Image Size

**454 MB**

---

## Dockerfile.multistage – Multi-Stage Build

The third implementation introduced **Multi-Stage Docker Builds**, separating the build environment from the runtime environment.

Instead of shipping Maven, source code, and build dependencies inside the final image, only the generated application artifact was copied into the runtime image.

### Build Stage

Responsible for:

- Downloading Maven dependencies
- Compiling the application
- Running the Maven build
- Generating the deployable WAR file

### Runtime Stage

Contains only:

- Apache Tomcat
- Generated WAR file

Everything required only during compilation is discarded after the build stage.

### Benefits

- Cleaner production image
- Better maintainability
- Improved security
- Reduced attack surface
- Production-ready deployment strategy
- Separation of build and runtime environments

### Image Size

**612 MB**

---

## Why did the image size increase from V2?

This is expected and does **not** indicate that Docker optimization failed.

### Version 2

The application was packaged as an executable **JAR** and executed directly using the Java Runtime.

Since only the Java Runtime was required, the image remained relatively small.

### Version 3

As the project evolved into a complete DevSecOps pipeline, the deployment strategy changed.

The application was migrated from **JAR** packaging to **WAR** packaging and deployed on **Apache Tomcat**.

Because the Tomcat runtime itself is included inside the Docker image, the image size increased from **454 MB** to **612 MB**.

Although the image became larger, it now supports:

- Enterprise deployment standards
- Centralized application hosting
- Automated WAR deployment
- Better scalability
- Standard DevOps deployment practices

Therefore, the increase in image size is a result of the deployment architecture rather than inefficient Docker optimization.

---

# Phase 2 – DevSecOps Integration

Once the Docker optimization was completed, the project was extended into a DevSecOps pipeline by integrating automated quality checks, security scanning, artifact management, and deployment.

---

## SonarCloud Integration

SonarCloud was integrated to perform continuous code quality analysis during every pipeline execution.

### Features

- Static code analysis
- Code smell detection
- Technical debt analysis
- Maintainability analysis
- Quality Gate validation

### Benefits

- Improved code quality
- Early defect detection
- Better maintainability
- Automated quality validation

---

## Trivy Integration

Trivy was integrated to perform automated vulnerability scanning on Docker images before deployment.

### Features

- Container image scanning
- HIGH vulnerability detection
- CRITICAL vulnerability detection

### Benefits

- Improved container security
- Early vulnerability detection
- Secure deployment process

---

## Nexus Repository

Nexus Repository Manager was integrated to centrally store application artifacts.

### Responsibilities

- WAR artifact storage
- Artifact version management
- Centralized repository management

### Benefits

- Better artifact traceability
- Version control
- Reusable deployments

---

## Docker Hub

Docker Hub was integrated as the container image registry.

### Responsibilities

- Store Docker images
- Maintain image versions
- Distribute images across environments

---

# Phase 3 – CI/CD Automation

The final phase automated the complete software delivery lifecycle.

Instead of executing each task manually, the entire workflow was integrated into CI/CD pipelines.

The project demonstrates implementation using:

- Jenkins Pipeline
- GitHub Actions
- GitLab CI/CD

---

## CI/CD Workflow

Each pipeline automatically performs the following stages:

### Build Stage

- Maven Build
- Dependency Download
- Package Application

---

### Test Stage

- Unit Testing
- Build Verification

---

### Code Quality Stage

- SonarCloud Analysis
- Quality Gate Validation

---

### Docker Stage

- Docker Image Build
- Multi-Stage Build
- Docker BuildKit

---

### Security Stage

- Trivy Vulnerability Scan

---

### Artifact Stage

- Upload WAR to Nexus Repository

---

### Container Registry Stage

- Push Docker Image to Docker Hub

---

### Deployment Stage

- Deploy WAR to Apache Tomcat

---

### Report Generation

The pipeline automatically generates reports including:

- Build Logs
- SonarCloud Reports
- Trivy Reports
- Pipeline Reports

---

# Results

The project successfully transformed a manual Docker build process into a fully automated DevSecOps CI/CD pipeline.

| Metric | Before | After |
|----------|----------|----------|
| Docker Build | Manual | Automated |
| Code Quality | Manual Review | SonarCloud |
| Security Validation | Manual | Trivy |
| Artifact Storage | Manual | Nexus Repository |
| Container Registry | Manual | Docker Hub |
| Deployment | Manual | Automated |
| CI/CD | Not Available | Jenkins / GitHub / GitLab |

---

## Technical Improvements

The implementation introduced the following improvements:

- Docker image optimization
- Docker layer caching
- Multi-stage Docker builds
- Docker BuildKit integration
- Docker Compose deployment
- CI/CD automation
- SonarCloud quality gates
- Trivy security scanning
- Nexus artifact management
- Docker Hub image publishing
- Apache Tomcat deployment
- Automated report generation

# Docker Image Evolution

The Docker image evolved through three implementation stages.

| Version | Dockerfile | Packaging | Deployment Model | Image Size |
|----------|------------|-----------|------------------|-----------:|
| V1 | Dockerfile.v1 | JAR | Standalone Java Runtime | **856 MB** |
| V2 | Dockerfile.v2 | JAR | Optimized Java Runtime | **454 MB** |
| V3 | Dockerfile.multistage | WAR | Apache Tomcat Deployment | **612 MB** |

---

# Docker Optimization Analysis

## Image Size Comparison

| Version | Size |
|----------|-----:|
| image-v1 (Single Stage) | **856 MB** |
| image-multistage | **612 MB** |

---

## Image Size Reduction Calculation

```text
Reduction = (856 − 612) / 856 × 100

          = 244 / 856 × 100

          = 28.5%
```

### Final Result

```text
856 MB  ─────────► 612 MB

Overall Reduction = 28.5%
```

Although Version 2 produced the smallest Docker image (**454 MB**), the project later transitioned from **JAR** packaging to **WAR** packaging for enterprise deployment on **Apache Tomcat**.

The comparison between **Dockerfile.v1** and **Dockerfile.multistage** best demonstrates the impact of Docker optimization because both represent complete production-ready implementations.

---

# What Actually Improved?

## Before Optimization (Dockerfile.v1)

```text
Final Image

├── Maven
├── JDK
├── Source Code
├── Maven Cache
├── Build Dependencies
└── Application
```

### Challenges

- Maven remained inside the production image.
- JDK remained inside the production image.
- Build dependencies increased image size.
- Larger attack surface.
- Higher storage consumption.
- Slower image pull and deployment.

**Image Size: 856 MB**

---

## After Optimization (Dockerfile.multistage)

```text
Build Stage

├── Maven
├── JDK
├── Build Dependencies
└── Build WAR
        │
        ▼

Runtime Stage

├── Apache Tomcat
└── WAR File Only
```

### Improvements

- Maven removed from runtime image.
- Build dependencies discarded after compilation.
- Cleaner production image.
- Better security.
- Easier maintenance.
- Enterprise deployment model.
- Production-ready container.

**Image Size: 612 MB**

---

# Docker Optimization Summary

The Docker optimization strategy focused on separating the build environment from the runtime environment.

Instead of packaging Maven, source code, and build dependencies inside the production image, the application is compiled during the build stage, and only the generated WAR file is copied into the Apache Tomcat runtime image.

This approach reduces unnecessary runtime components, improves maintainability, enhances security, and aligns with enterprise deployment standards.

---

# Architecture Metric

```text
Docker Build Optimization

Dockerfile.v1
856 MB
     │
     ▼
Docker Layer Optimization
     │
     ▼
Dockerfile.v2
454 MB
     │
     ▼
Enterprise Deployment
(JAR → WAR)
     │
     ▼
Dockerfile.multistage
612 MB

Final Reduction (V1 → V3)

856 MB ─────► 612 MB

Reduction = 28.5%
```

---

# Deliverables

The project successfully delivers all requested objectives from the case study.

## Docker Optimization

- Optimized Dockerfile
- Docker Layer Caching
- Multi-Stage Docker Build
- Docker BuildKit Integration
- Docker Compose Configuration

## DevSecOps

- SonarCloud Integration
- Trivy Security Scanning
- Nexus Artifact Repository
- Docker Hub Integration

## CI/CD

- Jenkins Pipeline
- GitHub Actions Workflow
- GitLab CI/CD Pipeline

## Deployment

- Apache Tomcat Deployment
- Automated WAR Deployment

## Documentation

- Build Comparison Report
- Image Size Comparison Report
- Best Practices Documentation

---

# Screenshots

## Docker Image Optimization

<img width="906" height="352" alt="image" src="https://github.com/user-attachments/assets/9907e0a5-331e-468a-8818-6864688c2e4f" />


<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/85daca3d-5150-488b-b312-3e489f3675d9" />


---

## Jenkins Pipeline

<img width="2560" height="2254" alt="image" src="https://github.com/user-attachments/assets/2b18f3ed-7eaf-4fd9-941e-003fda459e9d" />


---

## GitHub Actions Workflow

<img width="2560" height="1887" alt="image" src="https://github.com/user-attachments/assets/3180598d-968d-4cd6-8f73-35cd47a54687" />


---

## GitLab CI/CD Pipeline

<img width="2560" height="1229" alt="image" src="https://github.com/user-attachments/assets/479fd4de-e58b-4c49-a3b5-c2f9512bdc6b" />

---

## SonarCloud Dashboard

<img width="1920" height="3283" alt="image" src="https://github.com/user-attachments/assets/61278d04-9f9a-42d7-afe8-3bf5d20e0845" />


---

## Trivy Security Scan

<img width="1602" height="1222" alt="image" src="https://github.com/user-attachments/assets/430d72a7-d04c-4219-8f11-5463c27c9b5a" />


---

## Nexus Repository

<img width="2560" height="1272" alt="image" src="https://github.com/user-attachments/assets/4067b6d8-0951-4600-9422-eb68644c2d24" />
<img width="2560" height="1272" alt="image" src="https://github.com/user-attachments/assets/978a500d-3076-4efa-8ffe-3e15edadc4c8" />


---

## Docker Hub Repository

<img width="2537" height="597" alt="image" src="https://github.com/user-attachments/assets/41bf2156-52d1-4815-8a44-603bdcf4e217" />

---

## Apache Tomcat Deployment

<img width="2560" height="1669" alt="image" src="https://github.com/user-attachments/assets/f0b62d2b-38f8-43be-974f-a5893d24cf15" />
<img width="2560" height="1291" alt="image" src="https://github.com/user-attachments/assets/e60553e3-1222-4b8d-846b-53840c67d307" />

---

## DevOps pipeline workflow

<img width="936" height="1680" alt="image" src="https://github.com/user-attachments/assets/ec8f584b-ba21-4a27-b4a2-1b42f0d5ba6f" />


<img width="1871" height="840" alt="image" src="https://github.com/user-attachments/assets/193dd342-a8da-4672-8b0f-8c60d9a83c25" />


---

# Technologies Used

## Programming Language

- Java 21

## Build Tool

- Maven

## Containerization

- Docker
- Docker Compose
- Docker BuildKit
- Multi-Stage Docker Build

## CI/CD

- Jenkins
- GitHub Actions
- GitLab CI/CD

## Code Quality

- SonarCloud

## Security

- Trivy

## Artifact Management

- Nexus Repository Manager

## Deployment

- Apache Tomcat

## Container Registry

- Docker Hub

---

# Key Learnings

Throughout this project, I gained practical experience in:

- Docker image optimization
- Docker layer caching
- Multi-stage Docker builds
- Docker BuildKit
- Docker Compose
- CI/CD pipeline automation
- Jenkins Pipeline development
- GitHub Actions workflow automation
- GitLab CI/CD pipeline creation
- SonarCloud integration
- Trivy vulnerability scanning
- Nexus artifact management
- Docker Hub image publishing
- WAR-based enterprise deployment
- Apache Tomcat deployment
- DevSecOps best practices
- Automated software delivery

---

# Business Impact

This project transformed a Docker optimization initiative into a complete DevSecOps CI/CD solution.

### Benefits Achieved

- Reduced Docker image size using multi-stage builds.
- Improved Docker build efficiency.
- Automated software delivery pipelines.
- Automated code quality validation using SonarCloud.
- Automated container vulnerability scanning using Trivy.
- Centralized artifact management through Nexus Repository.
- Automated Docker image publishing to Docker Hub.
- Automated application deployment on Apache Tomcat.
- Standardized Docker build strategy for future projects.
- Reusable DevSecOps CI/CD architecture.
- Improved deployment consistency.
- Reduced manual effort during software delivery.

The solution demonstrates how Docker optimization techniques can be integrated with modern DevSecOps practices to create a scalable, secure, and reusable software delivery pipeline.

---

# Future Enhancements

Possible future improvements include:

- Kubernetes Deployment
- Helm Charts
- AWS EKS Deployment
- Terraform Infrastructure as Code
- Prometheus Monitoring
- Grafana Dashboards
- Argo CD GitOps Deployment
- Kubernetes Horizontal Pod Autoscaling
- Container Image Signing
- Policy-as-Code using Open Policy Agent (OPA)

---

# Final Project Highlights

✅ Docker Image Optimization

✅ Multi-Stage Docker Build

✅ Docker Layer Caching

✅ Docker BuildKit

✅ Docker Compose

✅ Jenkins Pipeline

✅ GitHub Actions Workflow

✅ GitLab CI/CD Pipeline

✅ SonarCloud Code Analysis

✅ Trivy Security Scan

✅ Nexus Artifact Repository

✅ Docker Hub Image Publishing

✅ Apache Tomcat Deployment

✅ Automated DevSecOps CI/CD Pipeline

✅ Enterprise WAR Deployment

✅ 28.5% Docker Image Size Reduction (856 MB → 612 MB)

---

## Conclusion

This project began as a **Docker Build Optimization case study** and successfully evolved into a **complete DevSecOps CI/CD implementation**.

It demonstrates practical experience in Docker optimization, secure software delivery, automated CI/CD pipelines, code quality analysis, vulnerability scanning, artifact management, container registry integration, and enterprise application deployment.

The resulting framework is reusable, scalable, and aligned with modern DevOps and DevSecOps best practices.
