//package org.example;
//
//
//
//import java.util.List;
//
//public class WifiScanner {
//
//    public static void main(String[] args) {
//        try {
//            // Get a list of network interfaces
//            List<NetworkInterfaceInfo> interfaces = NetworkInfo.getAllNetworkInterfaces();
//
//            // Iterate through each network interface
//            for (NetworkInterfaceInfo networkInterface : interfaces) {
//                System.out.println("Interface: " + networkInterface.getName());
//
//                // Check if it's a wireless interface
//                if (networkInterface.isWireless()) {
//                    System.out.println("Type: Wireless");
//
//                    // Get WiFi information
//                    WifiInfo wifiInfo = networkInterface.getWifiInfo();
//                    List<WifiInfo.AccessPoint> accessPoints = wifiInfo.getAccessPoints();
//
//                    // Iterate through each access point
//                    for (WifiInfo.AccessPoint accessPoint : accessPoints) {
//                        System.out.println("SSID: " + accessPoint.getSsid());
//                        System.out.println("BSSID: " + accessPoint.getBssid());
//                        System.out.println("Channel: " + accessPoint.getChannel());
//                        System.out.println("Signal Strength: " + accessPoint.getSignalStrength() + " dBm");
//                        System.out.println("Encryption: " + accessPoint.getEncryption());
//                        System.out.println("===============================");
//                    }
//                } else {
//                    System.out.println("Type: Wired");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
