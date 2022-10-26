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
	stage('Email Notification'){
		emailext body: 'Test Message',
    		subject: 'Test Subject',
    		to: 'test@example.com'    
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
