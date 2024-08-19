package app2;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Lista implements Serializable{
    private List <Zaposleni> listaZaposlenih;

    public Lista() {
        this.listaZaposlenih = new ArrayList();
    }

    public void dodajZaposlenog(Zaposleni z) {
        this.listaZaposlenih.add(z);
    }
  public String [][]prikaz () {
        String podaci [][] = new String [this.listaZaposlenih.size()][4];
        Iterator<Zaposleni> iter = this.listaZaposlenih.iterator();
        int brojac = 0;
        
        while(iter.hasNext()) {
            String [] zaposleni = new String [5];
            Zaposleni z = iter.next();
            
            zaposleni[0] = z.getIme();
            zaposleni[1] = z.getPrezime();
            zaposleni[2] = z.getId();
            zaposleni[3] = z.getPocetakRada();
            zaposleni[4] = z.getZavrsetakRada();
            
            podaci[brojac] = zaposleni;
            brojac++;
            
        }
        return podaci;
    }
    public void prikaziUTabeli(JTable jtb) {
        String kolone[]={"Ime", "Prezime","ID","Vrijeme pocetka rada","Vrijeme zavrsetka rada"};
        jtb.setModel(new javax.swing.table.DefaultTableModel(prikaz(), kolone));
    }
   
     public void ocistiInpute(JTextField jtf1, JTextField jtf2,JTextField jtf3){
        
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
       
    }
      public void prikaziPodatkeUTextField(JTable jtb, JTextField imeTxt, JTextField prezimeTxt, JTextField idTxt) {
        
        int red = jtb.getSelectedRow();
        
        imeTxt.setText(jtb.getValueAt(red, 0).toString());
        prezimeTxt.setText(jtb.getValueAt(red, 1).toString());
        idTxt.setText(jtb.getValueAt(red, 2).toString());
  
    }
    
      public void izmenaPodataka(JTable jtb, String ime, String prezime, String id) {
    
        Iterator <Zaposleni> iter = this.listaZaposlenih.iterator();
        int red = jtb.getSelectedRow();
        
        while (iter.hasNext()) {            
            Zaposleni z = iter.next();
            if(red>=0) {
                this.listaZaposlenih.get(red).setIme(ime);
                this.listaZaposlenih.get(red).setPrezime(prezime);
                this.listaZaposlenih.get(red).setId(id);
                
                prikaziUTabeli(jtb);
                
            }
        }    
    }
    public void izmenaPodataka2(JTable jtb, JTextField imeTxt, JTextField prezimeTxt, JTextField idTxt) {
        
        izmenaPodataka(jtb, imeTxt.getText(), prezimeTxt.getText(), idTxt.getText());
    }

    
    
    public void obrisiPodatke(JTable jtb) {
          int selectedRow = jtb.getSelectedRow();
    
    if (selectedRow != -1) { // Provera da li je odabran red
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        model.removeRow(selectedRow); // Ukloni izabrani red iz modela tabele
    }
       
    }

    
/*------------------------------------------baza podataka---------------------------------------------*/
    
    
    public void ucitajPodatkeIzBaze(DefaultTableModel model) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
        String query = "SELECT * FROM lista_dezurstava";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        // Clear existing table data
        model.setRowCount(0);

        // Populate table with data from the result set
        while (resultSet.next()) {
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String id = resultSet.getString("id");
            String pocetakRada = resultSet.getString("pocetak_rada");
            String zavrsetakRada = resultSet.getString("zavrsetak_rada");

            model.addRow(new Object[]{ime, prezime, id, pocetakRada, zavrsetakRada});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
   public void azurirajPodatkeUBazi(String ime, String prezime, String id) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
        String query = "UPDATE lista_dezurstava SET ime=?, prezime=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, ime);
        statement.setString(2, prezime);
        statement.setString(3, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Podaci uspješno ažurirani.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
   public void azurirajPodatkeUBazi2(JTextField imeTf, JTextField prezimeTf, JTextField idTf) {
       
       azurirajPodatkeUBazi(imeTf.getText(), prezimeTf.getText(), idTf.getText());
       
   }
   
  public void obrisiPodatkeIzBaze(int rowIndex) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
        // Prvo dobijamo ID reda na zadatom indeksu
        String query = "SELECT id FROM lista_dezurstava LIMIT 1 OFFSET ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, rowIndex);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String id = resultSet.getString("id");
            
            // Nakon što dobijemo ID, koristimo ga da obrišemo red iz tabele
            String deleteQuery = "DELETE FROM lista_dezurstava WHERE id=?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, id);
            int rowsDeleted = deleteStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Podaci uspješno obrisani.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    public void sacuvajPodatkeUBazi(String ime, String prezime, String id, String pocetakRada, String zavrsetakRada) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
        String query = "INSERT INTO lista_dezurstava (ime, prezime, id, pocetak_rada, zavrsetak_rada) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, ime);
        statement.setString(2, prezime);
        statement.setString(3, id);
        statement.setString(4, pocetakRada);
        statement.setString(5, zavrsetakRada);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Novi podaci uspješno sačuvani.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void azurirajPodatkeUBaziZavrsetakRada(String id, String zavrsetakRada) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
        String query = "UPDATE lista_dezurstava SET zavrsetak_rada=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, zavrsetakRada);
        statement.setString(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Vrijeme završetka rada uspješno ažurirano.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean validateCredentials(String username, String password) {
    boolean isValid = false;
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", ""); 
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM admini WHERE Korisnicko_ime = ? AND Lozinka = ?")) {
        
        statement.setString(1, username);
        statement.setString(2, hashPassword(password)); // Koristimo hesiranu lozinku prilikom provere u bazi
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                isValid = true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return isValid;
}

public String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-1"); // Koristimo SHA-1 umesto SHA-256
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}

public void sacuvajAdminaUBazu(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav", "root", "")) {
            String hashedPassword = hashPassword(password); // Hesiramo lozinku pre ubacivanja u bazu
           
            String query = "INSERT INTO admini (Korisnicko_ime, Lozinka) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword); // Ubacujemo hesiranu lozinku u bazu

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 

    

}

