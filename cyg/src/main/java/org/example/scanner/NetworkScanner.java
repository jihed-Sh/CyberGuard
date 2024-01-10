package org.example.scanner;

import java.net.*;
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
        }
//        else if (choice == '2') {
//            wifi_scanner();
//        }
        else if (choice == '2') {
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
        String myIpAddress=getMyIpAddress();
        ConcurrentSkipListSet networkIps = IpScanner.scan(myIpAddress, 254);
        System.out.println("Devices connected to the network:");
        networkIps.forEach(System.out::println);
    }

    private String getMyIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while( networkInterfaceEnumeration.hasMoreElements()){
                for ( InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses())
                    if ( interfaceAddress.getAddress().isSiteLocalAddress())
                        return interfaceAddress.getAddress().getHostAddress();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return "";
    }


///TODO: test with wifi adapter
    public void  wifi_scanner(){
//        initiate_wifi_scan();
    }


    public void  port_scanner(String target){
//        PortScanner.scanPorts(getMyIpAddress());
        PortScanner.scanPorts(target);
    }


}
