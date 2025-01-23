package com.wisnitech.omiesales.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales

@Dao
interface SaleDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun saveCustomer(customer: Customer): Long

    @Query("SELECT * FROM customer WHERE phone = :phoneNumber")
    fun loadCustomerByPhoneNumber(phoneNumber: String): Customer

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveCustomers(customers:List<Customer>)

//    @Query("SELECT * FROM customer")
//    suspend fun loadCustomers(): List<Customer>

//    @Delete
//    suspend fun deleteCustomer(customer: Customer)

//    @Insert
//    suspend fun saveProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllProducts(products: List<Product>)

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun loadProducts(): List<Product>

//    @Query("SELECT * FROM product WHERE id = :id")
//    suspend fun loadProduct(id: Int): Product

//    @Delete
//    suspend fun deleteProduct(product: Product)

    @Insert
    fun saveSale(sale: Sale): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProductOnSale(saleProduct: SaleProduct): Long

    @Transaction
    @Query(
        """
        SELECT 
            v.id AS saleId, 
            v.saleDate AS saleDate, 
            c.name AS customerName, 
            SUM(p.price * vp.quantity) AS saleValue
        FROM sale v
        INNER JOIN customer c ON v.customerId = c.id
        INNER JOIN saleproduct vp ON v.id = vp.saleId
        INNER JOIN product p ON vp.productId = p.id
        GROUP BY v.id
        """
    )
    fun loadSumOfSales(): List<SumSales>

//    @Query("SELECT * FROM sale WHERE id = :id")
//    suspend fun loadSale(id: Int): Sale

//    @Delete
//    suspend fun deleteSale(sale: Sale)
}