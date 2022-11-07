pipeline {
    agent any
    stages {
        stage("Build Maven") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/tobymoreno/mtls-proxy']]])
                sh 'mvn clean install'
            }
        }
        stage("Build Docker Image") {
            steps {
                script {
                    sh 'docker build -t 1rom2ny/mtls-proxy:v1 .'
                }
            }
        }
        stage("Push image to Hub") {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd-2', variable: 'dockerhubpwd2')]) {
                        // some block
                        sh 'docker login -u 1rom2ny -p ${dockerhubpwd2}'
                        sh 'docker push 1rom2ny/mtls-proxy:v1'
                    }
                }
            }

        }
    }
}