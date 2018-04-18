package itc.mervat.com.bakingapp.RecipeUi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import itc.mervat.com.bakingapp.Resourceling.BakingSimpleIdlingResource;
import itc.mervat.com.bakingapp.R;
import itc.mervat.com.bakingapp.Recipeadapters.BakingDAdapter;
import itc.mervat.com.bakingapp.Recipepojo.Recipe;
import java.util.ArrayList;

/**
 * Created by Mervat on 2-Feb-18.
 */

public class BakingActivity extends AppCompatActivity implements BakingDAdapter.ListItemClickListener{

    static String ALL_RECIPES="All_Recipes";
    static String SELECTED_RECIPES="Selected_Recipes";
    static String SELECTED_STEPS="Selected_Steps";
    static String SELECTED_INDEX="Selected_Index";

    @Nullable
    private BakingSimpleIdlingResource mIdlingResource;

        @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new BakingSimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_baking);

       Toolbar myToolbar = findViewById(R.id.my_toolbar);
       setSupportActionBar(myToolbar);
       getSupportActionBar().setHomeButtonEnabled(false);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       getSupportActionBar().setTitle("Baking Time");

// Get the IdlingResource instance
        getIdlingResource();
    }

    @Override
    public void onListItemClick(Recipe selectedItemIndex) {

        Bundle selectedRecipeBundle = new Bundle();
        ArrayList<Recipe> selectedRecipe = new ArrayList<>();
        selectedRecipe.add(selectedItemIndex);
        selectedRecipeBundle.putParcelableArrayList(SELECTED_RECIPES,selectedRecipe);

        final Intent intent = new Intent(this, BakingDetailActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
    }


}
