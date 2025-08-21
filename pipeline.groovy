pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/Utkarsh-Wanjari/project-backend-three-teir-application.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t utkarsh1313/new-project-backend-img:latest
                    docker push utkarsh1313/new-project-backend-img:latest
                    docker rmi utkarsh1313/new-project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}
