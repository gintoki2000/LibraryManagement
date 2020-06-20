/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.views;

import com.librarymanagement.ConnectionHelper;
import com.librarymanagement.entities.Book;
import com.librarymanagement.models.BookTableModel;
import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.repositories.BookRepositoryImpl;
import com.librarymanagement.repositories.CategoryRepository;
import com.librarymanagement.repositories.CategoryRepositoryImpl;
import com.sun.tools.javac.util.ArrayUtils;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {
    
    private final BookTableModel bookTableModel;
    
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    
    private final ComboBoxModel findByComboBoxModel;
    private final ComboBoxModel orderByComboBoxModel;
    
    public MainWindow(ConnectionHelper connectionHelper) {
        
        bookRepository = new BookRepositoryImpl(connectionHelper);
        categoryRepository = new CategoryRepositoryImpl(connectionHelper);
        
        bookTableModel = new BookTableModel();
        
        String[] orderByOptions = new String[BookRepository.COLUMN_NAMES.length + 1];
        orderByOptions[0] = "None";
        System.arraycopy(BookRepository.COLUMN_NAMES, 0, orderByOptions, 1, BookRepository.COLUMN_NAMES.length);
        
        findByComboBoxModel = new DefaultComboBoxModel(BookRepository.COLUMN_NAMES);
        orderByComboBoxModel = new DefaultComboBoxModel(orderByOptions);
        
        findByComboBoxModel.setSelectedItem(BookRepository.COLUMN_NAMES[0]);
        orderByComboBoxModel.setSelectedItem(orderByOptions[0]);
        
        initComponents();
        
        refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        findByComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        groupByComboBox = new javax.swing.JComboBox<>();
        searchTextField = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bookTable.setModel(bookTableModel);
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        findByComboBox.setModel(findByComboBoxModel);

        jLabel1.setText("FIND BY");

        jLabel3.setText("GROUP BY");

        groupByComboBox.setModel(orderByComboBoxModel);
        groupByComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupByComboBoxActionPerformed(evt);
            }
        });

        findButton.setText("FIND");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(findByComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(groupByComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(groupByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(findButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        int i = bookTable.getSelectedRow();
        Book book = bookTableModel.getBooks().get(i);
        
        try {
            UpdatingDialog updateDialog = new UpdatingDialog(this, book, bookRepository, bookTableModel, Utils.getCategoryIDs(categoryRepository));
        } catch (SQLException ex) {
            showSQLExceptionDialog(ex);
        }
    }//GEN-LAST:event_bookTableMouseClicked

    private void groupByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupByComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_groupByComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            AddingDialog dialog = new AddingDialog(this, bookRepository, bookTableModel, Utils.getCategoryIDs(categoryRepository));
            dialog.setVisible(true);
        } catch (SQLException ex) {
            showSQLExceptionDialog(ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // TODO add your handling code here:
        String inValue = searchTextField.getText();
        String inBy = (String) findByComboBox.getSelectedItem();
        String inGroupBy = (String) groupByComboBox.getSelectedItem();
        try {
            if (inValue != null && !inValue.equals("")) {
                if (inGroupBy.equals("None")) {
                    bookTableModel.setBooks(bookRepository.search(inValue, inBy));
                }
                else {
                    bookTableModel.setBooks(bookRepository.search(inValue, inBy, inGroupBy));
                }
            } else {
                bookTableModel.setBooks(bookRepository.getAllBooks());
            }
        } catch (SQLException sqlException) {
            showSQLExceptionDialog(sqlException);
        }
    }//GEN-LAST:event_findButtonActionPerformed
    
    private void refresh() {
        try {
            bookTableModel.setBooks(bookRepository.getAllBooks());
        } catch (SQLException ex) {
            showSQLExceptionDialog(ex);
        }
    }
    
    private void showSQLExceptionDialog(SQLException sqlException) {
        JOptionPane.showMessageDialog(this, sqlException.getMessage(), "SQL ERROR", JOptionPane.ERROR_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable bookTable;
    private javax.swing.JButton findButton;
    private javax.swing.JComboBox<String> findByComboBox;
    private javax.swing.JComboBox<String> groupByComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

}
