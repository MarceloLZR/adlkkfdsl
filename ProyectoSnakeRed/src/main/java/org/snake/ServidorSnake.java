/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSnake {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444); // Servidor escuchando en el puerto 4444
            Socket clientSocket = serverSocket.accept(); // Acepta la conexión del cliente
            
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            
            // Recibe y procesa el array del cliente
            int[] receivedArray = recibirArray(in);
            
            // Muestra el array en el servidor
            mostrarArray(receivedArray);
            
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int[] recibirArray(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int[] array = (int[]) in.readObject();
        return array;
    }
    
    public static void mostrarArray(int[] array) {
        System.out.print("Array recibido del cliente: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}