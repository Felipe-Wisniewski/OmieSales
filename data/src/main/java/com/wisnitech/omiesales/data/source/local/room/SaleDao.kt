package com.wisnitech.omiesales.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales
import kotlinx.coroutines.flow.Flow

@Dao
internal interface SaleDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun saveCustomer(customer: Customer): Long

    @Query("SELECT * FROM customer WHERE phone = :phoneNumber")
    fun loadCustomerByPhoneNumber(phoneNumber: String): Customer?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun saveAllProducts(products: List<Product>)

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun loadProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM orderitem ORDER BY productName")
    fun loadOrder(): Flow<List<OrderItem>>

    @Delete
    fun deleteOrderItem(orderItem: OrderItem)

    @Query("DELETE FROM orderitem")
    fun deleteOrderItems()

    @Insert
    fun saveSale(sale: Sale): Long?

    @Query("DELETE FROM sale WHERE id = :saleId")
    fun deleteSale(saleId: Long)

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
}