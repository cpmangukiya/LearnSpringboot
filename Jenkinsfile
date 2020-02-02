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

    stage('Trigger Run') {
      steps {
        withEnv(['JENKINS_NODE_COOKIE=dontkillMePlz']) {
            echo "Running Trigger on ${env.JAVA_HOME} and ${env.BUILD_ID} and ${env.JENKINS_NODE_COOKIE}"
            bat 'java -jar ./target/LearnSpringboot-0.0.1-SNAPSHOT.jar'
        }
        bat 'java -jar ./target/LearnSpringboot-0.0.1-SNAPSHOT.jar'
      }
    }

  }
  post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
            
        }
        changed {
            echo 'Things were different before...'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
  environment {
    Name = 'Dev'
  }
}
