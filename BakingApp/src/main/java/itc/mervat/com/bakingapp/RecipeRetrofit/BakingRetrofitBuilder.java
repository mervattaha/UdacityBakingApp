package itc.mervat.com.bakingapp.RecipeRetrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mervat on 2-Feb-18.
 */

public final class BakingRetrofitBuilder {
    static BakingIRecipe bakingIRecipe;

    public static BakingIRecipe Retrieve() {

        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        bakingIRecipe = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(BakingIRecipe.class);


        return bakingIRecipe;
    }
}

