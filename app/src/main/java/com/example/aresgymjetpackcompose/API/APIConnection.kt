package com.example.aresgymjetpackcompose.API

import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import kotlin.coroutines.*

class APIConnection() : CoroutineScope {

    private var job : Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object{

        private const val urlAPI = "https://192.168.1.133:45455/aresgym/"
        private val logging = HttpLoggingInterceptor()

        private fun getClient() : OkHttpClient{
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            return OkHttpClient.Builder().apply {
                addInterceptor(logging)
                ignoreAllSSLErrors()
            }.build()
        }

        private fun OkHttpClient.Builder.ignoreAllSSLErrors() : OkHttpClient.Builder{

            val naiveTrustManager = object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
            }

            val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
                val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
                init(null, trustAllCerts, SecureRandom())
            }.socketFactory

            sslSocketFactory(insecureSocketFactory, naiveTrustManager)
            hostnameVerifier { _, _ -> true }
            return this
        }

        fun getRetrofit() : Retrofit {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder().baseUrl(urlAPI)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    }

}