package com.android.nytimesarticles.models

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)

data class Result(
    @SerializedName("abstract") val abstract: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any,
   // val des_facet: List<String>,
    //val geo_facet: String,
    val id: Long,
    val media: List<Media>,
    //val org_facet: List<String>,
    //val per_facet: String,
    val published_date: String,
    val section: String,
    val source: String,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata") val media_metadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)