package api.util

import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.UserCredentials

class Credentials {
    companion object {
        private val clientId = System.getenv("CLIENT_ID")
        private val clientSecret = System.getenv("CLIENT_SECRET")
        private val refreshToken = System.getenv("REFRESH_TOKEN")

        val google: GoogleCredentials = UserCredentials
            .newBuilder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken)
            .build()
    }
}
