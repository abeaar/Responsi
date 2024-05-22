

package DAOdata;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Connector;
import DAOimplement.DAOImplementPerpustakaan;
import model.DataPerpustakaan;
import javax.swing.JOptionPane;

public class DAOPerpustakaan implements DAOImplementPerpustakaan {

    Connection connection;
    final String select = "SELECT * FROM buku";
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE buku SET judul = ?, penokohan = ?, akting = ?, nilai = ? WHERE judul = ?;";
    final String delete = "DELETE FROM movie WHERE judul = ?";

    public DAOPerpustakaan() {
        connection = Connector.connection();
    }

    public void insert (DataPerpustakaan dp){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dp.getJudul());
            statement.setString(2, dp.getPenulis());
            statement.setDouble(3, dp.getRating());
            statement.setDouble(4, dp.getHarga());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: Data dengan primary key tersebut sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void update(DataPerpustakaan dp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void select (DataPerpustakaan dp){}
    @Override
    public List<DataPerpustakaan> getAll() {
        List<DataPerpustakaan> dp = null;
        try {
            dp = new ArrayList<DataPerpustakaan>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                DataPerpustakaan buku = new DataPerpustakaan();
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setRating(resultSet.getDouble("rating"));
                buku.setHarga(resultSet.getDouble("harga"));
                buku.setId(resultSet.getInt("Id"));
                dp.add(buku);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataPerpustakaan.class.getName()).log(Level.SEVERE, null, ex);
        }
    return dp;
    }
}