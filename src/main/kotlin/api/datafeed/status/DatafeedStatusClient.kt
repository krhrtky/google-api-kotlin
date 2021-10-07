package api.datafeed.status

import api.util.Credentials
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.content.ShoppingContent
import com.google.api.services.content.model.DatafeedStatus
import com.google.auth.http.HttpCredentialsAdapter
import java.io.IOException
import java.math.BigInteger

class DatafeedStatusClient {
    companion object {
        private val client = ShoppingContent.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(Credentials.google)
        ).build()

        fun get(merchantId: BigInteger, datafeedId: BigInteger): DatafeedStatus? = try {
            client.datafeedstatuses().get(merchantId, datafeedId).execute()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
