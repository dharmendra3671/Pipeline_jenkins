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
                   bat ''
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
            failure {
                mail to: pipelineParams.email, subject: '', body: ""
            }
        }
    }
}
