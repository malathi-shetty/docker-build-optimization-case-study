#!/bin/bash

REPORT=reports/image-size-comparison.md

echo "# Docker Image Size Comparison" > $REPORT
echo "" >> $REPORT

docker images demo:v1 --format "| demo:v1 | {{.Size}} |" >> $REPORT
docker images demo:v2 --format "| demo:v2 | {{.Size}} |" >> $REPORT
docker images demo:v3 --format "| demo:v3 | {{.Size}} |" >> $REPORT

echo "Report generated: $REPORT"
