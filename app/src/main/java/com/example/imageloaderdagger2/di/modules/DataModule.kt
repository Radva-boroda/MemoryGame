//package dev.ronnie.imageloaderdagger2.di.modules
//
//import dagger.Module
//import dagger.Provides
//import dev.ronnie.imageloaderdagger2.dataa.api.ApiService
//import dev.ronnie.imageloaderdagger2.dataa.repository.Repository
//import javax.inject.Singleton
//
//@Module
//object DataModule {
//
//    @Singleton
//    @Provides
//    fun providesRepository(apiService: ApiService): Repository =
//        Repository(apiService)
//}