package itc.mervat.com.bakingapp.RecipeUi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import itc.mervat.com.bakingapp.Resourceling.BakingSimpleIdlingResource;

import itc.mervat.com.bakingapp.R;
import itc.mervat.com.bakingapp.Recipeadapters.BakingDAdapter;
import itc.mervat.com.bakingapp.Recipepojo.Recipe;
import itc.mervat.com.bakingapp.RecipeRetrofit.BakingIRecipe;
import itc.mervat.com.bakingapp.RecipeRetrofit.BakingRetrofitBuilder;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static itc.mervat.com.bakingapp.RecipeUi.BakingActivity.ALL_RECIPES;

/**
 * Created by Mervat on 2-Feb-18.
 */


public class BakingFragment extends Fragment  {



    public BakingFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;

        View rootView = inflater.inflate(R.layout.baking_fragment, container, false);

        recyclerView= rootView.findViewById(R.id.recipe_recycler);
        BakingDAdapter recipesAdapter =new BakingDAdapter((BakingActivity)getActivity());
        recyclerView.setAdapter(recipesAdapter);



        if (rootView.getTag()!=null && rootView.getTag().equals("phone-land")){
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(),4);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        else {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
        }

        BakingIRecipe bakingIRecipe = BakingRetrofitBuilder.Retrieve();
        Call<ArrayList<Recipe>> recipe = bakingIRecipe.getRecipe();

        BakingSimpleIdlingResource idlingResource = (BakingSimpleIdlingResource)((BakingActivity)getActivity()).getIdlingResource();

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }


        recipe.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                ArrayList<Recipe> recipes = response.body();

                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList(ALL_RECIPES, recipes);

                recipesAdapter.setRecipeData(recipes,getContext());
                if (idlingResource != null) {
                    idlingResource.setIdleState(true);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

        return rootView;
    }


}
