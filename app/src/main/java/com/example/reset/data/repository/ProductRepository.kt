package com.example.myapp.data.repository

import com.example.myapp.data.network.ApiService
import com.example.reset.data.model.Product

class ProductRepository(private val api: ApiService) {
    suspend fun getProducts() = api.getProducts()
    suspend fun addProduct(product: Product) = api.addProduct(product)
    suspend fun updateProduct(id: String, product: Product) = api.updateProduct(id, product)
    suspend fun deleteProduct(id: String) = api.deleteProduct(id)
}
