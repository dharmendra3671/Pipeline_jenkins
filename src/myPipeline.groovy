def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {
                    git branch: pipelineParams.branch, url: pipelineParams.scmUrl
                }
            }

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
                    bat ''
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
