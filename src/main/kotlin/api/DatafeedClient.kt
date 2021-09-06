package api

import api.util.Credentials
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.content.ShoppingContent
import com.google.api.services.content.model.Datafeed
import java.io.IOException
import java.math.BigInteger

class DatafeedClient {
    companion object {
        private val client = ShoppingContent.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            Credentials.google,
        ).build()

        fun get(merchantId: BigInteger, datafeedId: BigInteger): Datafeed? = try {
            client.datafeeds().get(merchantId, datafeedId).execute()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

        fun list(merchantId: BigInteger) = try {
            client.datafeeds().list(merchantId).execute()?.resources ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}
