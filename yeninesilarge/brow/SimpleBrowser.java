/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeninesilarge.brow;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import yeninesilarge.application.ApplicationManager;
import yeninesilarge.application.SimpleApplication;

/**
 *
 * @author nskmlylm
 */
public class SimpleBrowser extends javax.swing.JFrame implements KeyListener {

    DefaultListModel<String> model;
    File[] listOfFiles;
    File dosya;
    String parent, str, txtFormat, format;
    String title ="        Current path : ";
    List a ;

    public SimpleBrowser() {
        initComponents();

        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        parent = "C:\\";
        filesOfFolder(parent);//jList e klasördeki dosyaları ekleyen metot

        jList1.addKeyListener(this);

    }

    String getExtension(File f) {
        if (f.isDirectory()) {
            return null;
        }
        String extension = null;;
        String fileName = f.getName();
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public void filesOfFolder(String yol) {
        model = new DefaultListModel<>();
        jList1.setModel(model); //jList oluşturuldu.
        model.add(0, "../..");
        dosya = new File(yol); //file alındı
        parent = dosya.getParent(); // file ın bulunduğu klasör pathi
        this.setTitle(title + dosya.getPath());
        listOfFiles = dosya.listFiles(); //klasördeki dosyaların tutulduğu liste
        for (int i = 0; i < listOfFiles.length; i++) {
            model.addElement(listOfFiles[i].getName()); //jlist e dosyaları ekleme
        }
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            a = jList1.getSelectedValuesList();
            System.out.println(a);
            jButton1ActionPerformed(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        openCalendar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Select App");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        openCalendar.setText("Calendar");
        openCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCalendarActionPerformed(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("File List");

        jButton4.setText("App Register");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton4)
                        .addGap(52, 52, 52)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(openCalendar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(openCalendar))
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO 
        String[] options = {"Bilalinki", "Saliminki", "Furkanınki"};//istediğin kadar buton çıkartabilirsin...
        int reply = JOptionPane.showOptionDialog(this, "\n" + " Please choose a program..." + "\n\n", "Program Selecter", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (reply == 0) {
            System.out.println("1");
            ApplicationManager appManager = new ApplicationManager();
            //Browser browser = new Browser();

            SimpleApplication app = appManager.load("Paint");

            appManager.createAssociation(app);

            File f = new File("C:\\Users\\nskml\\Desktop\\Dosyalar\\Genel\\20449271_1632983346753166_4164704589958374336_o.jpg");

            //String extension = browser.getExtension(f);

            //app = appManager.getApplications(extension).get(0);
            app.init(50, 50, 900, 900);
            app.run(f);

            //buildFrame(paintPanel.title, panel, 300, 300, 800, 800);
           // browser.pack();
            //browser.setVisible(true);
        } else if (reply == 1) {
            System.out.println("2");

        } else if (reply == 2) {
            System.out.println("3");
        } else if (reply == -1) {
            System.out.println("çıktı");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void openCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openCalendarActionPerformed
        // TODO add your handling code here:
        ApplicationManager appManager = new ApplicationManager();
        //Browser browser = new Browser();

        SimpleApplication app = appManager.load("calendar");

        appManager.createAssociation(app);

        File f = null;

        app.init(50, 50, 900, 900);
        app.run(f);

        //buildFrame(paintPanel.title, panel, 300, 300, 800, 800);
       // browser.pack();
       // browser.setVisible(true);
    }//GEN-LAST:event_openCalendarActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            
            // ../.. ile parenta geçiş
            if (jList1.getSelectedIndex() == 0) {
                if (dosya.getPath().equals("C:\\")) {
                    System.out.println("C:\\ nin parentına geçemezsin.");
                } else {
                    filesOfFolder(parent); // üst dosyaya geri dönülür.
                }
            }

            // tıklanılan dosyanın file değeri = listOfFiles[i]
            String yol = "";
            String name = (String) jList1.getSelectedValue();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].getName().equals(name) && listOfFiles[i].isDirectory()) {
                    // klasörse içine girilir.
                    yol = listOfFiles[i].getPath();
                    filesOfFolder(yol);
                } else if (listOfFiles[i].getName().equals(name) && listOfFiles[i].isFile()) {
                    format = getExtension(listOfFiles[i]);
                    System.out.println(format);

                }

            }
        }


    }//GEN-LAST:event_jList1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        AppRegister a = new AppRegister();
        a.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimpleBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimpleBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimpleBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimpleBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimpleBrowser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openCalendar;
    // End of variables declaration//GEN-END:variables

}
