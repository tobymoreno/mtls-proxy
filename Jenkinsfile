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
                    sh 'docker build -t devsecopsenv.jfrog.io/default-docker/1rom2ny/mtls-proxy:v7 --platform linux/amd64 .'
                }
            }
        }
        stage("Push image to Hub") {
            steps {
                script {
                //   withCredentials([string(credentialsId: 'dockerhub-pwd-2', variable: 'dockerhubpwd2')]) {
                //        // some block
                //        sh 'docker login -u 1rom2ny -p ${dockerhubpwd2}'
                //        sh 'docker push 1rom2ny/mtls-proxy:v7'
                //    }
                // sb changes
                    withCredentials([string(credentialsId: 'jfrog-pwd', variable: 'jfrog-pwd')]) {
                        // some block
                        // some block
                        sh 'docker login -utoby.moreno@gmail.com devsecopsenv.jfrog.io -p AKCp8nHDosPdDAXipdKk1jpqZ2WtRggGRVXeUDuTibePJfdkE6h4MKHMWhwEJHc9iADWhZYsm'
                        sh 'docker push devsecopsenv.jfrog.io/default-docker/1rom2ny/mtls-proxy:v7'
                    }
                }
            }

        }
        stage("Deploying to Kubernetes") {
            steps {
                script {
                    sh 'kubectl apply -f secrets.yml'
                    sh 'kubectl patch serviceaccount default -p \'{"imagePullSecrets": [{"name": "acr-auth"}]}\' -n default'
                    sh 'kubectl apply -f deploymentservice-cluster.yml'
                    sh 'kubectl apply -f mtls-loadbalancer.yaml'
                    sh 'sleep 5'
                    sh 'kubectl get svc'
                    sh 'kubectl rollout restart -n default deployment mtls-proxy'
                }
            }
        }
    }
}