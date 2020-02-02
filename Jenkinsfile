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
      junit '**/target/*.xml'
      mail to: 'chintanpmangukiya@gmail.com', subject: "Success Pipeline: ${currentBuild.fullDisplayName}",
             body: "You are live!"
    }

    unstable {
      echo 'I am unstable :/'
    }

    failure {
      echo 'I failed :('
      mail to: 'chintanpmangukiya@gmail.com', subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }

    changed {
      echo 'Things were different before...For example, if the Pipeline was previously failing but is now successful'
    }

  }
}
