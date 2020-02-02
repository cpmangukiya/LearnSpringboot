pipeline {
  agent any
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
            echo "Running on ${env.JAVA_HOME} and ${env.BUILD_ID}"
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
  post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
  environment {
    Name = 'Dev'
  }
}
