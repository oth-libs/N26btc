package io.n26btc.data.datasource

import io.n26btc.domain.datasource.DataSourceResultHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * [API_MODEL] - The model of the api result
 *
 * [networkCall] - Calls the network and implicitly checks for internet connection and returns the result wrapped in a [DataSourceResultHolder]
 *
 * Function notify UI about:
 * [DataSourceResultHolder.Status.SUCCESS]
 * [DataSourceResultHolder.Status.ERROR] - if error has occurred`
 * [DataSourceResultHolder.Status.NO_INTERNET]
 * [DataSourceResultHolder.Status.IN_PROGRESS]
 */
fun <API_MODEL> resultFlow(
  networkCall: suspend () -> DataSourceResultHolder<API_MODEL>
): Flow<DataSourceResultHolder<API_MODEL>> {
  return flow {

    // 1- notify the process started
    emit(DataSourceResultHolder.inProgress())

    // 2- get remote result, will also hold success status
    val responseStatus = networkCall.invoke()

    emit(responseStatus)
  }
}
