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
        stage('Test') {
            steps {
                echo "Test"
            }
        }
        stage('Integration Test') {
            steps {
                echo "Integration test"
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