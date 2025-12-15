import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args){
        Cinema cinema = new Cinema("Sary arka");

        Movie movie1 = new Movie(1, "Star wars", 90, 12, 2000);
        Movie movie2 = new Movie(2, "Inception", 100, 18, 2500);

        Viewer aigerim = new Viewer(10, "Aigerim", 20, 3000);
        Viewer ali = new Viewer(11, "Ali", 14, 1000);

        cinema.addMovie(movie1, 5);
        cinema.addMovie(movie2, 2);

        System.out.println(movie1);
        System.out.println(movie2);
        System.out.println(aigerim);
        System.out.println(ali);

        cinema.showSchedule();


        System.out.println("\n--- Booking attempts ---");
        System.out.println(cinema.bookTickets(aigerim, movie1, 1));
        System.out.println(cinema.bookTickets(ali, movie2, 1));
        System.out.println(cinema.bookTickets(ali, movie1, 1));
        System.out.println(cinema.bookTickets(aigerim, movie2, 3));

        System.out.println("\n--- After booking ---");
        System.out.println(aigerim);
        System.out.println(ali);
        cinema.showSchedule();

        System.out.println("\n--- Compare objects ---");
        Movie movie1Copy = new Movie(1, "Star wars (copy)", 90, 12, 2000);

        System.out.println("movie1 == movie1Copy ? "+ (movie1 == movie1Copy));
        System.out.println("movie1.equals(movie1Copy) ?" + movie1.equals(movie1Copy));




    }

}