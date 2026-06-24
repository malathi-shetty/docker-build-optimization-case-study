pipeline {

agent any

environment {
    IMAGE_NAME = "docker-build-jenkins"
    IMAGE_TAG = "${BUILD_NUMBER}"
}

stages {

    stage('Debug Jenkinsfile') {
        steps {
            sh 'cat Jenkinsfile'
            sh '''
            echo "===== CURRENT JENKINSFILE ====="
            grep -n "trivy-report" Jenkinsfile || true
            '''
        }
    }

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

    stage('SonarQube Analysis') {
        steps {
            dir('app/build-optimization-demo') {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                    mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=malathi-shetty_docker-build-optimization-case-study
                    '''
                }
            }
        }
    }

    stage('Quality Gate') {
        steps {
            timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
            }
        }
    }

    stage('Build Docker Image') {
        steps {
            sh '''
            cd app/build-optimization-demo

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

    stage('Start Nexus And Continue') {
        steps {
            input message: 'Start Nexus now and click Proceed'
        }
    }

    stage('Deploy To Nexus') {
        steps {
            sh '''
            cd app/build-optimization-demo
            mvn deploy
            '''
        }
    }

    stage('Generate Report') {
        steps {
            sh '''
            mkdir -p reports

            echo "Build Number: ${BUILD_NUMBER}" > reports/jenkins-report.txt
            echo "Job Name: ${JOB_NAME}" >> reports/jenkins-report.txt
            echo "Build Date: $(date)" >> reports/jenkins-report.txt

            cat reports/jenkins-report.txt
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
