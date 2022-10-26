pipeline {
   agent any
   tools {
	maven 'MAVEN_3_8_6'
 	jdk 'JDK_11'
   }
    stages {
        stage('Compile Stage') {
            steps {
		withMaven(maven : 'MAVEN_3_8_6') {
			bat 'mvn clean compile'
		}
            }
        }
	post {
        always {
            emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
        }
    	}    
        stage('Testing Stage') {
            steps {
		withMaven(maven : 'MAVEN_3_8_6') {
			bat 'mvn test'
		}	
            }
        }
        stage('Deploy') {
            steps {
	        withMaven(maven : 'MAVEN_3_8_6') {
			bat 'mvn package'
		}
            }
        }
	
    }
}	
