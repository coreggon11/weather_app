package com.example.weatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.R;
import com.example.weatherapp.java.Recipe;
import com.example.weatherapp.viewmodel.RecipeViewModel;

import static com.example.weatherapp.activity.RecipeActivity.EXTRA_REQUEST_CODE;
import static com.example.weatherapp.activity.RecipeActivity.REQUEST_EDIT_RECIPE;
import static com.example.weatherapp.adapter.RecipeAdapter.EXTRA_RECIPE_ID;

/**
 * activity for editing or adding a recipe
 */
public class EditRecipeActivity extends BasicActivity<RecipeViewModel> {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_STEPS = "EXTRA_STEPS";

    private EditText editTextTitle;
    private EditText editTextRecipeSteps;

    private int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editTextTitle = findViewById(R.id.edit_text_recipe_title);
        editTextRecipeSteps = findViewById(R.id.edit_text_recipe_steps);

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        if (getIntent().getIntExtra(EXTRA_REQUEST_CODE, 0) == REQUEST_EDIT_RECIPE) {
            recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, 0);
            Recipe recipe = viewModel.getRecipe(recipeId);
            editTextTitle.setText(recipe.getTitle());
            editTextRecipeSteps.setText(recipe.getText());
        }
    }

    /**
     * method which saves changes in existing recipe or adds new recipe
     */
    private void saveRecipe() {
        String recipeTitle = editTextTitle.getText().toString();
        if (recipeTitle.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.recipe_no_title_alert, Toast.LENGTH_LONG).show();
            return;
        }
        String recipeSteps = editTextRecipeSteps.getText().toString();
        if (recipeSteps.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.recipe_no_body_alert, Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, recipeTitle);
        intent.putExtra(EXTRA_STEPS, recipeSteps);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_recipe) {
            saveRecipe();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
