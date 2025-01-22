package com.wisnitech.omiesales.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale

@Dao
interface SaleDao {
    @Insert
    suspend fun saveCustomer(customer: Customer)

    @Query("SELECT * FROM customer")
    suspend fun loadCustomers(): List<Customer>

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Insert
    suspend fun saveProduct(product: Product)

    @Insert
    suspend fun saveAllProducts(products: List<Product>)

    @Query("SELECT * FROM product ORDER BY product_name ASC")
    suspend fun loadProducts(): List<Product>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun loadProduct(id: Int): Product

    @Delete
    suspend fun deleteProduct(product: Product)

    @Insert
    suspend fun saveSale(sale: Sale)

    @Query("SELECT * FROM sale ORDER BY sale_date ASC")
    suspend fun loadSales(): List<Sale>

    @Query("SELECT * FROM sale WHERE id = :id")
    suspend fun loadSale(id: Int): Sale

    @Delete
    suspend fun deleteSale(sale: Sale)
}