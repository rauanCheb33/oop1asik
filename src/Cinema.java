import java.sql.SQLOutput;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cinema {

    private String name;
    private final Map<Movie, Integer> availableSeatsByMovie = new LinkedHashMap<>();
    public Cinema(String name){
        if(name == null || name.isBlank()) throw new IllegalArgumentException();
        this.name = name;
    }

    public String getName(String name){
        return name;
    }

    public void setName(String name){
        if(name == null || name.isBlank()) throw new IllegalArgumentException("movie is required");
        this.name = name;
    }

    public void addMovie(Movie movie, int seats){
        if(movie == null) throw new IllegalArgumentException("movie is required");
        if(seats< 0) throw new IllegalArgumentException("seats cannot be negative");
        availableSeatsByMovie.put(movie, seats);
    }

    public int getAvailableSeats(Movie movie){
        Integer seats = availableSeatsByMovie.get(movie);
        return seats == null ? 0 : seats;
    }

    public void showSchedule(){
        System.out.println("=== Cinema: "+ name + "===");
        if (availableSeatsByMovie.isEmpty()) {
            System.out.println("No movies yet.");
            return;
        }
        for (Map.Entry<Movie, Integer> entry : availableSeatsByMovie.entrySet()) {
            Movie movie = entry.getKey();
            int seats = entry.getValue();
            System.out.println(movie.getTitle() + "(id=" + movie.getId() + ", age=" + movie.getAgeRestriction() + "+, price=" + movie.getTicketPrice() + ") -> seats: " + seats);
        }
    }

    public String bookTickets(Viewer viewer, Movie movie, int quantity){
        if(viewer == null) return "Booking failed: viewer is null";
        if(movie == null) return "Booking failed: movie is null";
        if(quantity <= 0) return "Booking failed: quantity must be positive";

        if (!availableSeatsByMovie.containsKey(movie)){
            return "Booking failed: movie is not available in this cinema";
        }

        if(!viewer.canWatch(movie)){
            return "Booking failed: age restriction! (" + movie.getAgeRestriction() + "+)";
        }

        int availableSeats = availableSeatsByMovie.get(movie);
        if(availableSeats < quantity){
            return "Booking failed: not enough seats (available "+ availableSeats + ")";
        }

        double totalCost = movie.priceForTickets(quantity);
        if(!viewer.pay(totalCost)){
            return "Booking failed: not enough money";
        }

        availableSeatsByMovie.put(movie, availableSeats- quantity);
        return "Booking success: "+ viewer.getName() + " bought " + quantity+
                "ticket(s) for '"+ movie.getTitle() + "' total=" + totalCost;

    }

}
