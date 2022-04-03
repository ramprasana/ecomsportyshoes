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
                echo "BUILD Number - $evn.BUILD_NUMBER"
                echo "BUILD Id - $evn.BUILD_ID"
                echo "BUILD Tag - $evn.BUILD_TAG"
                echo "BUILD URL - $evn.BUILD_URL"
                echo "BUILD Number - $evn.BUILD_NUMBER"
                echo "JOB Name - $evn.JOB_NAME"
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