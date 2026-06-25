pipeline {
    agent any

    environment {
        IMAGE_NAME = "docker-build-jenkins"
        IMAGE_TAG = "${BUILD_NUMBER}"

        // 🔴 CHANGE THIS
        DOCKERHUB_REPO = "shettymalathi113/docker-build-jenkins"

        SONAR_ORG = "malathi-shetty"
        SONAR_PROJECT_KEY = "malathi-shetty_docker-build-optimization-case-study"

        // 🔴 CHANGE THIS if Jenkins is NOT inside Docker network
        NEXUS_URL = "http://nexus:8082"

        // 🔴 CHANGE THIS (your real server IP)
        TOMCAT_SERVER = "ubuntu@35.92.84.93"
        TOMCAT_PATH = "/opt/tomcat/webapps"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        //stage('Build Jar') {
        stage('Build War') {
            steps {
                sh '''
                cd app/build-optimization-demo
                mvn clean package
                ls -lah target
                '''
            }
        }

        // ================= BUILD DOCKER IMAGE FIRST =================
        stage('Build Docker Image') {
            steps {
                sh '''
                docker build \
                -f Dockerfile.multistage \
                -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        // ================= PARALLEL SCANS =================
        stage('Code Quality & Security Scan') {
            steps {
                script {
                    parallel(

                        // ---------- SONAR ----------
                        "SonarCloud": {
                            withCredentials([string(credentialsId: 'sonar-cloud-token', variable: 'SONAR_TOKEN')]) {
                                sh '''
                                cd app/build-optimization-demo
                                mvn clean verify sonar:sonar \
                                  -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                  -Dsonar.organization=${SONAR_ORG} \
                                  -Dsonar.host.url=https://sonarcloud.io \
                                  -Dsonar.token=${SONAR_TOKEN}
                                '''
                            }
                        },

                        // ---------- TRIVY ----------
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

        // ================= QUALITY GATE =================
        stage('Quality Gate') {
            steps {
                withCredentials([string(credentialsId: 'sonar-cloud-token', variable: 'SONAR_TOKEN')]) {

                    script {
                        timeout(time: 5, unit: 'MINUTES') {

                            def response = sh(
                                script: """
                                curl -s -u ${SONAR_TOKEN}: \
                                "https://sonarcloud.io/api/qualitygates/project_status?projectKey=${SONAR_PROJECT_KEY}"
                                """,
                                returnStdout: true
                            ).trim()

                            echo response

                            if (!response.contains('"status":"OK"')) {
                                error "❌ Quality Gate FAILED"
                            }

                            echo "✅ Quality Gate PASSED"
                        }
                    }
                }
            }
        }

        // ================= DOCKER HUB PUSH =================
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

        // ================= NEXUS DEPLOY =================
        stage('Deploy To Nexus') {
            steps {
                sh '''
                cd app/build-optimization-demo

                if [[ "${IMAGE_TAG}" == *SNAPSHOT* ]]; then
                    mvn deploy -DaltDeploymentRepository=nexus-snapshots::default::${NEXUS_URL}/repository/maven-snapshots/
                else
                    mvn deploy -DaltDeploymentRepository=nexus-releases::default::${NEXUS_URL}/repository/maven-releases/
                fi
                '''
            }
        }

        // ================= TOMCAT DEPLOY =================
        stage('Deploy To Tomcat') {
            steps {
                sh '''
                echo "Deploying WAR to Tomcat..."

                cp app/build-optimization-demo/target/*.war \
           /opt/tomcat/webapps/
                ${TOMCAT_SERVER}:${TOMCAT_PATH}/

                echo "Tomcat Deployment Done"
                '''
            }
        }

        // ================= REPORT =================
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

    // ================= POST ACTIONS =================
    post {

        success {
            echo "PIPELINE SUCCESS"

            emailext(
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build SUCCESS 🚀 Check Jenkins for details.",
                to: "shettymalathi113@gmail.com"
            )
        }

        failure {
            echo "PIPELINE FAILED"

            emailext(
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Build FAILED ❌ Check console logs.",
                to: "shettymalathi113@gmail.com"
            )
        }

        always {
            archiveArtifacts artifacts: 'reports/*'
            cleanWs()
        }
    }
}