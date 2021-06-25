package io.n26btc.data.api

import io.n26btc.data.model.BitcoinChartRetrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface BitcoinChartService {

  @GET("{chartType}")
  suspend fun getBitcoinChart(@Path("chartType") chartType: String, @Query("timespan") timeSpan: String): Response<BitcoinChartRetrofit>
}
