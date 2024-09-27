package org.redesOk;


import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Servidor50 {
    String[][] tabla= new String[15][20];
    
   TCPServer50 mTcpServer;
   Scanner sc;
   Random rand= new Random();
   List<Snake> snakes = new ArrayList<Snake>();
   public static void main(String[] args) {          
       Servidor50 objser = new Servidor50();
       objser.IniciaTabla();
       objser.iniciar();
   }
   void iniciar(){
    // Iniciar el servidor TCP en un hilo separado
    new Thread(new Runnable() {
        @Override
        public void run() {
            mTcpServer = new TCPServer50(new TCPServer50.OnMessageReceived() {
                @Override
                public void messageReceived(String message) {
                    System.out.println("hola");
                    ServidorRecibe(message);
                }
            });
            mTcpServer.run(); 
        }
    }).start();

    // Esperar la entrada del usuario en otro hilo
    new Thread(new Runnable() {
        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            String salir = "n";
            System.out.println("Servidor bandera 01");
            while (!salir.equals("s")) {
                
                salir = sc.nextLine();
                ServidorEnvia(salir);
            }
            System.out.println("Servidor bandera 02");
            sc.close(); // Cerrar el Scanner cuando termina
        }
    }).start();
}
   void ServidorRecibe(String llego) {
    System.out.println("Server recibe"+llego);
    String partes[] = llego.split(",");
    String accion = partes[0];
    String simbolo = partes[1]; // aquí va el símbolo que simbolaza los nodos que contiene la snake
    
    // Acción de inicio de una nueva serpiente
    if (accion.equals("inicia")) {
        System.out.println("entro en inicia");
        Point puntoValido = encontrarPosicion();
        System.out.println("busqué un punto válido");
        Snake snake = new Snake(puntoValido.x, puntoValido.y, simbolo);
        System.out.println("creé un snake");
        snakes.add(snake);
        System.out.println("añadí la snake a arraylist");
        AgregarSnakeTabla(snake);
        System.out.println("actualicé la tabla con la snake");
        iniciarMovimientoSnake(snake);
        System.out.println("inició el movimiento");
    }
    
    
    // Acción de movimiento de una serpiente existente
    if (accion.equals("movimiento")) {
        String partes2[] = simbolo.split("-");
        String dir=partes2[0];
        String sim=partes2[1];
        Snake actualSnake = encontrarID(sim);
        if(actualSnake==null){
        System.out.println("no se encontró snake");
        }else{
        System.out.println("condicional de movimiento");
        actualSnake.setNormal(false);
        if (actualSnake != null){
            System.out.println("condicional dentro de movimiento si se encuentra la snake");
            boolean validezGiro = actualSnake.verificarGiro(Integer.parseInt(dir));
            System.out.println("el estado del snake si hace el giro es: "+validezGiro);
            if (validezGiro==true) {
                System.out.println("condicional dentro de movimiento dentro si aS existe y no está muerto");
                actualSnake.move(Integer.parseInt(dir));
                
            } else {
                System.out.println("condicional dentro de movimiento dentro si aS existe y está muerto");
                EliminarSnakeTabla(actualSnake);                
                //ServidorEnvia("muerte-" + simbolo);
            }
        }
        }
    }
}

// Método para iniciar el movimiento de una serpiente en un hilo separado
void iniciarMovimientoSnake(Snake snake) {
    System.out.println("Inicia el movimiento");
    try{
    new Thread(new Runnable() {
        @Override
        public void run() {
            while (snake.isVivo()) {
                
                if(snake.isNormal()){
                    System.out.println("condicional si su movimiento es normal se mueve en dirección recta");
                    snake.move();
                }                                
                try {
                    Thread.sleep(1000); // Esperar 1 segundo entre movimientos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                snake.setNormal(true);
            }                  
            EliminarSnakeTabla(snake);
            snakes.remove(snake);
        }
    }).start();
    }catch(Exception e){
        System.out.println("error en iniciarMoviemientoSnake: "+e);
    }
}
void IniciaTabla(){
    
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if (i == 0 || i == tabla.length - 1 || j == 0 || j == tabla[i].length - 1) {
                    tabla[i][j] = "X";
                } else {
                    tabla[i][j] = " ";
                }
            }
        }
        // Agregar puntos aleatorios
        addRandomPoints(2);
}
private void addRandomPoints(int numPoints) {
        Random rand = new Random();
        int filas = tabla.length;
        int columnas = tabla[0].length;
        int puntosAgregados = 0;

        while (puntosAgregados < numPoints) {
            int randomFila = rand.nextInt(filas - 2) + 1; // Evitar el borde
            int randomColumna = rand.nextInt(columnas - 2) + 1; // Evitar el borde

            // Verificar si la posición está vacía
            if (tabla[randomFila][randomColumna].equals(" ")) {
                // Generar un número aleatorio entre 1 y 3 para el punto
                int punto = rand.nextInt(3) + 1;
                tabla[randomFila][randomColumna] = String.valueOf(punto);
                puntosAgregados++;
            }
        }
    }
void ServidorEnvia(String envia){
        if (mTcpServer != null) {
            mTcpServer.sendMessageTCPServer(envia);
        }
   }
   public Snake encontrarID(String simbolo){
       for (Snake snake : snakes) {
           
           if(snake.getCuerpoSimbolo().equals(simbolo)){
               return snake;
           }    
       }
       return null;
   }
   public void AgregarSnakeTabla(Snake snake){
       Node aux=snake.getHead();
       
       tabla[aux.x][aux.y]="O";      
       
       while(aux.next!=null){
           System.out.println("sigo aquí");
           aux=aux.next;
           tabla[aux.x][aux.y]=snake.getCuerpoSimbolo();
                      
       }
       System.out.println("A punot de enviar");
       ServidorEnvia(tablaToString(tabla));
   }
   public String tablaToString(String[][] tabla) {
    StringBuilder sb = new StringBuilder();
    for (int fila = 0; fila < tabla.length; fila++) {
        for (int columna = 0; columna < tabla[0].length; columna++) {
            sb.append(tabla[fila][columna]);
            if (columna < tabla[0].length - 1) {
                sb.append(",");
            }
        }
        sb.append(";");
    }
    return sb.toString();
}
   public void EliminarSnakeTabla(Snake snake){
       try{
       Node aux=snake.getHead();
       while(aux.next!=null){
           tabla[aux.x][aux.y]=" ";
           aux=aux.next;
       }
       tabla[aux.x][aux.y]=" ";
       //snakes.remove(snake);
       }catch(Exception e){
           System.out.println("error en eliminarSnakeTabla: "+e);
       }
   }
   public Point encontrarPosicion() {
    List<Point> posibilidades = new ArrayList<Point>();
    Point punto = null;
    
    while (punto == null) {
        int x = rand.nextInt(11) + 2;
        int y = rand.nextInt(16) + 2;

        if (tabla[x][y].equals(" ")) {     
            if (x > 0 && tabla[x - 1][y].equals(" ")) {
                Point aux = new Point(x - 1, y);
                posibilidades.add(aux);
            }
            if (x < tabla.length - 1 && tabla[x + 1][y].equals(" ")) {
                Point aux = new Point(x + 1, y);
                posibilidades.add(aux);
            }
            if (y > 0 && tabla[x][y - 1].equals(" ")) {
                Point aux = new Point(x, y - 1);
                posibilidades.add(aux);
            }
            if (y < tabla[0].length - 1 && tabla[x][y + 1].equals(" ")) {
                Point aux = new Point(x, y + 1);
                posibilidades.add(aux);
            }
        }
        if (!posibilidades.isEmpty()) {
            int index = rand.nextInt(posibilidades.size());
            punto = posibilidades.get(index);
        }
    }
    
    return punto;
}

   public class Snake {
    private Node head; // Referencia al nodo de la cabeza de la serpiente
    private int length; // Longitud de la serpiente
    private String cuerpoSimbolo;
    private int direccion;
    private int pendiente=0;
    private boolean estado = true;
    private boolean normal= true;
    
    public Snake(int initialX, int initialY, String simbolo) {
        this.head = new Node(initialX, initialY);
        this.cuerpoSimbolo = simbolo;
        this.length = 1; // Longitud inicial de la serpiente (solo cabeza)
        
        // Verificar si las posiciones adyacentes están disponibles y agregar el cuerpo en la primera posición disponible
        if (initialX - 1 >= 0 && tabla[initialX - 1][initialY].equals(" ")) {
            // Arriba
            addBodySegment(initialX - 1, initialY);
            direccion=0;
        } else if (initialX + 1 < tabla.length && tabla[initialX + 1][initialY].equals(" ")) {
            // Abajo
            addBodySegment(initialX + 1, initialY);
            direccion=1;
        } else if (initialY - 1 >= 0 && tabla[initialX][initialY - 1].equals(" ")) {
            // Izquierda
            addBodySegment(initialX, initialY - 1);
            direccion=2;
        } else if (initialY + 1 < tabla[0].length && tabla[initialX][initialY + 1].equals(" ")) {
            // Derecha
            addBodySegment(initialX, initialY + 1);
            direccion=3;
        }
    }

        public int getPendiente() {
            return pendiente;
        }

        public void setPendiente(int pendiente) {
            this.pendiente = pendiente;
        }
        
        public boolean isEstado() {
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }

        public boolean isNormal() {
            return normal;
        }

        public void setNormal(boolean normal) {
            this.normal = normal;
        }
        
        public boolean isVivo(){
            return estado;
        }
        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getCuerpoSimbolo() {
            return cuerpoSimbolo;
        }

        public void setCuerpoSimbolo(String cuerpoSimbolo) {
            this.cuerpoSimbolo = cuerpoSimbolo;
        }

        public int getDireccion() {
            return direccion;
        }

        public void setDireccion(int direccion) {
            this.direccion = direccion;
        }
        

    // Método para agregar un segmento del cuerpo
    private void addBodySegment(int x, int y) {
        Node newBodySegment = new Node(x, y);
        newBodySegment.next = head;
        head = newBodySegment;
        length++; // Incrementar la longitud de la serpiente
    }

    public void move() {
    try {
        int var1 = 0, var2 = 0;
        switch (direccion) {
            case 0: {
                var1 = -1;
                break;
            }
            case 1: {
                var1 = 1;
                break;
            }
            case 2: {
                var2 = -1;
                break;
            }
            case 3: {
                var2 = 1;
                break;
            }
            default:
                break;
        }
        if(this.isEstado()){
        // Verificar si la nueva posición está dentro de los límites de la matriz
        int newPosX = head.x + var1;
        int newPosY = head.y + var2;
        if (newPosX < 0 || newPosX >= tabla.length || newPosY < 0 || newPosY >= tabla[0].length) {
            this.setEstado(false); // Marcar el estado como false si la nueva posición está fuera de los límites
            EliminarSnakeTabla(this);
            return;
        }

        if(tabla[newPosX][newPosY].equals("X")){
            System.out.println("me choqué con algo una pared");
            
            EliminarSnakeTabla(this);
            this.normal=false;
            return;
        }
        if (tabla[newPosX][newPosY].equals("A")||tabla[newPosX][newPosY].equals("B")||tabla[newPosX][newPosY].equals("O")) {
              System.out.println("me choqué con algo que no se puede comer ");
            
            EliminarSnakeTabla(this);
            this.normal=false;
            return;
        } 
        if(tabla[newPosX][newPosY].equals(" ")) {
            EliminarSnakeTabla(this);
                Node newHead = new Node(newPosX, newPosY);
                newHead.next = head;
                head = newHead;
                if (this.getPendiente() > 0) {
                    this.setPendiente(this.pendiente-1); // Reducir la ganancia pendiente
                } else {
                    // Mover la cola normalmente
                    Node aux = head;
                    while (aux.next.next != null) {
                        aux = aux.next;
                    }
                    aux.next = null;
                }
                this.normal = true;
                this.direccion = direccion;
                AgregarSnakeTabla(this);
            return;
        }
        if(Verificar(tabla[newPosX][newPosY])){
            EliminarSnakeTabla(this);
            int ganancia = Convertir(tabla[newPosX][newPosY]);
            
            this.setPendiente(this.getPendiente()+ganancia); // Aumentar la ganancia pendiente
            this.normal = true;
            this.direccion = direccion;
            tabla[newPosX][newPosY]=" ";
            AgregarSnakeTabla(this);
            addRandomPoints(1);
            return;
        }
        this.setEstado(false);
        EliminarSnakeTabla(this);        
        }
    } catch (Exception e) {
        System.out.println("Error en movimiento sin parametro: " + e);
    }
}

    public void move(int direccion){
        try {
        int var1 = 0, var2 = 0;
        switch (direccion) {
            case 0: {
                var1 = -1;
                break;
            }
            case 1: {
                var1 = 1;
                break;
            }
            case 2: {
                var2 = -1;
                break;
            }
            case 3: {
                var2 = 1;
                break;
            }
            default:
                break;
        }
        if(this.isEstado()){
        // Verificar si la nueva posición está dentro de los límites de la matriz
        int newPosX = head.x + var1;
        int newPosY = head.y + var2;
        if (newPosX < 0 || newPosX >= tabla.length || newPosY < 0 || newPosY >= tabla[0].length) {
            this.setEstado(false); // Marcar el estado como false si la nueva posición está fuera de los límites
            EliminarSnakeTabla(this);
            return;
        }
        if(tabla[newPosX][newPosY].equals("X")){
            System.out.println("me choqué con algo una pared");
            
            EliminarSnakeTabla(this);
            this.normal=false;
            return;
        }
        if (tabla[newPosX][newPosY].equals("A")||tabla[newPosX][newPosY].equals("B")||tabla[newPosX][newPosY].equals("O")) {
              System.out.println("me choqué con algo que no se puede comer ");
            
            EliminarSnakeTabla(this);
            this.normal=false;
            return;
        }
        if(tabla[newPosX][newPosY].equals(" ")) {
            EliminarSnakeTabla(this);
                Node newHead = new Node(newPosX, newPosY);
                newHead.next = head;
                head = newHead;
                if (this.getPendiente() > 0) {
                    this.setPendiente(this.pendiente-1); // Reducir la ganancia pendiente
                } else {
                    // Mover la cola normalmente
                    Node aux = head;
                    while (aux.next.next != null) {
                        aux = aux.next;
                    }
                    aux.next = null;
                }
                this.normal = true;
                this.direccion = direccion;
                AgregarSnakeTabla(this);
            return;
        }
        if(Verificar(tabla[newPosX][newPosY])){
            EliminarSnakeTabla(this);
            int ganancia = Convertir(tabla[newPosX][newPosY]);
            
            this.setPendiente(this.getPendiente()+ganancia); // Aumentar la ganancia pendiente
            this.normal = true;
            this.direccion = direccion;
            tabla[newPosX][newPosY]=" ";
            AgregarSnakeTabla(this);
            addRandomPoints(1);
            return;
        }
        this.setEstado(false);
        EliminarSnakeTabla(this);        
        }
    } catch (Exception e){
            System.out.println("Error en giro con direccion: "+e);
        }
        
    }

    public boolean verificarGiro(int direccion){
    switch(direccion){
        case 0: {
            if(!tabla[head.x - 1][head.y].equals(" ")) {
                return false; // Si la posición está ocupada, el movimiento no es válido
            }
            return true;
        }
        case 1: {
            if(!tabla[head.x + 1][head.y].equals(" ")) {
                return false; // Si la posición está ocupada, el movimiento no es válido
            }
            return true;
        }
        // Agrega los casos restantes para las direcciones
        case 2: {
            if(!tabla[head.x][head.y - 1].equals(" ")) {
                return false; // Si la posición está ocupada, el movimiento no es válido
            }
            return true;
        }
        case 3: {
            if(!tabla[head.x][head.y + 1].equals(" ")) {
                return false; // Si la posición está ocupada, el movimiento no es válido
            }
            return true;
            
        }
        default:
            break;
    }
    return true; // Movimiento válido
}    
    }
    class Comida{
        String sim;
        int puntaje;
        public Comida(){
            int a= rand.nextInt(3)+1;
            this.puntaje=a;
            this.sim=String.valueOf(a);
        }
        
    }
    public int Convertir(String comida){
        return Integer.parseInt(comida);
    }
    public boolean Verificar(String a){
        int array[]={1,2,3};
       
        for (int i = 0; i < array.length; i++) {
            if(Integer.parseInt(a)==array[i]){
                return true;
            }
            
        }
        return false;        
    }
    class Node {
        int x;
        int y;
        Node next;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.next = null;
        }
    }
    public class Point{
        public int x,y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        
    }
}
