Docker Build Optimization Report

Version 1 (Dockerfile.v1)
--------------------------------
Build Time: 68 seconds
Image Size: 835 MB

Observations:
- Builds application inside container
- Maven included in final image
- Application built twice

Version 2 (Dockerfile.v2)
--------------------------------
Build Time: 55 seconds
Image Size: 454 MB

Observations:
- Builds application outside Docker
- Only JAR copied
- No Maven in final image

Version 3 (Dockerfile.multistage)
--------------------------------
Build Time: 12 seconds (cached)
Image Size: 454 MB

Observations:
- Automatic build process
- Maven removed from runtime image
- Production-ready approach