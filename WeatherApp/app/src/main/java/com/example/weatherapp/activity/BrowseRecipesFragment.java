package com.example.weatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.RecipeAdapter;
import com.example.weatherapp.viewmodel.BrowseRecipesViewModel;

import static android.app.Activity.RESULT_OK;
import static com.example.weatherapp.activity.RecipeActivity.REQUEST_EDIT_RECIPE;

/**
 * fragment for browsing all recipes
 */
public class BrowseRecipesFragment extends BasicFragment<BrowseRecipesViewModel> {
    private RecipeAdapter adapter;
    private String filter;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_recipes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.browse_recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(BrowseRecipesViewModel.class);
        adapter = new RecipeAdapter(getContext(), viewModel.getRepository(), false, getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setRecipes(viewModel.getFilteredRecipes(""));
        EditText editText = view.findViewById(R.id.edit_text_browse_recipe_filter);
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

        return view;
    }

    /**
     * method which filters recipes
     */
    private void filterRecipes() {
        adapter.setRecipes(viewModel.getFilteredRecipes(filter));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_EDIT_RECIPE && resultCode == RESULT_OK) {
            filterRecipes();
        }
    }
}

