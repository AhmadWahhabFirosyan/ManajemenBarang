package databarang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class mainscreen {

    private static final String URL = "jdbc:mysql://localhost:3306/data_barang";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static ResultSet executeQuery(String query){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }

        catch (Exception e){
            return null;
        }
    }

    public static List<barang> getbarang(){
        List<barang> arrayListbarang = new ArrayList<>();
        ResultSet resultSet = executeQuery("select * from barang");

        try {
            while (resultSet.next()){
                String nama_barang = resultSet.getString("nama barang");
                String stok_barang = resultSet.getString("stok barang");
                Integer harga_barang = Integer.parseInt(resultSet.getString("harga barang"));

                System.out.println(nama_barang);
                System.out.println(stok_barang);
                System.out.println(harga_barang);
                System.out.println();

                barang barang = new barang();
                barang.setHarga_barang(harga_barang);
                barang.setStok_barang(stok_barang);
                barang.setNama_barang(nama_barang);
                arrayListbarang.add(barang);
            }
        }

        catch (Exception e){

        }
        return arrayListbarang;
    }
    public static void executeSql(String sql){
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
        }

        catch (Exception e){

        }
    }

    private static void insertbarang(barang barang){
        String sql = "insert into barang values (" +
                "'" + barang.getNama_barang() + "'," +
                "'" + barang.getStok_barang() + "'," +
                "'" + barang.getHarga_barang() + "')";
        executeSql(sql);
    }

    private static void updatebarang(barang barang){
        String sql = "update barang set " +
                "nama_barang = '"+ barang.getNama_barang() +"', " +
                "harga_barang = '"+ barang.getHarga_barang() +"' " +
                "where stok_barang = '"+ barang.getStok_barang() +"'";
        executeSql(sql);
    }

    private static void deletebarang(String stok_barang){
        String sql = "delete from barang " +
                "where stok_barang = '"+ stok_barang +"'";
        executeSql(sql);
    }
    public static void main(String[] args) {
        barang barang = new barang();

        barang.setNama_barang("Laptop Asus");
        barang.setStok_barang("20");
        barang.setHarga_barang(6000000);

        deletebarang(barang.getStok_barang());
        getbarang();
    }
}
