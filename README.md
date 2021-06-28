# N26 Coding challenge

## Intro

This project is written 100% in Kotlin and following MVVM Clean Architecture principles, with Flow/LiveData/Coroutines/DataBinding.

It also includes Unit Tests and Instrumentation Tests inside each module grouped inside a test suite.


## Build and run

Simply clone the project, import into Android Studio and run.

## Modules

It contains 4 modules in total: 


* **app**: Contains the views fragment/activity and the adapters, view helper classes, extensions and data binding adapters.
* **presentation**: Contains the ViewModel declaration 
* **domain**: Contains the Repository interface and UseCases 
* **data**: Contains the Repository implementation and the retrofit api calls.

## Testing

Each module contains a set of unit tests grouped in a test suite. 

app module also includes instrumentation tests.



## Libraries

* Coroutines
* Koin
* Jetpack (Navigation, Data Binding, Lifecycle, LiveData, ViewModel)
* Kotlinx Serialization
* Jakewharton retrofit2-kotlinx-serialization-converter
* Retrofit
* JUnit
* Mockk
* Espresso

## Code

### Data Source helpers 

[**SourceResultHolder**](https://github.com/oth-libs/N26btc/blob/master/domain/src/main/java/io/n26btc/domain/datasource/DataSourceResultHolder.kt)

A generic class that holds a value with its loading status.


[**DataSourceFlow**](https://github.com/oth-libs/N26btc/blob/master/data/src/main/java/io/n26btc/data/datasource/DataSourceFlow.kt)

**```resultFlow()```** will call the api using ```networkCall``` function. 


[**RemoteDataSource**](https://github.com/oth-libs/N26btc/blob/master/data/src/main/java/io/n26btc/data/datasource/RemoteDataSource.kt)

**```getRemoteResult```** will execute the ```call``` function, generally coming from a retrofit service, and map it using the ```transform``` function. This method automatically checks on network state and handles errors, and returns the right ```SourceResultHolder``` wrapper.


All of these files can be put together with Retrofit services and have a seamless Repository implementation for use cases.


## Demo

<https://drive.google.com/file/d/1VSa3kckSP11poDykUegq0ht7onUXer_s> 



