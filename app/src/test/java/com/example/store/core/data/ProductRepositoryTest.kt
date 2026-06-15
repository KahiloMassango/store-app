package com.example.store.core.data

import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation1
import com.example.store.core.testing.fake_datasources.FakeProductNetworkDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class ProductRepositoryTest {

    private lateinit var fakeNetworkDataSource: FakeProductNetworkDataSource
    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        fakeNetworkDataSource = FakeProductNetworkDataSource()
        repository = ProductRepositoryImpl(fakeNetworkDataSource)
    }


    @Test
    fun `given valid category and gender when getProducts then return product list`() =  runTest  {
        val products = repository.getProducts("male", "clothing")
        assertTrue(products.isSuccess)
    }

    @Test
    fun `given no matching products when getProducts then return empty list`() =  runTest {
        val products = repository.getProducts("invalid gender", "invalid category")
        assertTrue(products.isSuccess)
        assertTrue(products.getOrNull()!!.isEmpty())
    }

    @Test
    fun `given network error when getProducts then return failure`() = runTest {
        fakeNetworkDataSource.setShouldFail(true)
        val products = repository.getProducts(null, null)
        assertTrue(products.isFailure)
    }

    @Test
    fun `given existing product ID when getProductById then return product with variations`() = runTest {
        val expected = fakeProductWithVariation1
        val product = repository.getProductById(expected.id)
        assertTrue(product.isSuccess)
        assertEquals(expected, product.getOrNull())

    }

    @Test
    fun `given non-existing product ID when getProductById then return failure`() = runTest {
        val product = repository.getProductById("non-existing-id")
        assertTrue(product.isFailure)

    }

    @Test
    fun `given valid query when searchProducts then return matching products`() = runTest {
        val expected = listOf(fakeProduct1)
        val products = repository.searchProducts(fakeProduct1.name)
        assertTrue(products.isSuccess)
        assertEquals(expected, products.getOrNull())
    }

    @Test
    fun `given no matching products when searchProducts then return empty list`() = runTest {
        val products = repository.searchProducts("non-existing-product")
        assertTrue(products.isSuccess)
        assertEquals(emptyList<Product>(), products.getOrNull())
    }

}