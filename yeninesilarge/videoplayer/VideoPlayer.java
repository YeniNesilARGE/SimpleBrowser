package yeninesilarge.videoplayer;

import yeninesilarge.image.ImagePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salimsah
 */
public class VideoPlayer extends javax.swing.JFrame {
    final ImageIcon icn1;
    BufferedImage image = new BufferedImage(20,20,6);
    JFileChooser fc;
    File[] files;
    BufferedImage[] images;
    boolean loop = false, 
            play = true;
    
    ImagePanel id;
    ImageDrawer idrw = new ImageDrawer();
    long delay = 100;
    
    
    FileFilter ff = new FileFilter() {
            String[] extensions = {"jpeg","png","bmp","gif","jpg"};
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                  return true;
                } else {
                  String path = f.getAbsolutePath().toLowerCase();
                  for (int i = 0, n = extensions.length; i < n; i++) {
                    String extension = extensions[i];
                    if ((path.endsWith(extension) && (path.charAt(path.length() 
                              - extension.length() - 1)) == '.')) {
                      return true;
                    }
                  }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Image Files(jpeg, png, bmp, gif, jpg)";
            }
        };
    class ImageDrawer implements Runnable{
        Thread thd = null;
        int i;
        boolean stp = false;
        VideoPlayer ths;
        boolean brk = false;
        public void setI(int i){
            this.i = i;
        }
        public void setThis(VideoPlayer typeThis){
            ths = typeThis;
        }
        public void stop(){
            stp = true;
        }
        public void start() {  
            stp = false; thd = new Thread(this); thd.start(); 
        }
        public void brk(boolean c){
            brk = c;
        }
        
        @Override
        public void run() {
            do{
                    for (i = 0; i < files.length; i++) {
                        System.out.println(i);
                        while(!play){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            if (brk == true) {
                                break;
                            }
                        }
                        
                        id.setImage(images[i]);
                        id.setPicSize(OutPanel.getWidth(),OutPanel.getHeight());
                        OutPanel.removeAll();
                        OutPanel.add(id);
                        jSlider.setValue(i);
                        remainingjLabel.setText(String.valueOf(i+1));
                        totaljLabel.setText(String.valueOf(files.length - i-1));
                        ths.repaint();
                        if (!brk) {
                            try {
                            Thread.sleep(delay);
                            } catch (InterruptedException ex) {
                            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        brk=false;
                        
                        
                    }
                    System.out.println("out:    "+i);
            }while(loop && !stp);
            idrw.thd= null;
        }
        
    }
    /**
     * Creates new form Entertainment
     */
    public VideoPlayer() {
        
        idrw.setThis(this);
        id = new ImageDisplay();
        
        try {
            Graphics2D g = image.createGraphics();
            g.drawImage(ImageIO.read(new File("/Users/salimsah/NetBeansProjects/BLM305-TermWork/src/main/repeat.png")), 0, 0, 20, 20, null);
            g.dispose();
        } catch (IOException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        icn1 = new ImageIcon(image);
        initComponents();
        repeatjButton.setIcon(icn1);
        
        id.setPicSize(OutPanel.getWidth(),OutPanel.getHeight());
        id.setBounds(0, 0, OutPanel.getWidth(), OutPanel.getHeight());
        
        fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setCurrentDirectory(new File("/"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setDialogTitle("Select Frames");
        fc.setMultiSelectionEnabled(true);
        
        fc.setFileFilter(ff);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OutPanel = new javax.swing.JPanel();
        backjButton = new javax.swing.JButton();
        playjButton = new javax.swing.JButton();
        forwardjButton = new javax.swing.JButton();
        jSlider = new javax.swing.JSlider();
        videoNamejLabel = new javax.swing.JLabel();
        repeatjButton = new javax.swing.JToggleButton();
        totaljLabel = new javax.swing.JLabel();
        chooseFramejButton = new javax.swing.JButton();
        pathjLabel = new javax.swing.JLabel();
        remainingjLabel = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout OutPanelLayout = new javax.swing.GroupLayout(OutPanel);
        OutPanel.setLayout(OutPanelLayout);
        OutPanelLayout.setHorizontalGroup(
            OutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OutPanelLayout.setVerticalGroup(
            OutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        backjButton.setText("<<");
        backjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButtonActionPerformed(evt);
            }
        });

        playjButton.setText("▶");
        playjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playjButtonActionPerformed(evt);
            }
        });

        forwardjButton.setText(">>");
        forwardjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardjButtonActionPerformed(evt);
            }
        });

        jSlider.setMaximum(0);
        jSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderStateChanged(evt);
            }
        });
        jSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSliderMouseClicked(evt);
            }
        });

        videoNamejLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        repeatjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatjButtonActionPerformed(evt);
            }
        });

        totaljLabel.setText("0");

        chooseFramejButton.setText("Choose Frames");
        chooseFramejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFramejButtonActionPerformed(evt);
            }
        });

        remainingjLabel.setText("0");

        jSpinner1.setValue(delay);
        jSpinner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSpinner1MouseClicked(evt);
            }
        });

        jButton1.setText("Set");
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
                .addGap(12, 12, 12)
                .addComponent(backjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forwardjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repeatjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(remainingjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(videoNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pathjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooseFramejButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(OutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseFramejButton)
                        .addComponent(pathjLabel))
                    .addComponent(videoNamejLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(repeatjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(forwardjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remainingjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseFramejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFramejButtonActionPerformed
        // TODO add your handling code here:
        fc.showOpenDialog(this);
        if(0 == JFileChooser.APPROVE_OPTION){
            idrw.stop();
            files = fc.getSelectedFiles();
            images = new BufferedImage[files.length];
            for (int i = 0; i < files.length; i++) {
                try {
                    images[i] = ImageIO.read(files[i]);
                } catch (IOException ex) {
                    Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pathjLabel.setText(files[0].getParentFile().getAbsolutePath());
            videoNamejLabel.setText(files[0].getParentFile().getName());
            jSlider.setMaximum(files.length-1);
            idrw.start();
            
        }
        
    }//GEN-LAST:event_chooseFramejButtonActionPerformed

    private void repeatjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatjButtonActionPerformed
        // TODO add your handling code here:
        if(loop==false){
            loop = true;
        }
        else{
            loop = false;
        }
    }//GEN-LAST:event_repeatjButtonActionPerformed

    private void playjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playjButtonActionPerformed
        // TODO add your handling code here:
        if (idrw.i == images.length) {
            play = true;
            idrw.start();
        }
        else if (play) {
            play = false;
        }
        else if(!play){
            play = true;
        }
    }//GEN-LAST:event_playjButtonActionPerformed

    private void jSpinner1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jSpinner1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        delay = Integer.toUnsignedLong((int) jSpinner1.getValue());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSliderStateChanged

    private void forwardjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardjButtonActionPerformed
        // TODO add your handling code here:
        if (loop == true) {
            if (idrw.i < images.length-1 && idrw.i != 0) {
                idrw.setI(idrw.i+1);
                if (play == false){
                    idrw.brk(true);
                }
            }
            else if(idrw.i == images.length-1){
                if (play == false){
                    idrw.brk(true);
                }
            }
        }
        else {
            if (idrw.i < images.length - 1) {
                idrw.setI(idrw.i + 1);
                if (play == false) {
                    idrw.brk(true);
                }
            } else if (idrw.i == images.length - 1) {
                if (play == false) {
                    idrw.brk(true);
                }
            }
        }
    }//GEN-LAST:event_forwardjButtonActionPerformed

    private void backjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButtonActionPerformed
        // TODO add your handling code here:
            if(idrw.thd == null || idrw.i == images.length){
                idrw.stop();
                play = false;
                idrw.start();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                idrw.setI(images.length-3);
                idrw.brk(true);
            }
            else{
                if (idrw.i > 2) {
                    idrw.setI(idrw.i-3);
                    if (play == false){
                        idrw.brk(true);
                    }
                }
                else if(idrw.i == 2){
                    idrw.setI(0);
                    if (play == false){
                        idrw.brk(true);
                    }
                }
                else if(idrw.i == 0){
                    idrw.setI(images.length-3);
                    if (play == false){
                        idrw.brk(true);
                    }
                }
            }
    }//GEN-LAST:event_backjButtonActionPerformed

    private void jSliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderMouseClicked
        // TODO add your handling code here:
        idrw.setI(jSlider.getValue());
        if (play == false){
            idrw.brk(true);
        }
        
    }//GEN-LAST:event_jSliderMouseClicked

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
            java.util.logging.Logger.getLogger(VideoPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VideoPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VideoPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VideoPlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VideoPlayer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OutPanel;
    private javax.swing.JButton backjButton;
    private javax.swing.JButton chooseFramejButton;
    private javax.swing.JButton forwardjButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JSlider jSlider;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel pathjLabel;
    private javax.swing.JButton playjButton;
    private javax.swing.JLabel remainingjLabel;
    private javax.swing.JToggleButton repeatjButton;
    private javax.swing.JLabel totaljLabel;
    private javax.swing.JLabel videoNamejLabel;
    // End of variables declaration//GEN-END:variables
}
