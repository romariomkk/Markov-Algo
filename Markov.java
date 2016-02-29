package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Markov extends javax.swing.JFrame {

    int tabIndex;
    String result;
    Vector<String> ruleSet;
    ArrayList<String> treeList;
    HashSet<String> treeSet;
    double uniquePercent;
    
    
    public Markov() throws IOException {
        initComponents();
        initElements();    
    }

    private void initElements() throws IOException{
        comboWords.removeAllItems();
        Scanner scanner = new Scanner(new File("src\\main\\text.txt"));
        while(scanner.hasNextLine()){//запишем строку заста
            comboWords.addItem(scanner.nextLine());
        }
        
        Vector<String> rules = readRuleFile("src\\main\\rule.txt");
        
        Vector<String> colName = new Vector<>();
        colName.add("Replace");
        colName.add("Insert");
        
        tableRule.setShowGrid(true);
        DefaultTableModel model = new MyModel(colName,2);
        tableRule.setModel(model);
        Vector<String> newRow;
        for (int i = 0; i < rules.size(); i++){
            if (0 == i % 2){
                newRow = new Vector<>();
                newRow.add(rules.get(i));
                newRow.add(rules.get(i+1));
                model.addRow(newRow);
            }
        }
        
        execBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    treeList = new ArrayList<>();
                    treeSet = new HashSet<>();
                    activizeMarkov(comboWords.getSelectedItem().toString());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Markov.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        });
        
        clearBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                treeList = new ArrayList<>();
                treeSet = new HashSet<>();
                resultArea.setText("");
            }
            
        });
    }
    
    private void print(String string, int tabIndex){
        resultArea.append("|");
        for (int i = 0; i < tabIndex; i++){
            System.out.print("     ");
            resultArea.append("     ");
        }
        System.out.println("LEVEL " + tabIndex + ' ' + string);
        resultArea.append("LEVEL " + tabIndex + ' ' + string);
    }
    
    private void printWithRule(String str, int tabIndex, int index){
        print(str, tabIndex);
        System.out.println("( " + ruleSet.get(index) + "--->" + ruleSet.get(index+1) + " )");
        resultArea.append("  ( " + ruleSet.get(index) + "--->" + ruleSet.get(index+1) + " )\n");
    }
            
    
    private void activizeMarkov(String str) throws FileNotFoundException{
        resultArea.append("NEW WORD:\n" + str);
        resultArea.append("\n");
        formatString(str);
        uniquePercent = (treeSet.size() / (double)treeList.size())*100;
        resultArea.append("∟-------->   Unique/All = " + String.format("%.2f", uniquePercent) +"%.\n");
    }
    
    private void replaceString(String format, int index) throws FileNotFoundException{
        String helper;
        
        if (format.contains(ruleSet.get(index))){
            helper = format.replace(ruleSet.get(index),ruleSet.get(index+1));
            printWithRule(helper, ++tabIndex, index);
            treeList.add(helper);
            formatString(helper);
            tabIndex--;
        }
        
        if (tabIndex == 0){
            for (String temp: treeList){
                treeSet.add(temp);
            }
            System.out.println("List.length= " + treeList.size());
            System.out.println("Set.length= " + treeSet.size());
        }
        
    }
    
    private void formatString(String format) throws FileNotFoundException{
        
        int index = 0;
        replaceString(format, index);
        
        index = 2;
        replaceString(format, index);
        
        index = 4;
        replaceString(format, index);
        
        index = 6;
        replaceString(format, index);
        
    }
    
    private Vector<String> readRuleFile(String path) throws FileNotFoundException{
        Scanner fileScan = new Scanner(new File(path));
        ruleSet = new Vector<>();
        while(fileScan.hasNext()){
            ruleSet.add(fileScan.nextLine());
        }
        
        for (String str:ruleSet){
            System.out.println(str);
        }
        
        return ruleSet;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        execBut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableRule = new javax.swing.JTable();
        comboWords = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        clearBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("АЛГОРИТМ МАРКОВА");

        execBut.setText("Выполнить");

        resultArea.setColumns(20);
        resultArea.setRows(5);
        resultArea.setToolTipText("Вывод результатов");
        jScrollPane1.setViewportView(resultArea);

        tableRule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null,},
                {null, null,},
                {null, null,}
            },
            new String [] {
                "Replace", "Insert"
            }
        ));
        tableRule.setToolTipText("Набор правил");
        tableRule.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tableRule);

        comboWords.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Выберите слово");

        clearBut.setText("Очистить");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clearBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(execBut))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(comboWords, 0, 132, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboWords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(execBut)
                    .addComponent(clearBut))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Markov().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Markov.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBut;
    private javax.swing.JComboBox<String> comboWords;
    private javax.swing.JButton execBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JTable tableRule;
    // End of variables declaration//GEN-END:variables
}


class MyModel extends DefaultTableModel{
    MyModel(int row, int column){
            super(row,column);
        }
    
    MyModel(Vector columnName, int column){
            super(columnName,column);
            super.setRowCount(0);
        }
}