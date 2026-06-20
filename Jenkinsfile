pipeline {

    agent any

    environment {
        IMAGE_NAME = "docker-build-optimization"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                sh '''
                cd app/build-optimization-demo
                mvn clean package
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build \
                -f Dockerfile.multistage \
                -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        stage('Trivy Scan') {
            steps {
                sh '''
                docker run --rm \
                -v /var/run/docker.sock:/var/run/docker.sock \
                aquasec/trivy:latest image \
                --timeout 15m \
                --scanners vuln \
                ${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Generate Report') {
            steps {
                sh '''
                echo "Build Number: ${BUILD_NUMBER}" > reports/jenkins-report.txt
                echo "Job Name: ${JOB_NAME}" >> reports/jenkins-report.txt
                echo "Build Date: $(date)" >> reports/jenkins-report.txt
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/*'
        }
    }
}