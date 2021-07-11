package app.suprsend.android.base

internal expect class LoggerKMM constructor() {

    actual var logLevel: LogLevel

    fun i(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable?)

}

internal enum class LogLevel(val num: Int) {
    VERBOSE(101),
    DEBUG(102),
    INFO(103),
    ERROR(104),
    OFF(Int.MAX_VALUE)
}