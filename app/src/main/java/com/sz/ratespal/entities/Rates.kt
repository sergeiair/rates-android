package com.sz.ratespal.entities

data class RatesItem(
    val id: Long,
    val EURUSD: Double,
    val EURPLN: Double,
    val EURCHF: Double,
    val EURGBP: Double,
    val USDNOK: Double,
    val GBPUSD: Double,
    val USDRUB: Double,
    val USDCHF: Double,
    val USDPLN: Double,
    val USDJPY: Double,
    val time: String
)

data class RatesResponseData(
    val rates: List<RatesItem>,
    val message: String,
)

data class RatesResponseWrapper(
    val data: RatesResponseData,
    val status: Int
)