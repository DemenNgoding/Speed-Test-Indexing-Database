import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membuat koneksi ke database
        try {
            Searching search = new Searching();

            // hal pertama yang harus diapstikan adalah koneksi database yang terletak pada file Searching.java pastikan username password dan database yang dimasukan adalah keterangan database yang sesuai dengan yang terinstall pada komputer anda
            
            // disini kita menggunakan database postgre sql

            // jika ingin running code nya maka compile file terlebih dahulu dengan command
            // javac -cp " didalam tanda kutip ini diisi path file postgresql-42.7.3.jar yang berada di folder lib" KoneksiDB.java BTree.java Searching.java Main.java

            // lalu jika ingin run code nya maka basukan command
            //java -cp " didalam tanda kutip ini diisi path file postgresql-42.7.3.jar yang berada di folder lib" Main.java

            
            System.out.println("Data yang berada pada database disini adalah sebanyak 64037 silahkan memilih id stasiun mana ayng ingin dicari antara 1 - 64037");
            System.out.println("===================");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.close();

            System.out.println("====================");
            System.out.println();

            search.getRecordByIdInSQL(id);

            System.out.println();
            System.out.println("====================");
            System.out.println();

            search.getRecordByIdInBTree(id);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
