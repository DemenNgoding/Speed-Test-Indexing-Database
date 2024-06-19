import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Searching {
    public void getRecordByIdInSQL(int id) {
        KoneksiDB koneksiDB = new KoneksiDB();
        Connection conn = koneksiDB.connect_to_db("UAS-datstruk", "postgres", "123456");

        try {
            String query = "SELECT name FROM station WHERE id = " + id;

            long startTimeSQL = System.currentTimeMillis();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String stationName = rs.getString("name");
                System.out.println("Record found in SQL: " + stationName);
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
        BTree bTree = new BTree(10);

        KoneksiDB koneksiDB = new KoneksiDB();
        Connection conn = koneksiDB.connect_to_db("UAS-datstruk", "postgres", "123456");

        try {
            String query = "SELECT id, name FROM station";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String stationName = null;

            while (rs.next()) {
                int stationId = rs.getInt("id");
                String name = rs.getString("name");
                bTree.Insert(name);
                if (stationId == id) {
                    stationName = name;
                }
            }

            long startTimeBTree = System.currentTimeMillis();
            boolean foundInBTree = stationName != null && bTree.Contain(stationName);
            long endTimeBTree = System.currentTimeMillis();
            long durationBTree = endTimeBTree - startTimeBTree;

            if (foundInBTree) {
                System.out.println("Record found in B-Tree: " + stationName);
            } else {
                System.out.println("Record not found in B-Tree");
            }

            System.out.println("Time taken to search directly from B-Tree: " + durationBTree + " ms");

            conn.close(); // Jangan lupa tutup koneksi setelah selesai
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
