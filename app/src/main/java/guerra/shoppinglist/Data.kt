package guerra.shoppinglist

data class LocationData(
    val latitude: Double,
    val longitude: Double
)

data class GeocodingResponse(
    val error_message: String,
    val results: List<GeocodingResult>,
    val status: String
)

data class GeocodingResult(
    val formatted_address: String
)
