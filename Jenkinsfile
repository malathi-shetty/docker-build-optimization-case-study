pipeline {

agent any

environment {
IMAGE_NAME = "docker-build-jenkins"
IMAGE_TAG = "${BUILD_NUMBER}"

// SonarCloud
SONAR_ORG = "malathi-shetty"
SONAR_PROJECT_KEY = "malathi-shetty_docker-build-optimization-case-study"


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

stage('SonarCloud Analysis') {
    steps {
        dir('app/build-optimization-demo') {

            /*
            ===== LOCAL SONARQUBE VERSION =====

            withSonarQubeEnv('SonarQube') {
                sh '''
                mvn clean verify sonar:sonar \
                -Dsonar.projectKey=docker-build-optimization
                '''
            }

            */

            withCredentials([
                string(
                    credentialsId: 'sonar-cloud-token',
                    variable: 'SONAR_TOKEN'
                )
            ]) {

                sh '''
                mvn clean verify sonar:sonar \
                -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                -Dsonar.organization=${SONAR_ORG} \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.token=${SONAR_TOKEN}
                '''
            }
        }
    }
}

stage('Quality Gate') {
    steps {

        withCredentials([
            string(
                credentialsId: 'sonar-cloud-token',
                variable: 'SONAR_TOKEN'
            )
        ]) {

            script {

                sleep 60

                sh '''
                curl -u ${SONAR_TOKEN}: \
                "https://sonarcloud.io/api/qualitygates/project_status?projectKey=${SONAR_PROJECT_KEY}" \
                > qualitygate.json

                cat qualitygate.json

                grep '"status":"OK"' qualitygate.json
                '''
            }
        }

        echo "Quality Gate checked in SonarCloud dashboard"
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

/*
===== LOCAL LAPTOP VERSION =====

stage('Start Nexus And Continue') {
    steps {
        input message: 'Start Nexus now and click Proceed'
    }
}

*/

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
