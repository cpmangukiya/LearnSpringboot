pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        call 'mvn clean'
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
