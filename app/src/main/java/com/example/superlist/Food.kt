package com.example.superlist

import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.HttpURLConnection
import java.net.URL
import java.net.URI
import java.net.URLEncoder
import javax.security.auth.callback.Callback


class Food {
    companion object FoodAPI {
        private const val BASE_URL = "https://api.spoonacular.com/"
        const val api_key = "6bb83d5451b64f7197002d14d44a5003"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        interface FoodAPIService {
            /**
             * Retrieves a valid address that closely matches the search string.
             *
             * The @GET annotation describes the API endpoint. You can include
             * variables using {} that enclose a variable name.
             *
             * The @PATH annotation assigns the parameter value to the variable
             * on the URL string marked with { }.
             *
             * The @Query annotation adds a query to the end of the URL with the
             * specified name and value from the associated parameter.
             *
             * @param search search string
             * @param token access token
             * @return Place object built from the API data
             */
            @GET("food/products/search")
            fun getProductByName(@Query("query") search: String,
                                 @Query("number") number: String = "1",
                                 @Query("apiKey") apiKey: String = api_key):
                    Call<ProductSearch>

            @GET("recipes/{search_string}/nutritionWidget.json")
            fun getNutrition(@Path(value = "search_string") search: String,
                                  @Query("apiKey") apiKey: String = api_key):
                    Call<Nutrition>
        }

        object Api {
            val retrofitService: FoodAPIService by lazy {
                retrofit.create(FoodAPIService::class.java)
            }
        }
    }

    //Sample API call to get price of a food given a string.
    fun getFoodPrice(foodString: String): Int {
        when(foodString) {
            "apple" -> return 25
            "orange" -> return 30
            else -> return 0
        }
    }

}