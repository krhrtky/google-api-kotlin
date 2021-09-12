package api.datafeed

/**
 *
 * @see [com.google.api.services.content.model.Datafeed.contentType]
 */
enum class ContentType {
    Product,
    ;

    fun toAPIValue() = this.toString()

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
