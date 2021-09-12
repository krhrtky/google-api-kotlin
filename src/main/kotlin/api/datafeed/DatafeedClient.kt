package api.datafeed

import api.util.Credentials
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.content.ShoppingContent
import com.google.api.services.content.model.Datafeed
import com.google.api.services.content.model.DatafeedFetchSchedule
import com.google.api.services.content.model.DatafeedTarget
import com.google.auth.http.HttpCredentialsAdapter
import java.io.IOException
import java.math.BigInteger
import java.net.URL
import java.time.LocalDateTime

class DatafeedClient {
    companion object {
        private val client = ShoppingContent.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(Credentials.google)
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

        fun create(
            merchantId: BigInteger,
            datafeedId: Long,
            name: String,
            fileName: String,
            fetchURL: URL,
            fetchDateTime: LocalDateTime,
        ): Long? {

            val fetchSchedule = DatafeedFetchSchedule()
                .setDayOfMonth(fetchDateTime.dayOfMonth.toLong())
                .setHour(fetchDateTime.hour.toLong())
                .setTimeZone("Asia/Tokyo")
                .setFetchUrl(fetchURL.toString())

            val targets = listOf(
                DatafeedTarget()
                    .setCountry("JP")
                    .setLanguage("ja")
                    .setIncludedDestinations(listOf(Destination.Shopping.toAPIValue()))
            )

            val datafeed = Datafeed()
                .setId(datafeedId)
                .setContentType(ContentType.Product.toAPIValue())
                .setName(name)
                .setFileName(fileName)
                .setFetchSchedule(fetchSchedule)
                .setTargets(targets)

            return try {
                client.datafeeds().insert(merchantId, datafeed).execute().id
            } catch (e: Exception) {
                null
            }
        }

        fun update(
            merchantId: BigInteger,
            datafeedId: Long,
            name: String,
            fileName: String,
            fetchURL: URL,
            fetchDateTime: LocalDateTime,
        ) {

            val fetchSchedule = DatafeedFetchSchedule()
                .setDayOfMonth(fetchDateTime.dayOfMonth.toLong())
                .setHour(fetchDateTime.hour.toLong())
                .setTimeZone("Asia/Tokyo")
                .setFetchUrl(fetchURL.toString())

            val targets = listOf(
                DatafeedTarget()
                    .setCountry("JP")
                    .setLanguage("ja")
                    .setIncludedDestinations(listOf(Destination.Shopping.toAPIValue()))
            )

            val datafeed = Datafeed()
                .setId(datafeedId)
                .setContentType(ContentType.Product.toAPIValue())
                .setName(name)
                .setFileName(fileName)
                .setFetchSchedule(fetchSchedule)
                .setTargets(targets)

            client.datafeeds().update(merchantId, datafeedId.toBigInteger(), datafeed).execute()
        }
    }
}
