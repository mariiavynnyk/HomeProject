pipeline {
  agent {
    label 'demo-docker-jenkins'
  }
  stages {
    stage('Initialize') {
      steps {
        script {
         def dockerHome = tool 'myDocker'
         env.PATH = "${dockerHome}/bin:${env.PATH}"
        }
      }
    }
    stage('Clone') {
     steps {
       git([url: 'https://github.com/mariiavynnyk/HomeProject.git', branch: 'main'])
     }
    }
    stage('Run') {
     steps {
      sh './scripts/build.sh'
      sh './scripts/run.sh'
      sh './scripts/generate-report.sh'
     }
    }
    stage('Clean up') {
      steps {
        sh './scripts/clear.sh'
      }
    }
  }
}