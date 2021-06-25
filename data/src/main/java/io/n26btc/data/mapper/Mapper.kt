package io.n26btc.data.mapper

internal interface Mapper<FROM, TO> {
  fun map(from: FROM): TO
}