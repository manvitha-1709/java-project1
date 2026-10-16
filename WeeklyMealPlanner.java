
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.FileWriter;
import java.io.IOException;

public class WeeklyMealPlanner {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Meal> meals = new ArrayList<Meal>();

    static String[] days = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    };

    static String[] types = {
        "Breakfast", "Lunch", "Dinner", "Snacks"
    };

    static Meal[][] plan = new Meal[7][4];

    public static void main(String[] args) {
        int choice = -1;

        while (choice != 0) {
            showMenu();
            choice = readNumber("Enter your choice: ");

            switch (choice) {
                case 1:
                    addMeal();
                    break;
                case 2:
                    showMeals();
                    break;
                case 3:
                    searchMeal();
                    break;
                case 4:
                    planMeal();
                    break;
                case 5:
                    showPlan();
                    break;
                case 6:
                    groceryList();
                    break;
                case 7:
                    savePlan();
                    break;
                case 0:
                    System.out.println("Thank you for using the program!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    static void showMenu() {
        System.out.println("\n===== WEEKLY MEAL PLANNER =====");
        System.out.println("1. Add meal");
        System.out.println("2. Show all meals");
        System.out.println("3. Search meal");
        System.out.println("4. Plan a meal");
        System.out.println("5. Show weekly plan");
        System.out.println("6. Generate grocery list");
        System.out.println("7. Save weekly plan");
        System.out.println("0. Exit");
    }

    static int readNumber(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    static void addMeal() {
        int id = readNumber("Enter meal ID: ");

        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getMealId() == id) {
                System.out.println("This ID is already used.");
                return;
            }
        }

        System.out.print("Enter meal name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Meal name cannot be empty.");
            return;
        }

        System.out.println("1. Breakfast  2. Lunch  3. Dinner  4. Snacks");
        int typeChoice = readNumber("Choose meal type: ");

        if (typeChoice < 1 || typeChoice > 4) {
            System.out.println("Invalid meal type.");
            return;
        }

        System.out.print("Enter ingredients separated by commas: ");
        String ingredientText = sc.nextLine().trim();

        if (ingredientText.isEmpty()) {
            System.out.println("Enter at least one ingredient.");
            return;
        }

        Meal m = new Meal(id, name, types[typeChoice - 1], ingredientText);
        meals.add(m);

        System.out.println("Meal added successfully.");
    }

    static void showMeals() {
        if (meals.size() == 0) {
            System.out.println("No meals added yet.");
            return;
        }

        System.out.println("\nAvailable meals:");

        for (int i = 0; i < meals.size(); i++) {
            System.out.println(meals.get(i));
        }
    }

    static void searchMeal() {
        System.out.print("Enter meal name to search: ");
        String name = sc.nextLine().trim().toLowerCase();
        boolean found = false;

        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getMealName().toLowerCase().contains(name)) {
                System.out.println(meals.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Meal not found.");
        }
    }

    static void planMeal() {
        if (meals.size() == 0) {
            System.out.println("Please add a meal first.");
            return;
        }

        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }

        int day = readNumber("Choose day (1-7): ");

        if (day < 1 || day > 7) {
            System.out.println("Invalid day.");
            return;
        }

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int type = readNumber("Choose meal category (1-4): ");

        if (type < 1 || type > 4) {
            System.out.println("Invalid category.");
            return;
        }

        showMeals();
        int id = readNumber("Enter the meal ID to plan: ");
        Meal selected = null;

        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getMealId() == id) {
                selected = meals.get(i);
                break;
            }
        }

        if (selected == null) {
            System.out.println("Meal ID not found.");
        } else {
            plan[day - 1][type - 1] = selected;
            System.out.println("Meal added to the weekly plan.");
        }
    }

    static void showPlan() {
        System.out.println("\n===== WEEKLY PLAN =====");

        for (int i = 0; i < days.length; i++) {
            System.out.println("\n" + days[i] + ":");

            for (int j = 0; j < types.length; j++) {
                if (plan[i][j] == null) {
                    System.out.println(types[j] + ": Not planned");
                } else {
                    System.out.println(types[j] + ": "
                            + plan[i][j].getMealName());
                }
            }
        }
    }

    static void groceryList() {
        HashMap<String, Integer> items = new HashMap<String, Integer>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (plan[i][j] != null) {
                    ArrayList<String> ingredients =
                            plan[i][j].getIngredients();

                    for (int k = 0; k < ingredients.size(); k++) {
                        String item = ingredients.get(k).trim().toLowerCase();

                        if (items.containsKey(item)) {
                            items.put(item, items.get(item) + 1);
                        } else {
                            items.put(item, 1);
                        }
                    }
                }
            }
        }

        if (items.size() == 0) {
            System.out.println("Plan some meals first.");
            return;
        }

        TreeSet<String> sortedItems = new TreeSet<String>(items.keySet());

        System.out.println("\n===== GROCERY LIST =====");

        for (String item : sortedItems) {
            System.out.println(item + " - used in "
                    + items.get(item) + " meal(s)");
        }
    }

    static void savePlan() {
        try {
            FileWriter file = new FileWriter("weekly_plan.txt");
            file.write("WEEKLY MEAL PLAN\n");

            for (int i = 0; i < days.length; i++) {
                file.write("\n" + days[i] + ":\n");

                for (int j = 0; j < types.length; j++) {
                    String name;

                    if (plan[i][j] == null) {
                        name = "Not planned";
                    } else {
                        name = plan[i][j].getMealName();
                    }

                    file.write(types[j] + ": " + name + "\n");
                }
            }

            file.close();
            System.out.println("Plan saved in weekly_plan.txt");

        } catch (IOException e) {
            System.out.println("Error while saving the plan.");
        }
        sc.close();
    }
}
