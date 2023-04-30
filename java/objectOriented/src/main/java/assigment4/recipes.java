package assigment4;

import java.util.ArrayList;

public class recipes {

  private csvReader s = new csvReader();

  private ArrayList<String[]> allRecipes = s.readCsvFile("recipes.csv");
  private ArrayList<String> recipe_names = new ArrayList<String>();
  private ArrayList<String> portions = new ArrayList<String>();
  private ArrayList<String> ingredients = new ArrayList<String>();
  private ArrayList<String> ingredient_amount = new ArrayList<String>();
  private ArrayList<String> comments = new ArrayList<String>();
  private ArrayList<String> steps = new ArrayList<String>();

  public void addRecipe_name(String recipe) {
    getRecipe_names().add(recipe);
  }

  public void addPortions(String portion) {
    getPortions().add(portion);
  }

  public void addIngredient(String Ingredient) {
    getIngredients().add(Ingredient);
  }

  public void addIngredient_Amount(String IngredientAmount) {
    getIngredient_amount().add(IngredientAmount);
  }

  public void addComments(String Comment) {
    getComments().add(Comment);
  }

  public void addSteps(String Steps) {
    getSteps().add(Steps);
  }

  public ArrayList<String> getRecipe_names() {
    return this.recipe_names;
  }

  public void setRecipe_names(ArrayList<String> recipe_names) {
    this.recipe_names = recipe_names;
  }

  public ArrayList<String> getPortions() {
    return this.portions;
  }

  public void setPortions(ArrayList<String> portions) {
    this.portions = portions;
  }

  public ArrayList<String> getIngredients() {
    return this.ingredients;
  }

  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }

  public ArrayList<String> getIngredient_amount() {
    return this.ingredient_amount;
  }

  public void setIngredient_amount(ArrayList<String> ingredient_amount) {
    this.ingredient_amount = ingredient_amount;
  }

  public ArrayList<String> getComments() {
    return this.comments;
  }

  public void setComments(ArrayList<String> comments) {
    this.comments = comments;
  }

  public ArrayList<String> getSteps() {
    return this.steps;
  }

  public void setSteps(ArrayList<String> steps) {
    this.steps = steps;
  }

  public void setRecipes() {
    ArrayList<String> recipe_names = new ArrayList<String>();
    ArrayList<String> portions = new ArrayList<String>();
    ArrayList<String> ingredients = new ArrayList<String>();
    ArrayList<String> ingredient_amount = new ArrayList<String>();
    ArrayList<String> comments = new ArrayList<String>();
    ArrayList<String> steps = new ArrayList<String>();

    for (String[] strArr : this.allRecipes) {
      recipe_names.add(strArr[0]);
      portions.add(strArr[1]);
      ingredients.add(strArr[2]);
      ingredient_amount.add(strArr[3]);
      comments.add(strArr[4]);
      steps.add(strArr[5]);
    }
    setRecipe_names(recipe_names);
    setPortions(portions);
    setIngredients(ingredients);
    setIngredient_amount(ingredient_amount);
    setComments(comments);
    setSteps(steps);
  }
}
