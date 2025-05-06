data class AllergyResponse(
    val result: ResultData
)

data class ResultData(
    val success: Boolean,
    val info: List<AllergyInfo>
)

data class AllergyInfo(
    val allergyId: Int
)
