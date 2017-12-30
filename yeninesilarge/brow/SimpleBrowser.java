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
import java.util.Set;
import java.util.HashSet;

import yeninesilarge.application.*;


/**
 *
 * @author nskmlylm
 */
public class SimpleBrowser extends javax.swing.JFrame implements KeyListener {

    private DefaultListModel<String> model;
    private String currentPath;
	private Browser browser = new Browser();
    public SimpleBrowser() {
        initComponents();
        model = new DefaultListModel<>();
        listFile.setModel(model);
        listFile.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        currentPath = System.getProperty("user.home");

        filesOfFolder(currentPath);//jList e klasördeki dosyaları ekleyen metot

        listFile.addKeyListener(this);

		browser.loadApplications();
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

    public void filesOfFolder(String path) {
		File file = new File(path); 
		if( file.getParent() == null ) 
			return;
	
		model.removeAllElements();
        model.add(0, "../..");
	
        File[] listOfFiles = file.listFiles(); 
		if(listOfFiles == null ) return;
        for (int i = 0; i < listOfFiles.length; i++) {
            model.addElement(listOfFiles[i].getName()); 
        }

        currentPath = file.getPath(); //updates parent

        this.setTitle(file.getPath());
    }

    public void keyReleased(KeyEvent e) { } //not uses

    public void keyTyped(KeyEvent e) { } // not uses

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {//todo
            List<String> selectedFiles = listFile.getSelectedValuesList();
 			
			if ( selectedFiles.size() == 1 ) {
				File file = new File(currentPath, selectedFiles.get(0) );

				if ( file.isDirectory() ) {
				    String path = file.getPath();
				    filesOfFolder(path);
					return;
				}
			}
			

			if ( !isListSameCategory(selectedFiles) ) return;

			File[] files = new File[selectedFiles.size()];
			for ( int i = 0; i < selectedFiles.size(); i++ ) {
				String file = selectedFiles.get(i);
				
				File f = new File(currentPath, file);
				if( f.isDirectory() ) return;
				
				files[i] = f; 
	
			}

			String extension = getExtension(files[0]);

			runWith(extension, files);
				
        } else if (e.getKeyCode() == KeyEvent.VK_F5){
		ApplicationManager appManager = ApplicationManager.getInstance();
			appManager.clearAll();
			browser.loadApplications();
		}
    }

	private void runWith(String extension, File[] files) {
		ApplicationManager appManager = ApplicationManager.getInstance();
		List<SimpleApplication> appList;
		try {
			appList = appManager.getApplications(extension);
		} catch ( ExtensionNotDefinedException ex ) {
			JOptionPane.showMessageDialog(this, "EXTENSION BULUNAMADI.");
			return;
		}

		String[] options = new String[appList.size()];
		for ( int i = 0; i < appList.size(); i++ ) {
			SimpleApplication app = appList.get(i);
			options[i] = app.name;
		}
		int reply = JOptionPane.showOptionDialog(this, "\n" + " Please choose a program..." + "\n\n", "Program Selecter",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if( reply == -1 ) return;
		SimpleApplication app = appList.get(reply);
		app.init(0,0,500,500);
		app.run(files);
	}

	private boolean isListSameCategory(List<String> fileList) {
		Set<String> extensions = new HashSet<>();

		String extension = Browser.getExtension(fileList.get(0));
		extensions.add(extension);
		for ( int i = 1; i<fileList.size(); i++ ){
			extension = Browser.getExtension(fileList.get(i));
			if ( extensions.add(extension) ) return false;
		}

		return true;
	}

    private void listFileMouseClicked(java.awt.event.MouseEvent evt) {

        if (evt.getClickCount() == 2) {
            // ../.. 
            if (listFile.getSelectedIndex() == 0) {
				File file = new File(currentPath);
				String path = file.getParent();
				filesOfFolder(path); // üst dosyaya geri dönülür.
				return;
            }

            String name = (String) listFile.getSelectedValue();
			File file = new File(currentPath, name);

            if ( file.isDirectory() ) {
                String path = file.getPath();
                filesOfFolder(path);
            }
        }

    }

   private void openCalendarActionPerformed(java.awt.event.ActionEvent evt) {
        ApplicationManager appManager = ApplicationManager.getInstance();
        SimpleApplication app = appManager.load("Calendar");

		File f = null;
        app.init(50, 50, 900, 900);
        app.run(f);
    }

    private void btnApplicationRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        AppRegister a = new AppRegister();
        a.setVisible(true);
    }

    public static void main(String args[]) {
		new SimpleBrowser().setVisible(true);

    }

    private javax.swing.JButton btnApplicationRegister;
    private javax.swing.JLabel lblFileList;
    private javax.swing.JList listFile;
    private javax.swing.JScrollPane scroolPane;
    private javax.swing.JButton openCalendar;

	// netbeans cop
	private void initComponents() {

        openCalendar = new javax.swing.JButton();
        scroolPane = new javax.swing.JScrollPane();
        listFile = new javax.swing.JList();
        lblFileList = new javax.swing.JLabel();
        btnApplicationRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        openCalendar.setText("Calendar");
        openCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCalendarActionPerformed(evt);
            }
        });

        listFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listFileMouseClicked(evt);
            }
        });
        scroolPane.setViewportView(listFile);

        lblFileList.setText("File List");

        btnApplicationRegister.setText("App Register");
        btnApplicationRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplicationRegisterActionPerformed(evt);
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
                        .addComponent(btnApplicationRegister)
                        .addGap(31, 31, 31)
                        .addComponent(openCalendar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFileList)
                            .addComponent(scroolPane, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApplicationRegister)
                    .addComponent(openCalendar))
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(lblFileList)
                .addGap(18, 18, 18)
                .addComponent(scroolPane, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }


}
