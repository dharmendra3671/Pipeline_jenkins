def call() {

    pipeline {
        agent any
        stages {
            stage('build') {
                steps {
                   bat 'Pythonzip.py'
                }
            }

            stage ('test') {
                steps {
                    echo 'testing'
                }
            }

            stage('deploy'){
                steps {
                    bat 'sharelibjf-zip.py'
                }
            }
        }
        post {
            always {
                emailext to: pipelineParams.email, subject: 'Jenkins Pipeline Build Status', body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n ${currentBuild.currentResult}: Job ${env.JOB_NAME} \n ${currentBuild.currentResult}: ${env.BUILD_TAG}"
            }
        }
    }
}
