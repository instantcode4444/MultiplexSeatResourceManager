import java.io.*;
import java.util.*;

public class BookMovie {
    private Map<Integer, Node> bookings = new HashMap<>();
    private List<Movie> movies = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void firstPage() {
        while (true) {
            System.out.println("\n---- WELCOME ----");
            System.out.println("1. Admin Profile");
            System.out.println("2. SignUp");
            System.out.println("3. Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> admin();
                case 2 -> signUp();
                case 3 -> login();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void admin() {
        System.out.print("Enter admin password: ");
        String password = scanner.next();
        if (password.equals("admin")) {
            System.out.println("Admin logged in.");
            movieFunction();
        } else {
            System.out.println("Invalid admin password.");
        }
    }

    private void movieFunction() {
        System.out.println("1. Insert Movie");
        System.out.println("2. Show Movies");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> insertMovie();
            case 2 -> displayMovies();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void insertMovie() {
        System.out.print("Enter movie name: ");
        String name = scanner.next();
        System.out.print("Enter movie ID: ");
        int id = scanner.nextInt();
        movies.add(new Movie(id, name));
        bookings.put(id, new Node());
        System.out.println("Movie added.");
    }

    private void displayMovies() {
        for (Movie movie : movies) {
            System.out.println("ID: " + movie.getId() + ", Name: " + movie.getName());
        }
    }

    private void signUp() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        try (FileWriter fw = new FileWriter(email + ".txt")) {
            fw.write(email + "\n" + password + "\n" + name);
            System.out.println("Sign up successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        try (BufferedReader br = new BufferedReader(new FileReader(email + ".txt"))) {
            String storedEmail = br.readLine();
            String storedPassword = br.readLine();
            if (email.equals(storedEmail) && password.equals(storedPassword)) {
                System.out.println("Logged in.");
                book(email);
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (IOException e) {
            System.out.println("User not found.");
        }
    }

    private void book(String email) {
        System.out.println("1. Show Movies");
        System.out.println("2. Book Ticket");
        System.out.println("3. Cancel Ticket");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> displayMovies();
            case 2 -> bookTicket();
            case 3 -> cancelTicket();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void bookTicket() {
        System.out.print("Enter movie ID: ");
        int id = scanner.nextInt();
        Node node = bookings.get(id);
        if (node != null) {
            node.displaySeats();
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter col: ");
            int col = scanner.nextInt();
            node.bookSeat(row, col);
        } else {
            System.out.println("Invalid movie ID.");
        }
    }

    private void cancelTicket() {
        System.out.print("Enter movie ID: ");
        int id = scanner.nextInt();
        Node node = bookings.get(id);
        if (node != null) {
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter col: ");
            int col = scanner.nextInt();
            node.cancelSeat(row, col);
        } else {
            System.out.println("Invalid movie ID.");
        }
    }
}