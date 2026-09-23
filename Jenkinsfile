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

    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          bat 'mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ' +
              '-Dsonar.projectKey=greet-service -Dsonar.projectName=greet-service ' +
              '-Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.token=%SONAR_AUTH_TOKEN%'
        }
      }
    }

    stage('Quality Gate') {
      steps {
        timeout(time: 5, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true
        }
      }
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
