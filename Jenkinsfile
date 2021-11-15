pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git([url: 'https://github.com/mariiavynnyk/HomeProject.git', branch: 'main'])
      }
    }
    stage('Build') {
      steps {
        sh 'docker-compose up'
        }
      }
    }
    stage('Clean up') {
      steps {
        sh 'docker stop $(docker ps -aq)'
        sh 'docker system prune -af'
        sh 'docker volume prune -f'
      }
    }
  }
}