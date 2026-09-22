
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] days = {
            "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"
        };

        String[] meals = {
            "Veg Pulao", "Pasta", "Dosa",
            "Fried Rice", "Sandwich"
        };

        String[] groceries = {
            "Rice", "Vegetables", "Pasta",
            "Dosa Batter", "Bread", "Eggs",
            "Cheese", "Onion"
        };

        int[] plan = new int[7];
        boolean[] groceryList = new boolean[8];

        // Choose meals for the whole week
        for (int i = 0; i < 7; i++) {

            System.out.println("\nChoose a meal for " + days[i]);

            for (int j = 0; j < meals.length; j++) {
                System.out.println((j + 1) + ". " + meals[j]);
            }

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            while (choice < 1 || choice > 5) {
                System.out.print("Please enter a number from 1 to 5: ");
                choice = sc.nextInt();
            }

            plan[i] = choice - 1;

            // Add groceries needed for the selected meal
            switch (choice) {

                case 1:
                    groceryList[0] = true;
                    groceryList[1] = true;
                    groceryList[7] = true;
                    break;

                case 2:
                    groceryList[2] = true;
                    groceryList[1] = true;
                    groceryList[6] = true;
                    break;

                case 3:
                    groceryList[3] = true;
                    groceryList[7] = true;
                    break;

                case 4:
                    groceryList[0] = true;
                    groceryList[1] = true;
                    groceryList[5] = true;
                    break;

                case 5:
                    groceryList[4] = true;
                    groceryList[5] = true;
                    groceryList[6] = true;
                    break;
            }
        }

        // Display the weekly meal plan
        System.out.println("\n===== WEEKLY MEAL PLAN =====");

        for (int i = 0; i < 7; i++) {
            System.out.println(days[i] + " : " + meals[plan[i]]);
        }

        // Display the grocery list
        System.out.println("\n===== GROCERY LIST =====");

        for (int i = 0; i < groceries.length; i++) {

            if (groceryList[i]) {
                System.out.println("- " + groceries[i]);
            }
        }

        System.out.println("\nYour weekly meal plan is ready!");

        sc.close();
    }
}