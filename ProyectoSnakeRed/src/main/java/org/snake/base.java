/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.snake;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.redesOk.TCPClient50;

public class base extends javax.swing.JFrame {
    public  String SERVERIP;
    public static int SERVERPORT;
    TCPClient50 mTcpClient;
    public boolean Conexion=false;
    Scanner sc;
    public base() {        
        initComponents();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSimbolo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnTop = new javax.swing.JButton();
        btnLeft = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        btnRight = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        DefaultTableModel model = new DefaultTableModel(15, 20) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Deshabilitar la edición de celdas
            }
        };
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                model.setValueAt(" ", row, column);
            }
        }
        posiciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("IP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 58;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 30, 0);
        jPanel2.add(txtIp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 58;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 30, 0);
        jPanel2.add(txtPort, gridBagConstraints);

        jLabel2.setText("Port");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Simbolo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 58;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 30, 0);
        jPanel2.add(txtSimbolo, gridBagConstraints);

        jButton1.setText("Connect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        jPanel2.add(jButton1, gridBagConstraints);

        btnInicio.setText("Iniciar");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jPanel2.add(btnInicio, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha_arriba.png"))); // NOI18N
        btnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnTop, gridBagConstraints);

        btnLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha_izquierda.png"))); // NOI18N
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnLeft, gridBagConstraints);

        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha_abajo.png"))); // NOI18N
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnDown, gridBagConstraints);

        btnRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha_derecha.png"))); // NOI18N
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnRight, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        posiciones.setRowHeight(15);
        posiciones.setModel(model);
        posiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posicionesMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 30, 30);
        jPanel4.add(posiciones, gridBagConstraints);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
         Move(1);
    }//GEN-LAST:event_btnDownActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(Conexion==false){
        SERVERIP=txtIp.getText();
       SERVERPORT = Integer.parseInt(txtPort.getText());
        iniciar();        
       }
        Conexion=true;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void posicionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posicionesMouseClicked
        
    }//GEN-LAST:event_posicionesMouseClicked

    private void btnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopActionPerformed
        //ClienteEnvia("prueba de envio");
        Move(0);
    }//GEN-LAST:event_btnTopActionPerformed

    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
         Move(2);
    }//GEN-LAST:event_btnLeftActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
         Move(3);
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        ClienteEnvia("inicia," + txtSimbolo.getText());
    }//GEN-LAST:event_btnInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(base.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(base.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(base.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(base.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new base().setVisible(true);
                
            }
        });
    }   
    
    void iniciar() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                mTcpClient = new TCPClient50(SERVERIP,
                        new TCPClient50.OnMessageReceived() {
                            @Override
                            public void messageReceived(String message) {     
                                System.out.println("acabo de recibir un mensaje");
                                System.out.println(message);
                                
                                ActualizarTabla(message);
                            }
                        });
                mTcpClient.run();
                
                return null;
            }

            @Override
            protected void done() {
                super.done();
                System.out.println("Cliente bandera 02");
            }
        };

        worker.execute();
    }

    void ClienteRecibe(String llego) {
        
    }

    void ClienteEnvia(String envia) {
        if (mTcpClient != null) {
            mTcpClient.sendMessage(envia);
        }
    }
    
    
    public void Move(int direccion){
        System.out.println("envio: "+direccion);
        if (mTcpClient != null) {
            mTcpClient.sendMessage("movimiento,"+direccion+"-"+txtSimbolo.getText());
        }
    }
    public void VerificaMuerte(String message){
        if(message.equals("muerte")){
            ClienteEnvia("inicia,"+txtSimbolo.getText());
        }
    }
    public void ActualizarTabla(String message) {
    try {
        String[] filas = message.split(";");
        if (filas.length == 0) {
            System.out.println("El mensaje está vacío");
            return; // Salir del método si el mensaje está vacío
        }

        String[][] tabla = new String[15][20];

        int numHilos = Math.min(4, filas.length); // Número máximo de hilos: 4 o la cantidad de filas, lo que sea menor

        // Crear un conjunto de hilos para procesar las filas de la tabla de forma paralela
        Thread[] threads = new Thread[numHilos];
        int filasPorHilo = filas.length / numHilos;
        int filaInicio = 0;
        for (int i = 0; i < numHilos; i++) {
            final int inicio = filaInicio;
            final int fin = (i == numHilos - 1) ? filas.length : filaInicio + filasPorHilo;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int fila = inicio; fila < fin; fila++) {
                        String filaActualString = filas[fila];
                        String[] columnas = filaActualString.split(",");
                        for (int columna = 0; columna < 20; columna++) {
                            tabla[fila][columna] = (columna < columnas.length) ? columnas[columna] : " ";
                        }
                    }
                }
            });
            threads[i].start();
            filaInicio = fin;
        }

        // Esperar a que todos los hilos terminen antes de continuar
        for (int i = 0; i < numHilos; i++) {
            threads[i].join();
        }

        DefaultTableModel model = (DefaultTableModel) posiciones.getModel();
        model.setRowCount(tabla.length);
        model.setColumnCount(tabla[0].length);

        for (int fila = 0; fila < tabla.length; fila++) {
            for (int columna = 0; columna < tabla[0].length; columna++) {
                model.setValueAt(tabla[fila][columna], fila, columna);
            }
        }
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
}
    
    public class TCPClient50 {

        private String servermsj;
        private OnMessageReceived mMessageListener = null;
        private boolean mRun = false;

        PrintWriter out;
        BufferedReader in;

        public TCPClient50(String ip, OnMessageReceived listener) {
            SERVERIP = ip;
            mMessageListener = listener;
        }

        public void sendMessage(String message) {
            if (out != null && !out.checkError()) {
                out.println(message);
                out.flush();
            }
        }

        public void stopClient() {
            mRun = false;
        }

        public void run() {
            mRun = true;
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVERIP);
                System.out.println("TCP Client" + "C: Conectando...");
                Socket socket = new Socket(serverAddr, SERVERPORT);
                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    System.out.println("TCP Client" + "C: Sent.");
                    System.out.println("TCP Client" + "C: Done.");
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (mRun) {
                        servermsj = in.readLine();
                        if (servermsj != null && mMessageListener != null) {
                            mMessageListener.messageReceived(servermsj);
                        }
                        servermsj = null;
                    }
                    
                } catch (Exception e) {
                    System.out.println("TCP" + "S: Error" + e);

                } finally {
                    socket.close();
                }
            } catch (Exception e) {
                System.out.println("TCP" + "C: Error" + e);
            }
        }

        public interface OnMessageReceived {
            public void messageReceived(String message);
        }
    
    
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLeft;
    private javax.swing.JButton btnRight;
    private javax.swing.JButton btnTop;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTable posiciones;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtSimbolo;
    // End of variables declaration//GEN-END:variables
}
