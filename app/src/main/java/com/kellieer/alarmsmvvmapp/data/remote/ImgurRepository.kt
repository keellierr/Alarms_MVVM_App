package com.kelliier.alarmsmvvmapp.data.remote

import android.util.Base64
import android.util.Log
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream
import java.util.concurrent.TimeUnit
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ImgurRepository {

    private const val CLIENT_ID = "6039547f4fb3943"

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()


    suspend fun uploadImage(uri: Uri, context: Context): String? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                val imageBytes = inputStream?.readBytes()
                val encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT)

                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", encodedImage)
                    .build()

                val request = Request.Builder()
                    .url("https://api.imgur.com/3/image")
                    .addHeader("Authorization", "Client-ID $CLIENT_ID")
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val body = response.body?.string()
                    Log.d("ImgurUpload", "Respuesta exitosa: $body")

                    val linkRegex = """"link":"(.*?)"""".toRegex()
                    val match = linkRegex.find(body ?: "")
                    match?.groups?.get(1)?.value?.replace("\\/", "/")
                } else {
                    Log.e(
                        "ImgurUpload",
                        "Error en respuesta: ${response.code} - ${response.message}"
                    )
                    null
                }
            } catch (e: Exception) {
                Log.e("ImgurUpload", "Error al subir imagen", e)
                null
            }
        }
    }
}
