//package org.example.reconnaissance;
//
//
//import com.google.gson.Gson;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.*;
//import java.util.*;
//
//public class Reconnaissance {
//    private static final int ARP_PACKET_SIZE = 34;  // ARP packet size in bytes
//    private static final int TIMEOUT = 1000;  // Timeout in milliseconds
//
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
//
//    public static void main(String[] args) {
//        String addressIp="192.168.40.82";
//        scanAddresses(addressIp);
//    }
//    public static void chooseMacAddress(String target) {
//        scanAddresses(target);
//    }
//
//    public static void inputMacAddress(String manual_input) {
//        addressApiCall(manual_input, "");
//    }
//
//    private static void scanAddresses(String target) {
//        byte[] broadcastPacket = createPacket(target);
//        List<String> successPackets = transmitPacket(broadcastPacket);
//        List<Entry> entries = parseResponse(successPackets);
//        displayPicker(entries);
//    }
//
//    private static byte[] createPacket(String targetIp) {
//        try {
//
//            InetAddress myInetAddress = InetAddress.getByName(targetIp);
//            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(myInetAddress);
//            byte[] localMac = networkInterface.getHardwareAddress();
//
//            byte[] broadcast = new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255};
//            byte[] header = new byte[]{0, 1, 8, 6, 0, 1, 8, 0, 6, 4, 0, 1};
//
//            byte[] arpRequest = new byte[ARP_PACKET_SIZE];
//            System.arraycopy(header, 0, arpRequest, 0, header.length);
//            System.arraycopy(localMac, 0, arpRequest, header.length, localMac.length);
//            Arrays.fill(arpRequest, header.length + localMac.length, header.length + 2 * localMac.length, (byte) 0);
//            System.arraycopy(myInetAddress.getAddress(), 0, arpRequest, header.length + 2 * localMac.length, 4);
//            System.arraycopy(localMac, 0, arpRequest, header.length + 2 * localMac.length + 4, localMac.length);
//            Arrays.fill(arpRequest, header.length + 2 * localMac.length + 4 + localMac.length, arpRequest.length, (byte) 0);
//
//            return arpRequest;
//        } catch (UnknownHostException | SocketException e) {
//            e.printStackTrace();
//            return new byte[0];
//        }
//    }
//
//    private static List<String> transmitPacket(byte[] packet) {
//        List<String> successList = new ArrayList<>();
//
//        try {
//            DatagramSocket socket = new DatagramSocket();
//            socket.setSoTimeout(TIMEOUT);
//
//            // Broadcast the ARP request packet
//            InetAddress broadcastAddress = InetAddress.getByAddress(new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255});
//            DatagramPacket sendPacket = new DatagramPacket(packet, packet.length, broadcastAddress, 7);
//            socket.send(sendPacket);
//
//            // Receive ARP responses
//            while (true) {
//                byte[] receiveBuffer = new byte[ARP_PACKET_SIZE];
//                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
//
//                try {
//                    socket.receive(receivePacket);
//
//                    // Process the received ARP response packet
//                    String macAddress = parseMacAddress(receiveBuffer);
//                    successList.add(macAddress);
//                } catch (java.net.SocketTimeoutException timeoutException) {
//                    // Timeout reached, stop receiving responses
//                    break;
//                }
//            }
//
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return successList;
//    }
//
//    private static String parseMacAddress(byte[] packet) {
//        // Assuming a common structure of an ARP response packet
//        // You may need to adjust this based on the actual format of your ARP response
//        StringBuilder macAddress = new StringBuilder();
//
//        // Extract the MAC address from the ARP response packet
//        for (int i = 6; i < 12; i++) {
//            macAddress.append(String.format("%02X", packet[i]));
//            if (i < 11) {
//                macAddress.append(":");
//            }
//        }
//
//        return macAddress.toString();
//    }
//
//    private static List<Entry> parseResponse(List<String> successList) {
//        List<Entry> entries = new ArrayList<>();
//
//        for (String macAddress : successList) {
//            // Assuming the MAC address is a string in the successList
//            // You might need to adjust this logic based on the actual format of the ARP response
//            entries.add(new Entry("", macAddress));
//        }
//
//        return entries;
//    }
//
//    private static void displayPicker(List<Entry> elementEntries) {
//        System.out.println("SELECT MAC Address:");
//
//        for (int i = 0; i < elementEntries.size(); i++) {
//            System.out.println((i + 1) + ". " + elementEntries.get(i).getMac());
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        // Get user input
//        System.out.print("Enter the number corresponding to the selected MAC Address: ");
//        int selectedIndex = scanner.nextInt();
//
//        // Validate the selected index
//        if (selectedIndex >= 1 && selectedIndex <= elementEntries.size()) {
//            Entry selectedEntry = elementEntries.get(selectedIndex - 1);
//            addressApiCall(selectedEntry.getMac(), selectedEntry.getIp());
//        } else {
//            System.out.println("Invalid selection. Please enter a valid number.");
//        }
//    }
//
//    private static void addressApiCall(String address, String ipAddress) {
//        System.out.println("\n SCANNING MAC ADDRESS...");
//
//        try {
//            URL url = new URL("https://macvendors.co/api/" + address);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // Process API response
//                transcribeApiResults(response.toString(), ipAddress);
//            } else {
//                System.out.println("\n No MAC Address Found!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void transcribeApiResults(String jsonResponse, String ipAddress) {
//        // Assuming jsonResponse is a JSON string
//        // You may need to adjust this based on the actual API response format
//        // You can use a JSON parsing library like Jackson or Gson for more complex responses
//        Map<String, Object> jsonMap = parseJson(jsonResponse);
//
//        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println("\n " + snakeCaseToNormal(key) + ": " + value);
//        }
//
//        if (ipAddress != null && ipAddress.length() > 0) {
//            System.out.println("\n IP ADDRESS: " + ipAddress);
//        }
//    }
//
//    private static Map parseJson(String jsonString) {
//        Gson gson = new Gson();
//        return gson.fromJson(jsonString, Map.class);
//
//    }
//
//    private static String snakeCaseToNormal(String snakeText) {
//        String[] temp = snakeText.split("_");
//        StringBuilder res = new StringBuilder(temp[0].toUpperCase());
//        for (int i = 1; i < temp.length; i++) {
//            res.append(" ").append(temp[i].substring(0, 1).toUpperCase()).append(temp[i].substring(1));
//        }
//        return res.toString();
//    }
//
//
//    private static class Entry {
//        private String ip;
//        private String mac;
//
//        public Entry(String ip, String mac) {
//            this.ip = ip;
//            this.mac = mac;
//        }
//
//        public String getIp() {
//            return ip;
//        }
//
//        public String getMac() {
//            return mac;
//        }
//    }
//
//
//}
