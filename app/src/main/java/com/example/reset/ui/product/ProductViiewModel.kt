package com.example.myapp.ui.product

import androidx.lifecycle.*
import com.example.myapp.data.repository.ProductRepository
import com.example.reset.data.model.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val repo: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun fetchProducts() {
        viewModelScope.launch {
            val response = repo.getProducts()
            if (response.isSuccessful) {
                _products.postValue(response.body())
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repo.addProduct(product)
            fetchProducts()
        }
    }

    fun updateProduct(id: String, product: Product) {
        viewModelScope.launch {
            repo.updateProduct(id, product)
            fetchProducts()
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            repo.deleteProduct(id)
            fetchProducts()
        }
    }
}
