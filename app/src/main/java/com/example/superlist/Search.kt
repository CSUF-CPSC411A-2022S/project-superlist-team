package com.example.superlist

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class Search {
    companion object SearchAPI {
        private const val BASE_URL = "https://serpapi.com/"
        const val api_key = "19ce4c10251f9314952a03de75a5cbb631435be25e3d11862664534a68d35b72"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        interface SearchAPIService {
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
            @GET("search.json")
            fun searchWalmart(@Query("query") query: String,
                              @Query("engine") engine: String = "walmart",
                              @Query("api_key") apiKey: String = api_key):
                    Call<SearchData>
        }

        object Api {
            val retrofitService: SearchAPIService by lazy {
                retrofit.create(SearchAPIService::class.java)
            }
        }
    }

    //Sample API call to get price of a food given a string.
    fun getItemPrice(itemString: String): Int {
        when(itemString) {
            "Grapes" -> return 25
            "Banana" -> return 30
            else -> return 0
        }
    }

}
