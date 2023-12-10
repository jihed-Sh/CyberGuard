package org.example;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.example.Main.exit;

public class NetworkScanner {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int wifiScanTimeout = 10;
    private static final Map<String, List<String>> networks = new HashMap<>();
    // Port Scanner Configs
    private static final int scanStart = 1;
    private static final int scanEnd = 1025;
    // WiFi Scanner Configs
    private static String interfaceName = "wlan0";

    public void scanner_choice(char choice, String target) {
        if (choice == '1') {
            network_scanner(target);
        } else if (choice == '2') {
            wifi_scanner();
        } else if (choice == '3') {
            if(target.isEmpty()){
                System.out.println("Needs an IP address");
            }else{
                port_scanner(target);
            }
        } else {
            exit("Invalid option");
        }

    }


    public void network_scanner(String target) {
        IpScanner ipScanner = new IpScanner();
        ConcurrentSkipListSet networkIps = IpScanner.scan("192.168.1.0", 254);
        System.out.println("Devices connected to the network:");
        networkIps.forEach(System.out::println);
    }

    private String getMyIpAddress() {
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
//                    System.out.println(iface.getDisplayName() + " " + ip);
                    return ip;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return "";
    }


///TODO: test with wifi adapter
    public void  wifi_scanner(){
//        initiate_wifi_scan();
    }


    public void  port_scanner(String target){
        PortScanner.scanPorts(target);
    }


}
