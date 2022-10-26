pipeline {
   agent any
   tools {
	maven 'MAVEN_3_8_6'
 	jdk 'JDK_11'
   }
	
   node{
	stage('Email Notification') {
	     mail bcc: '', body: '''Hello welcome to jenkins alerts!
	     Thanks
	     Patrick''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'patrickcuentasmariano@gmail.com'
	}	
   }
    stages {
        stage('Compile Stage') {
            steps {
		withMaven(maven : 'MAVEN_3_8_6') {
			bat 'mvn clean compile'
		}
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
