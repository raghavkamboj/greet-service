pipeline {
  agent any
  tools { maven 'Maven3' }
  environment { APP_NAME = 'greet-service' }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Build')    { steps { bat 'mvn -B clean compile' } }

    stage('Test') {
      steps { bat 'mvn -B test' }
      post  { always { junit 'target/surefire-reports/*.xml' } }
    }

    stage('Package') {
      steps { bat 'mvn -B package -DskipTests' }
      post  { success { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true } }
    }
  }

  post {
    success { echo "${APP_NAME}: pipeline finished successfully" }
    failure { echo "${APP_NAME}: pipeline failed" }
  }
}
