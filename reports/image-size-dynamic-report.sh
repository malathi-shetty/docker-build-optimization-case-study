#!/bin/bash

echo "Docker Image Size Comparison"
echo "============================"

docker images demo:v1 --format "demo:v1 {{.Size}}"
docker images demo:v2 --format "demo:v2 {{.Size}}"
docker images demo:v3 --format "demo:v3 {{.Size}}"
