package com.example.myapp.ui.product

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.network.ApiClient
import com.example.myapp.data.repository.ProductRepository
import com.example.reset.data.model.Product
import com.example.reset.databinding.ActivityMainBinding
import com.example.reset.databinding.ActivityProductBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepository(ApiClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup RecyclerView
        adapter = ProductAdapter()
        binding.rvItemList.layoutManager = LinearLayoutManager(this)
        binding.rvItemList.adapter = adapter
        // observe data
        viewModel.products.observe(this) { list ->
            adapter.submitList(list)
        }

        // fetch data awal
        viewModel.fetchProducts()

        // contoh tambah produk
        binding.btnAdd.setOnClickListener {
            val newProduct = Product(
                id = "p${System.currentTimeMillis()}",
                name = "Produk Baru",
                description = "Deskripsi Produk",
                price = 50000
            )
            viewModel.addProduct(newProduct)
        }
    }
}

