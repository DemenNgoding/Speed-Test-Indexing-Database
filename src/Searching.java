import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Searching {
    public void getRecordByIdInSQL(int id) {

        KoneksiDB koneksiDB = new KoneksiDB();
        Connection conn = koneksiDB.connect_to_db("UAS-datstruk", "postgres", "123456");

        try {
            String query = "SELECT name, country FROM station WHERE id = " + id;

            long startTimeSQL = System.currentTimeMillis();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Record found in SQL");
            } else {
                System.out.println("Record not found in SQL");
            }

            long endTimeSQL = System.currentTimeMillis();
            long durationSQL = endTimeSQL - startTimeSQL;

            System.out.println("Time taken to query directly from SQL: " + durationSQL + " ms");

            conn.close(); // Jangan lupa tutup koneksi setelah selesai
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getRecordByIdInBTree(int id) {
        BTree bTree = new BTree(3);

        for (int i = 1; i <= id; i++) {
            bTree.Insert(i);
        }

        long startTimeBTree = System.currentTimeMillis();
        boolean foundInBTree = bTree.Contain(id);
        long endTimeBTree = System.currentTimeMillis();
        long durationBTree = endTimeBTree - startTimeBTree;

        if (foundInBTree) {
            System.out.println("Record found in B-Tree");
        } else {
            System.out.println("Record not found in B-Tree");
        }

        System.out.println("Time taken to search directly from B-Tree: " + durationBTree + " ms");
    }
}
