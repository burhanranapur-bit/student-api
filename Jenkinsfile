pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk   'JDK-21'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/burhanranapur-bit/student-api.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    pkill -f "student-api" || true
                    sleep 2
                    nohup java -jar target/student-api-1.0.0.jar \
                        --server.port=9090 \
                        > /tmp/student-api.log 2>&1 &
                    echo "App deployed on port 9090"
                '''
            }
        }
    }

    post {
        success {
            echo "SUCCESS - Build #${BUILD_NUMBER} is live on port 9090"
        }
        failure {
            echo "FAILED - Check logs above"
        }
    }
}
