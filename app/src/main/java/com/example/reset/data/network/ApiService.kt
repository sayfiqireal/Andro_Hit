package com.example.myapp.data.network

import com.example.reset.data.model.Product
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<Product>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: String, @Body product: Product): Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<Unit>
}
