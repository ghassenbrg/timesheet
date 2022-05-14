pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
              git branch: "master",credentialsId: 'GitHub',url: 'https://github.com/ghassenbrg/timesheet.git'
              bat "mvn clean compile -DskipTests -X"
            }
        }
        
        stage("Tests") {
          steps {
            bat "mvn test -X"
         }
            
        }

        stage("Sonar") {
          steps {
            bat "mvn clean verify sonar:sonar \ -Dsonar.projectKey=Timesheet \ -Dsonar.host.url=http://localhost:9000 \ -Dsonar.login=a88f1521486655a0791fc806a1f88335ea43a091"
         }
            
        }
        
        stage("Deploy") {
          steps {
            bat "mvn clean package  deploy:deploy-file  -DgroupId=tn.esprit -DartifactId=timesheet -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-1.0.war"
          }
        }  
  }
  post {
    always {
      emailext to: "ghassen.bargougui@esprit.tn",
      subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
      body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
    }
  }  
}