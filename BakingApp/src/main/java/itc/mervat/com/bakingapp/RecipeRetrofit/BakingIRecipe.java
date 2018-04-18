package itc.mervat.com.bakingapp.RecipeRetrofit;

import itc.mervat.com.bakingapp.Recipepojo.Recipe;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mervat on 2-Feb-18.
 */
public interface BakingIRecipe {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}