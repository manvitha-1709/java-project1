
import java.util.ArrayList;

public class Meal {
    private int mealId;
    private String mealName;
    private String mealType;
    private ArrayList<String> ingredients;

    public Meal(int id, String name, String type, String ingredientText) {
        mealId = id;
        mealName = name;
        mealType = type;
        ingredients = new ArrayList<String>();

        String[] list = ingredientText.split(",");

        for (int i = 0; i < list.length; i++) {
            if (!list[i].trim().isEmpty()) {
                ingredients.add(list[i].trim());
            }
        }
    }

    public int getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealType() {
        return mealType;
    }

    public ArrayList<String> getIngredients() {
        return new ArrayList<String>(ingredients);
    }

    public String toString() {
        return "Meal ID: " + mealId + ", Name: " + mealName
                + ", Type: " + mealType + ", Ingredients: " + ingredients;
    }
}
