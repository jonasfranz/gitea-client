package de.jonasfranz.gitea.client.utils

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlin.js.Date

@Serializable
actual class JSONDate actual constructor() {

    var date: Date

    actual fun getTime(): Number {
        return date.getTime()
    }

    init {
        date = Date()
    }

    constructor(date: Date) : this() {
        this.date = date
    }

    actual companion object : KSerializer<JSONDate> {

        @Suppress("UnsafeCastFromDynamic")
        override fun load(input: KInput): JSONDate {
            return JSONDate(js("Date.parse(input)"))
        }

        @Suppress("UnsafeCastFromDynamic")
        override fun save(output: KOutput, obj: JSONDate) {
            output.writeStringValue(js("obj.date.toJSON()"))
        }

        override val serialClassDesc: KSerialClassDesc = SerialClassDescImpl("de.jonasfranz.gitea.client.utils.JSONDate")
    }

}