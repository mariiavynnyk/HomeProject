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
        sh 'curl -L https://github.com/docker/machine/releases/download/v0.16.1/docker-machine-`uname -s`-`uname -m` >/usr/local/bin/docker-machine'
        sh 'chmod +x /usr/local/bin/docker-machine'
        sh 'docker-machine create --driver virtualbox default'
        sh 'eval "$(docker-machine env default)"'
        sh 'docker ps'
        sh 'dockerd'
        sh 'docker version'
        sh 'echo ${PWD}'
        sh 'sh scripts/clear.sh'
      }
    }
    stage('Clone') {
     steps {
       git([url: 'https://github.com/mariiavynnyk/HomeProject.git', branch: 'main'])
     }
    }
    stage('Build') {
     steps {
      sh 'sh scripts/build.sh'
     }
    }
    stage('Run Selenoid') {
     steps {
      sh 'sh scripts/run-selenoid.sh'
      sh 'echo "http://127.0.0.1:8081"'
     }
    }
    stage('Run Tests') {
     steps {
      sh 'sh scripts/run-tests.sh'
     }
    }
    stage('Generate Report') {
     steps {
      sh 'sh scripts/report.sh'
     }
    }
  }
}
