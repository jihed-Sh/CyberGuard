//package org.example.reconnaissance;
//
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.*;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class Reconnaissance {
//    public static void reconChoice(String choice, String target, String manualInput) {
//        if ("1".equals(choice)) {
//            chooseMacAddress(target);
//        } else if ("2".equals(choice)) {
//            inputMacAddress(manualInput);
//        } else {
//            System.exit(0);
//        }
//    }
//
//    public static void chooseMacAddress(String target) {
//        scanAddresses(target);
//    }
//
//    public static void inputMacAddress(String manualInput) {
//        addressApiCall(manualInput, "");
//    }
//
//    // Helper Functions
//
//    public static void addressApiCall(String address, String ipAddress) {
//        System.out.println("\nSCANNING MAC ADDRESS...");
//        String apiUrl = "https://macvendors.co/api/" + address;
//
//        try {
//            URL url = new URL(apiUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Set the request method
//            connection.setRequestMethod("GET");
//
//            // Get the response
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder content = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    content.append(inputLine);
//                }
//                in.close();
//
//                // Parse JSON response
//                String jsonResponse = content.toString();
//                parseApiResults(jsonResponse, ipAddress);
//            } else {
//                System.out.println("\nNo MAC Address Found!");
//            }
//
//            connection.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("\nERROR: Something Went Wrong");
//        }
//    }
//
//    public static void parseApiResults(String jsonResponse, String ipAddress) {
//        // Parse the JSON response here and print the results
//        // You might want to use a JSON parsing library like Gson for more complex JSON parsing
//
//        System.out.println("\nResults from API:");
//        System.out.println(jsonResponse);
//
//        if (ipAddress != null && !ipAddress.isEmpty()) {
//            System.out.println("\nIP ADDRESS: " + ipAddress);
//        }
//    }
//    public static void scanAddresses(String target) {
//        List<Map<String, String>> entries = createPacketAndTransmit(target);
//        displayPicker(entries);
//    }
//
//    public static List<Map<String, String>> createPacketAndTransmit(String ip) {
//        List<Map<String, String>> successList = transmitPacket(createPacket(ip));
//        return successList;
//    }
//
//    public static byte[] createPacket(String ip) {
//        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
//
//        if (devices.length == 0) {
//            System.out.println("No network interface found");
//            return null;
//        }
//
//        NetworkInterface device = devices[0]; // Choose a network interface, you may need to select the appropriate one
//
//        // Create an ARP request packet
//        ARPPacket arpRequest = new ARPPacket();
//        arpRequest.hardtype = ARPPacket.HARDTYPE_ETHER;
//        arpRequest.prototype = ARPPacket.PROTOTYPE_IP;
//        arpRequest.operation = ARPPacket.ARP_REQUEST;
//
//        // Set the sender's MAC and IP address
//        arpRequest.sender_hardaddr = device.mac_address;
//        arpRequest.sender_protoaddr = device.addresses[0].address;
//
//        // Set the target's MAC and IP address
//        arpRequest.target_hardaddr = new byte[]{0, 0, 0, 0, 0, 0}; // Placeholder, set the appropriate MAC
//        arpRequest.target_protoaddr = IPAddress.valueOf(ip).getAddress();
//
//        // Create an Ethernet frame
//        EthernetPacket ether = new EthernetPacket();
//        ether.frametype = EthernetPacket.ETHERTYPE_ARP;
//        ether.src_mac = device.mac_address;
//        ether.dst_mac = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
//
//        // Combine ARP request with Ethernet frame
//        ether.datalink = arpRequest;
//
//        return ether.getPacketData();
//        // Create an ARP request packet
//        // This might require additional dependencies or custom classes for packet creation
//        // Adjust this part based on the library or method you choose for creating packets
//        byte[] arpRequestPacket = createArpRequestPacket(ip);
//        return arpRequestPacket;
//    }
//
//    // This method needs to be implemented based on your choice of packet creation library or method
//    // You may use a dedicated library for creating ARP packets in Java
//    private static byte[] createArpRequestPacket(String ip) {
//        // Implement ARP request packet creation here
//        // Return the byte representation of the packet
//        // You may use a library or construct the packet manually
//        return new byte[0];
//    }
//
//    public static List<Map<String, String>> transmitPacket(byte[] packet) {
//        // Implement packet transmission logic here
//        // Adjust this part based on the library or method you choose for packet transmission
//        List<Map<String, String>> successList = sendPacketAndGetResponse(packet);
//        return successList;
//    }
//
//    // This method needs to be implemented based on your choice of packet transmission library or method
//    private static List<Map<String, String>> sendPacketAndGetResponse(byte[] packet) {
//        // Implement packet transmission and reception logic here
//        // Return a list of successful responses
//        return List.of();  // Placeholder, replace with actual logic
//    }
//
//    public static void displayPicker(List<Map<String, String>> elementEntries) {
//        List<String> macList = extractMacAddresses(elementEntries);
//        String selectedMac = displayPickerAndGetSelection(macList);
//        addressApiCall(selectedMac, elementEntries);
//    }
//
//    private static List<String> extractMacAddresses(List<Map<String, String>> elementEntries) {
//        // Extract MAC addresses from the entries
//        // Adjust this part based on the structure of your entries
//        return elementEntries.stream()
//                .map(entry -> entry.get("mac"))
//                .collect(Collectors.toList());
//    }
//
//    private static String displayPickerAndGetSelection(List<String> macList) {
//        // Implement the picker display logic here
//        // Return the selected MAC address
//        // You may use a GUI library or console-based input
//        // This part depends on your specific requirements and the available libraries
//        // Replace this with the actual logic
//        return macList.get(0);
//    }
//
//    private static void addressApiCall(String selectedMac, List<Map<String, String>> elementEntries) {
//        // Implement the API call using the selected MAC address
//        // You can use the selected MAC address to fetch additional information
//        // Make an HTTP request or use any appropriate method
//        // Adjust this part based on your requirements and available libraries
//        System.out.println("Selected MAC Address: " + selectedMac);
//        // Replace this with the actual API call logic
//    }
//}
