//Declarative
pipeline {
    agent any
    environment {
        dockerHome = tool 'myDocker'
        mavenHome = tool 'myMaven'
        PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                // sh 'docker version'
                echo "Build"
                echo "PATH - $PATH"
                echo "BUILD Number - $env.BUILD_NUMBER"
                echo "BUILD Id - $env.BUILD_ID"
                echo "BUILD Tag - $env.BUILD_TAG"
                echo "BUILD URL - $env.BUILD_URL"
                echo "BUILD Number - $env.BUILD_NUMBER"
                echo "JOB Name - $env.JOB_NAME"
            }
        }
        stage('Compile') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        
        stage('Package') {
            steps {
                sh "mvn package -DskipTests"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("ramprasana/ecomportal-sporty:${env.BUILD_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('','DockerHub') {
                        dockerImage.push();
                        dockerImage.push('latest');
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }
        failure {
            echo 'Build failure'
        }
    }

}