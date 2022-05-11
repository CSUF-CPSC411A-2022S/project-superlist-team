package com.example.superlist

import com.squareup.moshi.Json

data class SearchData (
    val search_metadata: search_metadata,
    val search_parameters: search_parameters,
    val search_information: search_information,
    val organic_results: Array<organic_result>,
    val pagination: pagination,
    val serpapi_pagination: serpapi_pagination,

    )

data class search_metadata (
    val id: String,
    val status: String,
    val json_endpoint: String,
    val created_at: String,
    val processed_at: String,
    val walmart_url: String,
    val raw_html_file: String,
    val total_time_taken: Double,
)

data class search_parameters (
    val engine: String,
    val device: String,
    val query: String
)


data class search_information (
    val location: Location,
    val total_results: Double,
    val query_displayed: String,
    val organic_results_state: String

)


data class Location (
    val postal_code: String,
    val province_code: String,
    val city: String,
    val store_id: String
)

data class organic_result (
    val us_item_id: String,
    val product_id: String,
    val title: String,
    val thumbnail: String,
    val rating: Double,
    val reviews: Double,
    val seller_id: String,
    val seller_name: String,
    @field:Json(name = "fulfillment_badge") val fulfillment_badge: String?,
    @field:Json(name = "fulfillment_badges") val fulfillment_badges: Array<String>?,
    val two_day_shipping: Boolean,
    val out_of_stock: Boolean,
    val sponsored: Boolean,
    val muliple_options_available: Boolean,
    val primary_offer: primary_offer,
    val price_per_unit: price_per_unit,
    val product_page_url: String,
    val serpapi_product_page_url: String
)

data class primary_offer (
    val offer_id: String,
    val offer_price: Double,
    val min_price: Double,
)

data class price_per_unit (
    val unit: String,
    val amount: String,
)

data class pagination (
    val current: Double,
    val next: String,
    val other_pages: other_pages
)


data class serpapi_pagination (
    val current: Double,
    val next_link: String,
    val next: String,
    val other_pages: other_pages
)

data class other_pages (
    @field:Json(name = "2") val page2: String?,
    @field:Json(name = "3") val page3: String?,
    @field:Json(name = "4") val page4: String?,
    @field:Json(name = "5") val page5: String?,
    @field:Json(name = "6") val page6: String?,
    @field:Json(name = "7") val page7: String?,
    @field:Json(name = "8") val page8: String?,
    @field:Json(name = "9") val page9: String?,
    @field:Json(name = "10") val page10: String?,
    @field:Json(name = "11") val page11: String?,
    @field:Json(name = "12") val page12: String?,
    @field:Json(name = "13") val page13: String?,
    @field:Json(name = "14") val page14: String?,
    @field:Json(name = "15") val page15: String?,
    @field:Json(name = "16") val page16: String?,
    @field:Json(name = "17") val page17: String?,
    @field:Json(name = "18") val page18: String?,
    @field:Json(name = "19") val page19: String?,
    @field:Json(name = "20") val page20: String?,
    @field:Json(name = "21") val page21: String?,
    @field:Json(name = "22") val page22: String?,
    @field:Json(name = "23") val page23: String?,
    @field:Json(name = "24") val page24: String?,
    @field:Json(name = "25") val page25: String?
)