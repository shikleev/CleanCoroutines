package project.views.cleancoroutines.data.network

import android.annotation.SuppressLint
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object NetworkService {

    private const val BASE_URL = " http://www.mocky.io/v2/"

    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
    }

    private val baseInterceptor: Interceptor = invoke { chain ->

        val builder: HttpUrl.Builder = chain
                .request()
                .url
                .newBuilder()

        val newUrl: HttpUrl = builder.build()

        val request = chain
                .request()
                .newBuilder()
                .url(newUrl)
                .build()

        return@invoke chain.proceed(request)
    }

    private val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(baseInterceptor)
            .build()

    fun retrofitService(): Api {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api::class.java)
    }

}