pipeline {
  agent any
  stages {
    stage('Clean') {
      parallel {
        stage('Clean') {
          steps {
            sh 'mvn clean'
          }
        }

        stage('Echo') {
          steps {
            echo 'Cleaning in progress ...'
          }
        }

      }
    }

    stage('Compile') {
      steps {
        bat 'mvn compile'
      }
    }

    stage('Test') {
      steps {
        bat 'mvn test'
      }
    }

    stage('Package') {
      steps {
        bat 'mvn package'
      }
    }

    stage('Call run hook') {
      steps {
        bat 'mvn spring-boot:run'
      }
    }

  }
  environment {
    Name = 'FirstPipeline'
  }
}