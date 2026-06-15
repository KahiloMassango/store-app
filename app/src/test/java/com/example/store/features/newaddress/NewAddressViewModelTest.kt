package com.example.store.features.newaddress

import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.model.Location
import com.example.store.core.model.MapCoordinates
import com.example.store.core.testing.fake_repositories.FakeAddressRepository
import com.example.store.core.testing.fake_repositories.FakeLocationRepository
import com.example.store.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewAddressViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: NewAddressViewModel
    private lateinit var fakeLocationRepository: FakeLocationRepository
    private lateinit var fakeAddressRepository: FakeAddressRepository

    @Before
    fun setup() {
        fakeLocationRepository = FakeLocationRepository()
        fakeAddressRepository = FakeAddressRepository()
        viewModel = NewAddressViewModel(fakeLocationRepository, fakeAddressRepository)
    }

    @Test
    fun `searchResults initial state is empty`() = runTest {
        assertEquals(emptyList<Location>(), viewModel.searchResults.value)
    }

    @Test
    fun `updateSearchQuery updates searchQuery state`() = runTest {
        viewModel.updateSearchQuery("Luanda")
        assertEquals("Luanda", viewModel.searchQuery.value)
    }


    @Test
    fun `setAddressCoordinates updates coordinates in uiState`() = runTest {
        val newCoordinates = MapCoordinates(-8.85, 13.25)
        viewModel.setAddressCoordinates(newCoordinates)
        assertEquals(newCoordinates, viewModel.uiState.value.coordinates)
    }

    @Test
    fun `updatePhoneNumber updates phoneNumber in uiState`() = runTest {
        viewModel.updatePhoneNumber("923456789")
        assertEquals("923456789", viewModel.uiState.value.phoneNumber)
    }

    @Test
    fun `updateReceiverName updates receiverName in uiState`() = runTest {
        viewModel.updateReceiverName("Carlos")
        assertEquals("Carlos", viewModel.uiState.value.receiverName)
    }

    @Test
    fun `updateAddressType should update uiState with new address type`() = runTest {
        // Given an initial address type
        val initialState = viewModel.uiState.value
        assertEquals(AddressType.HOME, initialState.addressType)

        // When updating address type
        val newAddressType = AddressType.WORK
        viewModel.updateAddressType(newAddressType)

        // Then the state should reflect the updated type
        assertEquals(newAddressType, viewModel.uiState.value.addressType)
    }

    @Test
    fun `saveAddress adds a new address to repository`() = runTest {
        viewModel.updateReceiverName("Carlos")
        viewModel.updatePhoneNumber("923456789")
        viewModel.saveAddress()
        advanceUntilIdle()

        val lastAdded = fakeAddressRepository.getLastAddedAddress()

        assertNotNull(lastAdded)
        assertEquals("Carlos", lastAdded!!.receiverName)
        assertEquals("923456789", lastAdded.phoneNumber)
    }
}
