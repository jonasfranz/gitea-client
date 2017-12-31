package de.jonasfranz.gitea.client.utils

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import java.text.SimpleDateFormat
import java.util.*

@Serializable
actual class JSONDate {
    private val calendar: Calendar

    actual constructor() {
        calendar = Calendar.getInstance()
    }

    constructor(date: Date) {
        calendar = Calendar.getInstance().apply {
            time = date
        }
    }

    val date: java.util.Date get() = calendar.time

    actual fun getTime(): Number = calendar.timeInMillis

    override fun equals(other: Any?): Boolean = other is JSONDate && other.calendar.time == calendar.time
    override fun hashCode(): Int {
        return calendar.hashCode()
    }

    @Serializer(forClass = JSONDate::class)
    actual companion object : KSerializer<JSONDate> {
        override fun save(output: KOutput, obj: JSONDate) {
            output.writeStringValue(dateFormatter.format(obj.date))
        }

        override fun load(input: KInput): JSONDate {
            return JSONDate(dateFormatter.parse(input.readStringValue()))
        }

        private val dateFormatter = SimpleDateFormat(defaultDateFormat)

        override val serialClassDesc: KSerialClassDesc = SerialClassDescImpl("de.jonasfranz.gitea.client.utils.JSONDate")
    }
}