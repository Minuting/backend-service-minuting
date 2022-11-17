package net.huray.backend.http.res

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize(using = BaseError.Serializer::class)
class BaseError() {
    /**
     * @param code will prepend with `"error."` automatically.
     * @param defaultMessage default message
     * @param args message formatting arguments
     */
    constructor(code: String, defaultMessage: String?, args: Array<out Any?>? = null) : this() {
        this.code = code
        this.message = defaultMessage ?: ""
        this.e = "error.$code"
    }

    var code = ""
    var message = ""

    /**
     * error code will prepend with `"error."` automatically.
     */
    @JsonIgnore
    var e = ""

    class Serializer : JsonSerializer<BaseError>() {
        override fun serialize(value: BaseError, gen: JsonGenerator, provider: SerializerProvider) {
            gen.writeStartObject()
            gen.writeStringField("code", value.e)
            gen.writeFieldName("message")
            gen.writeObject(value.e)
            gen.writeEndObject()
        }
    }
}