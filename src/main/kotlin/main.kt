import api.datafeed.DatafeedClient
import api.datafeed.status.DatafeedStatusClient

fun main(args: Array<String>) {

    val merchantIdStr = args[0]
    val datafeedIdStr = args[1]

    val merchantId = try {
        merchantIdStr.toBigInteger()
    } catch (e: NumberFormatException) {
        null
    }

    val datafeedId = try {
        datafeedIdStr.toBigInteger()
    } catch (e: NumberFormatException) {
        null
    }

    if (merchantId == null || datafeedId == null) {
        println("$merchantIdStr does not format to Int.")
        return
    }

    val datafeedList = DatafeedClient.list(merchantId)

    datafeedList.forEach {
        println(it.id)
        println(it.name)
        println(it.fileName)
    }

    val datafeedStatus = DatafeedStatusClient.get(
        merchantId = merchantId,
        datafeedId = datafeedId,
    )

    if (datafeedStatus != null) {
        println(datafeedStatus.lastUploadDate)
    }
}
