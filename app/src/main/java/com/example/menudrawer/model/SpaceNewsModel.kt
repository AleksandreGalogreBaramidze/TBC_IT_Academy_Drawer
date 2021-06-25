package com.example.menudrawer.model

data class SpaceNewsModel(
    val id: Int,
    val url: String,
    val title: String,
    val newsSite: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String,
    val featured: Boolean? = null,
    val providers: List<ProviderModel>? = null,
    val launches:List<HolderModel>? = null
)
