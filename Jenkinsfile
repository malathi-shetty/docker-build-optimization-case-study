pipeline {
    agent any

    environment {
        IMAGE_NAME = "docker-build-jenkins"
        IMAGE_TAG = "${BUILD_NUMBER}"

        DOCKERHUB_REPO = "shettymalathi113/docker-build-jenkins"

        SONAR_ORG = "malathi-shetty"
        SONAR_PROJECT_KEY = "malathi-shetty_docker-build-optimization-case-study"

        NEXUS_URL = "http://172.17.0.1:8082"

        TOMCAT_SERVER = "ubuntu@172.31.44.114"
        TOMCAT_PATH = "/var/lib/tomcat10/webapps"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build War') {
            steps {
                sh '''
                cd app/build-optimization-demo
                mvn clean package -DskipTests
                ls -lah target
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

        stage('Code Quality & Security Scan') {
            steps {
                script {
                    parallel(

                        "SonarCloud": {
                            withCredentials([string(credentialsId: 'sonar-cloud-token', variable: 'SONAR_TOKEN')]) {
                                sh '''
                                cd app/build-optimization-demo

                                mvn clean verify sonar:sonar \
                                  -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                  -Dsonar.organization=${SONAR_ORG} \
                                  -Dsonar.host.url=https://sonarcloud.io \
                                  -Dsonar.token=$SONAR_TOKEN \
                                  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                                '''
                            }
                        },

                        "Trivy Scan": {
                            sh '''
                            docker run --rm \
                              -v /var/run/docker.sock:/var/run/docker.sock \
                              aquasec/trivy:latest image \
                              --timeout 10m \
                              --scanners vuln \
                              ${IMAGE_NAME}:${IMAGE_TAG} || true
                            '''
                        }
                    )
                }
            }
        }

        stage('Quality Gate') {
            steps {
                withCredentials([string(credentialsId: 'sonar-cloud-token', variable: 'SONAR_TOKEN')]) {

                    script {

                        def response = sh(
                            script: """
                            curl -s -u ${SONAR_TOKEN}: \
                            "https://sonarcloud.io/api/qualitygates/project_status?projectKey=${SONAR_PROJECT_KEY}"
                            """,
                            returnStdout: true
                        ).trim()

                        echo "SONAR RESPONSE: ${response}"

                        def json = readJSON text: response

                        if (json.projectStatus.status != "OK") {
                            error("❌ Quality Gate FAILED")
                        }

                        echo "✅ Quality Gate PASSED"
                    }
                }
            }
        }

        stage('DockerHub Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin

                    docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_REPO}:${IMAGE_TAG}

                    docker push ${DOCKERHUB_REPO}:${IMAGE_TAG}
                    '''
                }
            }
        }

        stage('Deploy To Nexus') {
            steps {
                withCredentials([file(credentialsId: 'maven-settings', variable: 'MAVEN_SETTINGS')]) {

                    sh '''
                    cd app/build-optimization-demo

                    mvn deploy \
                    --settings $MAVEN_SETTINGS \
                    -DaltDeploymentRepository=nexus-releases::default::http://172.17.0.1:8082/repository/maven-releases/
                    '''
        }
    }
}
        stage('Deploy To Tomcat') {
            steps {
                sh '''
                echo "Setting up SSH trust..."

                mkdir -p ~/.ssh

                ssh-keyscan -H 172.31.44.114 >> ~/.ssh/known_hosts

                echo "Deploying WAR..."

                scp -o StrictHostKeyChecking=no \
                app/build-optimization-demo/target/build-optimization-demo-1.0.war \
                ubuntu@172.31.44.114:/tmp/app.war

                ssh -o StrictHostKeyChecking=no ubuntu@172.31.44.114 "
                    sudo mv /tmp/app.war /var/lib/tomcat10/webapps/ROOT.war
                    sudo systemctl restart tomcat10
                "

                echo "Tomcat Deployment Done"
                '''
            }
        }

        stage('Generate Report') {
            steps {
                sh '''
                mkdir -p reports

                echo "Build Number: ${BUILD_NUMBER}" > reports/report.txt
                echo "Job Name: ${JOB_NAME}" >> reports/report.txt
                echo "Date: $(date)" >> reports/report.txt
                '''
            }
        }
    }

    post {
/*
        success {
            echo "PIPELINE SUCCESS"

            emailext(
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build SUCCESS 🚀",
                to: "shettymalathi113@gmail.com"
            )
        }

        failure {
            echo "PIPELINE FAILED"

            emailext(
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build FAILED ❌",
                to: "shettymalathi113@gmail.com"
            )
        }
*/
        always {
            archiveArtifacts artifacts: 'reports/*'
            cleanWs()
        }
    }
}