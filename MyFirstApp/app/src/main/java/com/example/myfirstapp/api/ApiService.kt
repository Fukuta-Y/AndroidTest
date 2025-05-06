import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/RecipeAllergyInfo")
    suspend fun getRecipeAllergyInfo(
        @Query("recipeId") recipeId: Int
    ): AllergyResponse
}
