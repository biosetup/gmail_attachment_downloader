package com.progrexor.google

import java.io.{File, InputStreamReader}

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.{GoogleAuthorizationCodeFlow, GoogleClientSecrets}
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
//import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import com.google.api.services.gmail.Gmail

import scala.collection.JavaConverters._

class GMailClient {
  def get = {
    //Init
    val scopes = List("https://www.googleapis.com/auth/gmail.readonly").asJava
    val jsonFactory = JacksonFactory.getDefaultInstance
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport

    //val dataStoreFactory = new FileDataStoreFactory(new File("/path/gmail_attachment_downloader/datastore"))
    val dataStoreFactory = new MemoryDataStoreFactory

    val authData = getClass.getClassLoader.getResourceAsStream("client_id.json")
    val clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(authData))

    // set up authorization code flow
    val flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, scopes)
      .setDataStoreFactory(dataStoreFactory)
      .build
    val credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("me")
    new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport,
      JacksonFactory.getDefaultInstance,
      credential)
      .setApplicationName("GMail Attachment Downloader")
      .build()
  }
}

object GMailClient {
  def main(args: Array[String]): Unit = {
    val gmail = new GMailClient().get
    val listResponse = gmail.users.labels.list("me").execute
    val labels = listResponse.getLabels
    println(labels.get(0).getName)
  }
}