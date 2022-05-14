pipeline {
    agent any   
    stages {
        stage('Compile') {
            steps {
                updateGitlabCommitStatus name: 'Compile', state: 'running'
                bat 'mvn clean compile -DskipTests -X'
            }
            post {
                success {
                  updateGitlabCommitStatus name: 'Compile', state: 'success'
                }
                failure {
                  updateGitlabCommitStatus name: 'Compile', state: 'failed'
                }
            }
        }
        stage('Test') {
            steps {
                updateGitlabCommitStatus name: 'Test', state: 'running'
                bat 'mvn test -X'
            }
            post {
                success {
                  updateGitlabCommitStatus name: 'Test', state: 'success'
                }
                failure {
                  updateGitlabCommitStatus name: 'Test', state: 'failed'
                }
            }
        }
        stage('SonarQube') {
            steps {
                updateGitlabCommitStatus name: 'Sonar', state: 'running'
                bat 'mvn verify sonar:sonar -DskipTests -Dsonar.projectKey=timesheet -Dsonar.host.url=http://localhost:9000 -Dsonar.login=8258e331195bc724964dab54d6da478fa4390773 -Dsonar.skip=false -Dsonar.qualitygate.wait=true -X'
            }
            post {
                success {
                  updateGitlabCommitStatus name: 'Sonar', state: 'success'
                }
                failure {
                  updateGitlabCommitStatus name: 'Sonar', state: 'failed'
                }
            }
        }
        stage('Deploy') {
            steps {
                updateGitlabCommitStatus name: 'Deploy', state: 'running'
                bat 'mvn clean package -DskipTests -X && mvn deploy:deploy-file -DartifactId=Timesheet -DgroupId=tn.esprit.spring -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases -Dfile=target/Timesheet-1.0.war -X'
            }
            post {
                success {
                  updateGitlabCommitStatus name: 'Deploy', state: 'success'
                }
                failure {
                  updateGitlabCommitStatus name: 'Deploy', state: 'failed'
                }
            }
        }
    }   
}
