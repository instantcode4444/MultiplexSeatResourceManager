public class Node {
    public int[][] seat = new int[10][7];
    public String name;
    public String password;
    public int row, col;
    public Node next;
    public Node prev;

    public Node() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                seat[i][j] = 0;
            }
        }
    }

    public void displaySeats() {
        System.out.println("\nFollowing are the seats:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(seat[i][j] == 0 ? "\t| | " : "\t|B| ");
            }
            System.out.println();
        }
    }

    public void bookSeat(int row, int col) {
        if (row < 0 || row >= 10 || col < 0 || col >= 7) {
            System.out.println("Invalid seat coordinates.");
            return;
        }
        seat[row][col] = 1;
        System.out.println("Seat booked at row " + row + ", col " + col);
    }

    public void cancelSeat(int row, int col) {
        if (seat[row][col] == 1) {
            seat[row][col] = 0;
            System.out.println("Seat canceled at row " + row + ", col " + col);
        } else {
            System.out.println("Seat was not booked.");
        }
    }
}
