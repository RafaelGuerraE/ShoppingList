package guerra.shoppinglist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {

    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address

    fun updateLocation(newLocation: LocationData, locationUtils: LocationUtils){
        _location.value = newLocation
    }

    fun fetchAddress(latLng: String){
        try {
            viewModelScope.launch{
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latLng,
                    "AIzaSyCPcRhWIhjrocWDePHF0EPLkv5ahY501cU"
                )

                _address.value = result.results

                Log.d("Res1", "Result: $result")
                Log.d("Res1", "LatLng: $latLng")
            }
        }
        catch(e: Exception){
            Log.d("Res1", "${e.cause} ${e.message}")
        }
    }

}