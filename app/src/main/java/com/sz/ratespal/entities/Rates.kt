package com.sz.ratespal.entities

data class RatesItem(
    val id: Long,
    val EURUSD: Float,
    val EURPLN: Float,
    val EURCHF: Float,
    val EURGBP: Float,
    val USDNOK: Float,
    val GBPUSD: Float,
    val USDRUB: Float,
    val USDCHF: Float,
    val USDPLN: Float,
    val USDJPY: Float,
    val time: String
) {
    operator fun get(pair: String): Float {
        return when (pair) {
            "EURUSD" -> this.EURUSD
            "EURPLN" -> this.EURPLN
            "EURCHF" -> this.EURCHF
            "EURGBP" -> this.EURGBP
            "USDNOK" -> this.USDNOK
            "GBPUSD" -> this.GBPUSD
            "USDRUB" -> this.USDRUB
            "USDCHF" -> this.USDCHF
            "USDPLN" -> this.USDPLN
            else -> this.USDJPY
        }

    }
}

data class RatesResponseData(
    val rates: List<RatesItem>,
    val message: String,
)

data class RatesResponseWrapper(
    val data: RatesResponseData,
    val status: Int
)