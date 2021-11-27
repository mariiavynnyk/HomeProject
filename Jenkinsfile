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
        sh './scripts/clear.sh'
        sh 'mkdir allure-report'
        sh 'chmod 777 -R allure-report'
        sh 'mkdir target'
        sh 'chmod 777 -R target'
        sh 'ls -la'
        sh 'pwd'
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
      sh 'echo "http://127.0.0.1:8081"'
      sh './scripts/run-tests.sh'
      sh './scripts/generate-report.sh'
      sh 'ls -la'
      sh 'pwd'
     }
    }
    stage('Clean up') {
      steps {
        sh './scripts/clear.sh'
      }
    }
  }
}