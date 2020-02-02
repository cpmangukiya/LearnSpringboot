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
        powershell 'wmic process where "name like \'%javaw%\'" Call Terminate'
        withEnv(overrides: ['JENKINS_NODE_COOKIE=dontkillMePlz']) {
          echo "Running Trigger on ${env.JAVA_HOME} and ${env.BUILD_ID} and ${env.JENKINS_NODE_COOKIE}"
          bat 'start javaw -jar ./target/LearnSpringboot-0.0.1-SNAPSHOT.jar'
        }

      }
    }

    stage('Deploy - Staging') {
      steps {
        echo './deploy staging'
        echo './run-smoke-tests'
      }
    }

    stage('Sanity check') {
      steps {
        input 'Does the staging environment look ok?'
      }
    }

    stage('Deploy - Production') {
      steps {
        echo './deploy production'
      }
    }

  }
  environment {
    Name = 'Dev'
  }
  post {
    always {
      echo 'One way or another, I have finished'
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
      echo 'Things were different before...For example, if the Pipeline was previously failing but is now successful'
    }

  }
}