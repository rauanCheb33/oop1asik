import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Выполнение требования


        Cinema cinema = new Cinema("Sary Arka");
        cinema.addMovie(new Movie(1, "Star Wars", 120, 12, 2000.0), 50);
        cinema.addMovie(new Movie(2, "Inception", 148, 16, 2500.0), 40);
        cinema.addMovie(new Movie(3, "Lion King", 90, 0, 1500.0), 100);

        // Создаем пользователя
        System.out.println("Welcome! Please create a viewer profile.");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();

        Viewer viewer = new Viewer(1, name, age, balance);
        System.out.println("Viewer created: " + viewer);

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Show Schedule (All Movies)");
            System.out.println("2. Buy Ticket");
            System.out.println("3. Search Movie by Title");
            System.out.println("4. Filter Movies by Price");
            System.out.println("5. Show Sorted by Title");
            System.out.println("6. Show Sorted by Price"); // <--- ДОБАВИЛИ ПУНКТ
            System.out.println("7. Show Viewer Info");      // <--- СДВИНУЛИ НОМЕРА
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cinema.showSchedule();
                    break;
                case 2:
                    System.out.print("Enter exact movie title to buy: ");
                    String titleToBuy = scanner.nextLine();
                    Movie movieToBuy = cinema.searchMovieByTitle(titleToBuy);
                    if (movieToBuy != null) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        String result = cinema.bookTickets(viewer, movieToBuy, qty);
                        System.out.println(result);
                    }
                    break;
                case 3: // Поиск
                    System.out.print("Enter title: ");
                    String searchTitle = scanner.nextLine();
                    Movie found = cinema.searchMovieByTitle(searchTitle);
                    if (found != null) System.out.println("Found: " + found);
                    break;
                case 4: // Фильтрация по цене
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();

                    // ВЫЗОВ МЕТОДА
                    List<Movie> cheapMovies = cinema.filterMoviesByMaxPrice(maxPrice);

                    if (cheapMovies.isEmpty()) {
                        System.out.println("No movies found at this price.");
                    } else {
                        System.out.println("Found Films:");
                        for (Movie m : cheapMovies) {
                            System.out.println(m);
                        }
                    }
                    break;
                case 5: // Сортировка по Названию
                    System.out.println("Movies sorted by title:");
                    cinema.getMoviesSortedByTitle().forEach(m ->
                            System.out.println(m.getTitle() + " - " + m.getTicketPrice()));
                    break;

                case 6: // Сортировка по Цене
                    System.out.println("Movies sorted by price (Low to High):");
                    cinema.getMoviesSortedByPrice().forEach(m ->
                            System.out.println(m.getTitle() + " - " + m.getTicketPrice()));
                    break;

                case 7: //
                    System.out.println(viewer);
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}