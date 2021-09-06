package api.util

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory

class Credentials {
    companion object {
        private val clientId = System.getenv("CLIENT_ID")
        private val clientSecret = System.getenv("CLIENT_SECRET")
        private val refreshToken = System.getenv("REFRESH_TOKEN")

        val google: GoogleCredential = GoogleCredential
            .Builder()
            .setClientSecrets(clientId, clientSecret)
            .setJsonFactory(GsonFactory.getDefaultInstance())
            .setTransport(GoogleNetHttpTransport.newTrustedTransport())
            .build()
            .setRefreshToken(refreshToken)
    }
}
