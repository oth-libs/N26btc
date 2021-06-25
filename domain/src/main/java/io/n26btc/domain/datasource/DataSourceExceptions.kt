package io.n26btc.domain.datasource

sealed class DataSourceException : Throwable()

class NoInternetException : DataSourceException()

class ErrorException : DataSourceException()