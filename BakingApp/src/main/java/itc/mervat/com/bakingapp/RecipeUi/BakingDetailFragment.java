package itc.mervat.com.bakingapp.RecipeUi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import itc.mervat.com.bakingapp.R;
import itc.mervat.com.bakingapp.Recipeadapters.BakingAdapter;
import itc.mervat.com.bakingapp.Recipepojo.Ingredient;
import itc.mervat.com.bakingapp.Recipepojo.Recipe;
import itc.mervat.com.bakingapp.RecipeWidget.UpdateBakingService;
import java.util.ArrayList;
import java.util.List;
import static itc.mervat.com.bakingapp.RecipeUi.BakingActivity.SELECTED_RECIPES;

/**
 * Created by Mervat on 2-Feb-18.
 */
public class BakingDetailFragment extends Fragment  {

    ArrayList<Recipe> recipe;
    String recipeName;

    public BakingDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        TextView textView;

        recipe = new ArrayList<>();


        if(savedInstanceState != null) {
            recipe = savedInstanceState.getParcelableArrayList(SELECTED_RECIPES);

        }
        else {
            recipe =getArguments().getParcelableArrayList(SELECTED_RECIPES);
        }

        List<Ingredient> ingredients = recipe.get(0).getIngredients();
        recipeName=recipe.get(0).getName();

        View rootView = inflater.inflate(R.layout.baking_detail_fragment, container, false);
        textView = rootView.findViewById(R.id.recipe_detail_text);

        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();


        for (Ingredient a : ingredients) {
            textView.append("\u2022 " + a.getIngredient() + "\n");
            textView.append("\t\t\t Quantity: " + a.getQuantity().toString() + "\n");
            textView.append("\t\t\t Measure: " + a.getMeasure() + "\n\n");

            recipeIngredientsForWidgets.add(a.getIngredient() + "\n" +
                    "Quantity: " + a.getQuantity().toString() + "\n" +
                    "Measure: " + a.getMeasure() + "\n");
        }

        recyclerView= rootView.findViewById(R.id.recipe_detail_recycler);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        BakingAdapter mBakingAdapter =new BakingAdapter((BakingDetailActivity)getActivity());
        recyclerView.setAdapter(mBakingAdapter);
        mBakingAdapter.setMasterRecipeData(recipe,getContext());

        //update widget
        UpdateBakingService.startBakingService(getContext(),recipeIngredientsForWidgets);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(SELECTED_RECIPES, recipe);
        currentState.putString("Title",recipeName);
    }


}


