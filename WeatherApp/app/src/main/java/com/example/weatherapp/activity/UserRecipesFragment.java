package com.example.weatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.RecipeAdapter;
import com.example.weatherapp.java.LoginData;
import com.example.weatherapp.java.Recipe;
import com.example.weatherapp.viewmodel.UserRecipesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;
import static com.example.weatherapp.adapter.RecipeAdapter.REQUEST_SHOW_RECIPE;

/**
 * fragment which contains user recipes
 */
public class UserRecipesFragment extends BasicFragment<UserRecipesViewModel> {
    public static final int REQUEST_ADD_RECIPE = 1;
    private RecipeAdapter adapter;
    private String filter;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_recipes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(UserRecipesViewModel.class);
        adapter = new RecipeAdapter(getContext(), viewModel.getRepository(), true, getActivity());
        recyclerView.setAdapter(adapter);
        filter = "";

        adapter.setRecipes(viewModel.getFilteredRecipes(""));
        EditText editText = view.findViewById(R.id.edit_text_recipe_filter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // we change filter
                filter = charSequence.toString();
                filterRecipes();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });

        FloatingActionButton addRecipeButton = view.findViewById(R.id.button_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), EditRecipeActivity.class), REQUEST_ADD_RECIPE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_ADD_RECIPE && resultCode == RESULT_OK) {
            String title = intent.getStringExtra(EditRecipeActivity.EXTRA_TITLE);
            String steps = intent.getStringExtra(EditRecipeActivity.EXTRA_STEPS);
            viewModel.insert(new Recipe(LoginData.getLoggedUserId(), title, steps));
            filterRecipes();
            Toast.makeText(getContext(), R.string.recipe_saved, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_SHOW_RECIPE && resultCode == RESULT_OK) {
            filterRecipes();
        }
    }

    /**
     * method which gets recipes filtered by a string
     */
    private void filterRecipes() {
        adapter.setRecipes(viewModel.getFilteredRecipes(filter));
    }
}
