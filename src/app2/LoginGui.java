
package app2;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class LoginGui extends javax.swing.JFrame {

    
    public LoginGui() {
        initComponents();
         BackgroundImagePanel backgroundPanel = new BackgroundImagePanel("C:\\Users\\Nemanja\\Documents\\NetBeansProjects\\App2_1\\img2.png");

        // Postavljanje layout-a za background panel
        backgroundPanel.setLayout(null);

        // Postavljanje dimenzija panela na veličinu prozora
        backgroundPanel.setBounds(0, 153, 400, 200);

        // Dodavanje background panela na content pane
        getContentPane().add(backgroundPanel);
          getContentPane().setBackground(Color.WHITE);
          
          BackgroundImagePanel backgroundPane2 = new BackgroundImagePanel("C:\\Users\\Nemanja\\Documents\\NetBeansProjects\\App2_1\\security.jpg");
          backgroundPane2.setLayout(null);
          backgroundPane2.setBounds(30, 0, 110, 100);
          getContentPane().add(backgroundPane2);
  
           try {
            // Load the image from the specified file path
            Image icon = ImageIO.read(new File("C:\\Users\\Nemanja\\Documents\\NetBeansProjects\\App2_1\\security2.jpg"));
            
            // Resize the image to the desired dimensions (e.g., 64x64)
            Image scaledIcon = icon.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
            
            // Set the scaled icon image for the frame
            setIconImage(scaledIcon);
        } catch (IOException e) {
            // Handle the exception if the image file is not found
            e.printStackTrace();
        }
    }
    
    Lista lista = new Lista();
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UsernameTxt = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Security Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("LOGIN");

        jLabel2.setText("Korisnicko ime:");

        jLabel3.setText("Lozinka:");

        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(UsernameTxt)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButton1)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String username = UsernameTxt.getText();
    String password = new String(jPasswordField1.getPassword());

    if (this.lista.validateCredentials(username, password)) {
        GUI gui = new GUI(username);
        gui.setVisible(true);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Neispravno korisničko ime ili lozinka.", "Greška", JOptionPane.ERROR_MESSAGE);
    }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
       
        

      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UsernameTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
