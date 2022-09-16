package com.example.marsphotos.service.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {

    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

object MarsApi {

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}


/*private const val BASE_URL2 = "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit2 = Retrofit.Builder() //Compilador de retrofit, para compilar y crear un objeto retrofit
    .addConverterFactory(ScalarsConverterFactory.create()) //Fabrica de conversor , recupera el JSON y muestra un string
    .baseUrl(BASE_URL2) // Agregamos el URI base para el servicio web
    .build() //Crear el objeto retrofit

//Interfaz -> define como retrofit se comunica con el servidor web mediante solicitues HTTP
interface MarsApiService2 {

    @GET("photos")
    suspend fun getPhotos(): String //la funcion es suspend para poder llamarlo desde una corrutina
}

// Inicializar del objeto retrofit, objeto singleton publico al que se puede acceder desde el resto de la app
object MarsApi2 {

    /*propiedad de obj retrofit de tipo MarsApiService, con inicializacion diferida para que se
    inicialice en su primer uso.
    Creacion de instancia diferida: Es cuando la creacion de objetos se retrasa con lentitud, hasta
    que realmente necesita ese objeto para evitar calculos innecesarios o usar otros recursos de procesamiento*/
    val retrofitService2: MarsApiService2 by lazy {
        retrofit2.create(MarsApiService2::class.java)
    }
}
*/