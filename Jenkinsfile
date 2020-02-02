pipeline {
  agent { docker { image 'maven:3.3.3' } }
  stages {
    stage('Clean') {
      parallel {
        stage('Clean') {
          steps {
            bat 'mvn clean'
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
        script {
          withEnv(['JENKINS_NODE_COOKIE=dontkillMePlz']) {
            bat 'java -jar ./target/LearnSpringboot-0.0.1-SNAPSHOT.jar'
          }
        }

      }
    }

  }
  environment {
    Name = 'FirstPipeline'
  }
}
