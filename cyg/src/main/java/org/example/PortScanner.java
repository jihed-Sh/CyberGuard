package org.example;

import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScanner {

    public static void scanPorts(String target) {
        int startPort = 1;
        int endPort = 1024; // Specify the range of ports you want to scan
        int portsOpen = 0;
        int timeout = 1000; // Timeout in milliseconds

        System.out.println("Scanning ports on " + target + "...");

        for (int port = startPort; port <= endPort; port++) {
            try {
                // Try to open a socket on the target IP address and port
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(target, port), timeout);

                // If the connection is successful, the port is open
                System.out.println("Port " + port + " is open");
                portsOpen++;

                // Close the socket
                socket.close();
            } catch (Exception e) {
                // If an exception occurs, the port is closed
                // Uncomment the line below if you want to see closed ports
                // System.out.println("Port " + port + " is closed");
            }
        }
        System.out.println("Number of open ports on " + target+"is :"+portsOpen);
    }
}
