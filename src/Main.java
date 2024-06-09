import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membuat koneksi ke database
        try {
            Searching search = new Searching();

                Scanner scanner = new Scanner(System.in);
                System.out.print("Input ID = ");
                int id = scanner.nextInt();
                scanner.close();

                search.getRecordByIdInSQL(id);
                search.getRecordByIdInBTree(id);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
