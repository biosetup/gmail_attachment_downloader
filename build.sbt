name := "gmail_attachment_downloader"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.google.apis" % "google-api-services-gmail" % "v1-rev82-1.23.0"

libraryDependencies += "com.google.apis" % "google-api-services-oauth2" % "v2-rev134-1.23.0"

libraryDependencies += "com.google.oauth-client" % "google-oauth-client-java6" % "1.23.0"

libraryDependencies += "com.google.oauth-client" % "google-oauth-client-jetty" % "1.23.0"

libraryDependencies += "com.google.api-client" % "google-api-client" % "1.23.0"

libraryDependencies += "com.google.http-client" % "google-http-client-jackson2" % "1.23.0"
