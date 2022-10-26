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
		steps{
			mail bcc: '', body: '''Hello welcome to jenkins alerts!
			Thanks
			Patrick''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'patrickcuentasmariano@gmail.com'
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
	post {
        always {
            echo "Notifying build result by email"
        }
        success {
            mail to: 'patrickcuentasmariano@gmail.com',
                 subject: "SUCCESS: ${currentBuild.fullDisplayName}",
                 body: "Test Complete Build passed."
        }
        failure {
           mail to: 'patrickcuentasmariano@gmail.com',
                subject:"FAILURE: ${currentBuild.fullDisplayName}",
                body: "Test Complete Build failed."
        }
	}
    }
}	
