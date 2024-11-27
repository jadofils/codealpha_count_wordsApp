
package wordCount;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

/**
 *
 * @author jdfils
 */
public class WordCounter extends javax.swing.JFrame {

    /**
     * Creates new form WordCounter
     */
    public WordCounter() {
        initComponents();
     
    }
   // Function to get the username and validate it
    public String getName() {
        String username = userName.getText().trim(); // Trim to avoid leading/trailing spaces
        if (username.isEmpty()) {
            wordCountLabel.setText("Please enter your name to count the words.");
            wordCountLabel.setForeground(Color.RED);  // Set color to red for error
            return null;  // Return null if name is empty
        }
        return username;
    }

    // Function to count words in a given text
    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0; // No words in empty or null text
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Function to display word count
    private void showWordCount(String text) {
        String name = getName();  // Validate the name first
        if (name != null) {  // Only proceed if the name is valid
            int wordCount = countWords(text);
            if (wordCount == 0) {
                wordCountLabel.setText("Please enter some text to count the words.");
                wordCountLabel.setForeground(Color.RED);
            } else {
                wordCountLabel.setText(name + " has " + wordCount + " words.");
                wordCountLabel.setForeground(Color.BLUE);  // Set text color to blue
            }
        }
    }

    // Function to reset the fields
    private void resetFields() {
        userName.setText("");
        textAreaToCountWords.setText("");
        wordCountLabel.setText(""); // Clear the word count label
    }
  private void saveToPDF(String username, String content, String currentDate) {
        try {
            // Validate inputs
            if (username == null || username.trim().isEmpty()) {
                showErrorMessage("Username is required to save PDF");
                return;
            }

            if (content == null || content.trim().isEmpty()) {
                showErrorMessage("No content to save");
                return;
            }

            // Create uploads directory if it doesn't exist
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdirs()) {
                    showErrorMessage("Failed to create uploads directory");
                    return;
                }
            }

            // Generate unique filename
            String fileName = generateUniqueFileName(username, currentDate);
            String filePath = uploadDir.getPath() + File.separator + fileName;

            // Create PDF
            try (PdfWriter writer = new PdfWriter(filePath);
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document document = new Document(pdfDoc)) {
                
                // Add content to PDF with improved formatting
                document.add(new Paragraph("Word Count Report")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18)
                    .setBold());
                
                document.add(new Paragraph("Username: " + username)
                    .setFontSize(12));
                
                document.add(new Paragraph("Date: " + currentDate)
                    .setFontSize(12));
                
                int wordCount = countWords(content);
                document.add(new Paragraph("Word Count: " + wordCount)
                    .setFontSize(12));
                
                document.add(new Paragraph("\nContent:")
                    .setFontSize(12)
                    .setBold());
                
                document.add(new Paragraph(content)
                    .setFontSize(10));
            }

            // Show success message
            JOptionPane.showMessageDialog(
                this, 
                "PDF saved successfully at: codealpha_word_count/" + filePath, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            // Handle potential IO errors
            showErrorMessage("Error saving PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to show error messages
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            this, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Helper method to generate a unique filename
    private String generateUniqueFileName(String username, String currentDate) {
        // Use system time to ensure unique filename
        return username + "_" + currentDate + "_" + System.currentTimeMillis() + ".pdf";
    }

    // Method to be called when download button is clicked
    private void downloadPDF() {
        // Get username and validate
        String username = getName();
        if (username == null) {
            return; // Username validation failed
        }
        
        // Get content
        String content = textAreaToCountWords.getText();
        
        // Get current date
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        // Save to PDF
        saveToPDF(username, content, currentDate);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelForHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaToCountWords = new javax.swing.JTextArea();
        countButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        printDownloadButton = new javax.swing.JButton();
        wordCountLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CodeALpha Word COunter Advanced Application");

        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Enter your Name Please?");

        jLabelForHeader.setText("Enter Or type your Sentences here on the following");

        textAreaToCountWords.setColumns(20);
        textAreaToCountWords.setRows(5);
        jScrollPane1.setViewportView(textAreaToCountWords);

        countButton.setText("Count");
        countButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        printDownloadButton.setText("Print | Download");
        printDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printDownloadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(countButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetButton)
                                .addGap(34, 34, 34)
                                .addComponent(printDownloadButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelForHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(wordCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelForHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wordCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countButton)
                    .addComponent(resetButton)
                    .addComponent(printDownloadButton))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void countButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countButtonActionPerformed
  String text = textAreaToCountWords.getText();
        showWordCount(text);
    }//GEN-LAST:event_countButtonActionPerformed

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
        
        name();
    }//GEN-LAST:event_userNameActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
                resetFields();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void printDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDownloadButtonActionPerformed
       
                downloadPDF(); // Reuse the downloadPDF method

// TODO add your handling code here:
    }//GEN-LAST:event_printDownloadButtonActionPerformed

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
            java.util.logging.Logger.getLogger(WordCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordCounter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> new WordCounter().setVisible(true));
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WordCounter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton countButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelForHeader;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printDownloadButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextArea textAreaToCountWords;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel wordCountLabel;
    // End of variables declaration//GEN-END:variables

    private void name() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
