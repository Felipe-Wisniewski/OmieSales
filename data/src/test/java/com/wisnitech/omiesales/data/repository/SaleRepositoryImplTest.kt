package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SaleProductClient
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SaleRepositoryImplTest {

    private lateinit var saleLocal: SaleLocalDataSource
    private lateinit var repository: SaleRepositoryImpl

    @Before
    fun setUp() {
        saleLocal = mock(SaleLocalDataSource::class.java)
        repository = SaleRepositoryImpl(saleLocal)
    }

    @Test
    fun `addSale should save sale to local data source`() = runTest {
        val sale = Sale(id = 1, 1, "27/01/2025")

        repository.addSale(sale)

        verify(saleLocal).saveSale(sale)
    }

    @Test
    fun `removeSale should delete sale by id in local data source`() = runTest {
        val saleId = 1L

        repository.removeSale(saleId)

        verify(saleLocal).deleteSale(saleId)
    }

    @Test
    fun `addProductsOnSale should save list of products to local data source`() = runTest {
        val saleProducts = listOf(
            SaleProduct(saleId = 1, productId = 101, quantity = 2),
            SaleProduct(saleId = 1, productId = 102, quantity = 1)
        )

        repository.addProductsOnSale(saleProducts)

        verify(saleLocal).saveProductsOnSale(saleProducts)
    }

    @Test
    fun `getSales should return list of summed sales`() = runTest {
        val sumSales = listOf(
            SumSales(saleId = 1, "27/01/2025", "Felipe", 100.0),
            SumSales(saleId = 2, "27/01/2025", "Silva", 200.0)
        )
        `when`(saleLocal.loadSumOfSales()).thenReturn(sumSales)

        val result = repository.getSales()

        verify(saleLocal).loadSumOfSales()
        assert(result == sumSales)
    }

    @Test
    fun `getProductListBySaleId should return list of products for the given sale id`() = runTest {
        val saleId = 1L
        val products = listOf(
            SaleProductClient("P1", 1, 1.0, Product.PriceUnit.UNIT, 1.0),
            SaleProductClient("P2", 2, 2.0, Product.PriceUnit.UNIT, 4.0)
        )
        `when`(saleLocal.loadProductListBySaleId(saleId)).thenReturn(products)

        val result = repository.getProductListBySaleId(saleId)

        verify(saleLocal).loadProductListBySaleId(saleId)
        assert(result == products)
    }
}