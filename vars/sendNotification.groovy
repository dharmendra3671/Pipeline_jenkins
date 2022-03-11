def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus = buildStatus ?: 'SUCCESS'

  // Default values
  def subject = "'Jenkins Pipeline ${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]"
  def details = "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n ${currentBuild.currentResult}: Job ${env.JOB_NAME} \n ${currentBuild.currentResult}: ${env.BUILD_TAG}"

 emailext (
      to: 'kumar.dk3671@gmail.com',
      subject: subject,
      body: details
    )
}
