package andres.rangel.androidtest.data.remote

import andres.rangel.androidtest.BuildConfig
import andres.rangel.androidtest.data.remote.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY_PIXABAY
    ): Response<ImageResponse>
}