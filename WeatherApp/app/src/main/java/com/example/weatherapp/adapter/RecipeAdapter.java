package com.example.weatherapp.adapter;

/**
 * adapter which contains recipe title and handles on click
 */
public class RecipeAdapter {
//public class RecipeAdapter extends RecyclerView.Adapter<String> {

//    public static final String EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID";
//    public static final int REQUEST_SHOW_RECIPE = 101;
//
//    private List<Recipe> recipes = new ArrayList<>();
//    private Context context;
//    private RecipeRepository repository;
//    private boolean userRecipes;
//    private Activity activity;
//
//    public RecipeAdapter(Context context, RecipeRepository repository, boolean userRecipes, Activity activity) {
//        super();
//        this.context = context;
//        this.repository = repository;
//        this.userRecipes = userRecipes;
//        this.activity = activity;
//    }
//
//    @NonNull
//    @Override
//    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
//        return new RecipeHolder(itemView, context, activity);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
//        Recipe current = recipes.get(position);
//        holder.textViewRecipeTitle.setText(String.format("%s%s", current.getTitle(), userRecipes ? "" : " od " + repository.getRecipeAuthor(current.getUserId())));
//        holder.recipeId = current.getId();
//    }
//
//    @Override
//    public int getItemCount() {
//        return recipes.size();
//    }
//
//    public void setRecipes(List<Recipe> recipes) {
//        this.recipes = recipes;
//        notifyDataSetChanged();
//    }
//
//    static class RecipeHolder extends RecyclerView.ViewHolder {
//        private TextView textViewRecipeTitle;
//        private int recipeId;
//
//        RecipeHolder(@NonNull View itemView, final Context context, final Activity activity) {
//            super(itemView);
//            textViewRecipeTitle = itemView.findViewById(R.id.text_view_recipe_title);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, RecipeActivity.class);
//                    intent.putExtra(EXTRA_RECIPE_ID, recipeId);
//                    activity.startActivityForResult(intent, REQUEST_SHOW_RECIPE);
//                }
//            });
//        }
//    }

}
