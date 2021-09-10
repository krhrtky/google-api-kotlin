import api.datafeed.DatafeedClient

fun main(args: Array<String>) {

    val merchantIdStr = args[0]

    val merchantId = try {
        merchantIdStr.toBigInteger()
    } catch (e: NumberFormatException) {
        null
    }

    if (merchantId == null) {
        println("$merchantIdStr does not format to Int.")
        return
    }

    val datafeedList = DatafeedClient.list(merchantId)

    datafeedList.forEach {
        println(it.id)
        println(it.name)
        println(it.fileName)
    }
}
