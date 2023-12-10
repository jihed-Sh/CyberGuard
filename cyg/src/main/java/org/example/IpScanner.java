package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IpScanner {


    /**
     * @param firstIpInTheNetwork e.g: 192.168.1.0
     * @param numOfIps            e.g: 254
     * @return
     */
    public static ConcurrentSkipListSet scan(String firstIpInTheNetwork, int numOfIps) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final String networkId = firstIpInTheNetwork.substring(0, firstIpInTheNetwork.length() - 1);
        ConcurrentSkipListSet ipsSet = new ConcurrentSkipListSet();

        AtomicInteger ips = new AtomicInteger(0);
        while (ips.get() <= numOfIps) {
            String ip = networkId + ips.getAndIncrement();
            executorService.submit(() -> {
                try {
                    InetAddress inAddress = InetAddress.getByName(ip);
                    if (inAddress.isReachable(500)) {
                        ipsSet.add(ip);
                    }
                } catch (IOException e) {

                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return ipsSet;
    }
//    public static Set<String> getConnectedIPs(String ipAddress) {
//        Set<String> connectedIPs = new HashSet<>();
//        try {
//            Process process = Runtime.getRuntime().exec("arp -a " + ipAddress);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Parse the ARP table output to extract connected IP addresses
//                String[] tokens = line.split("\\s+");
//                if (tokens.length >= 2) {
//                    String connectedIP = tokens[0];
//                    connectedIPs.add(connectedIP);
//                }
//            }
//
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return connectedIPs;
//    }
}