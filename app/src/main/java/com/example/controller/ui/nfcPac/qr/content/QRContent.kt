package com.example.controller.ui.nfcPac.qr.content

@Suppress("ArrayInDataClass")
public sealed class QRContent(
    public open val rawBytes: ByteArray?,
    public open val rawValue: String?,
) {



    public data class Plain internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?
    ) : QRContent(rawBytes, rawValue)



    public data class Wifi internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val encryptionType: Int,
        val password: String,
        val ssid: String
    ) : QRContent(rawBytes, rawValue)



    public data class Url internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val title: String,
        val url: String
    ) : QRContent(rawBytes, rawValue)



    public data class Sms internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val message: String,
        val phoneNumber: String
    ) : QRContent(rawBytes, rawValue)


    public data class GeoPoint internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val lat: Double,
        val lng: Double
    ) : QRContent(rawBytes, rawValue)



    public data class Email internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val address: String,
        val body: String,
        val subject: String,
        val type: EmailType
    ) : QRContent(rawBytes, rawValue) {
        public enum class EmailType {
            UNKNOWN, WORK, HOME
        }
    }



    public data class Phone internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val number: String,
        val type: PhoneType
    ) : QRContent(rawBytes, rawValue) {
        public enum class PhoneType {
            UNKNOWN, WORK, HOME, FAX, MOBILE
        }
    }



    public data class ContactInfo internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val addresses: List<Address>,
        val emails: List<Email>,
        val name: PersonName,
        val organization: String,
        val phones: List<Phone>,
        val title: String,
        val urls: List<String>
    ) : QRContent(rawBytes, rawValue) {

        public data class Address internal constructor(val addressLines: List<String>, val type: AddressType) {
            public enum class AddressType {
                UNKNOWN, WORK, HOME
            }
        }

        public data class PersonName internal constructor(
            val first: String,
            val formattedName: String,
            val last: String,
            val middle: String,
            val prefix: String,
            val pronunciation: String,
            val suffix: String
        )
    }


    public data class CalendarEvent internal constructor(
        override val rawBytes: ByteArray?,
        override val rawValue: String?,
        val description: String,
        val end: CalendarDateTime,
        val location: String,
        val organizer: String,
        val start: CalendarDateTime,
        val status: String,
        val summary: String
    ) : QRContent(rawBytes, rawValue) {

        public data class CalendarDateTime internal constructor(
            val day: Int,
            val hours: Int,
            val minutes: Int,
            val month: Int,
            val seconds: Int,
            val year: Int,
            val utc: Boolean
        )
    }
}
