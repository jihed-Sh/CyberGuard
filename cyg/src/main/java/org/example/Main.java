package org.example;

import org.example.scanner.NetworkScanner;

import java.util.Scanner;

import static org.example.utils.ConsolColors.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if(args.length==2){
            if(args[0].equals("scan")){
                String option=args[1];
                switch (option){
                    case "--network":
                        scannerChoice('1',"");
                        break;
                    case "--wifi":
                        scannerChoice('2',"");
                        break;
                    case "--port":
                        String target = printInput(" NET IP ADDRESS (Eg: 192.168.1.1/24) >> ");
                        scannerChoice('3',target);
                        break;
                }
            }
        }
        else if(args.length==1) {
            if(args[0].equals("scan")){
                handleScanning();
            }

        }
        else{
            printDisplay();
            while (true) {
                String userInput = printInput("\n CYG >> ");

                if (userInput.isEmpty()) {
                    printOutput("\n");
                    continue;
                }

                if (userInput.equals("help") || userInput.equals("options") || userInput.equals("commands")) {
                    printOutput(menuDisplay());
                    continue;
                }

                try {
                    int choice = Integer.parseInt(userInput);
                    switch (choice) {
                        case 1:
                            handleScanning();
                            printDisplay();
                            break;
                        case 2:
                        handleReconnaissance();
                            System.out.println("reconnaissance is here");
                            break;
                        case 3:
//                        handleDetection();
                            System.out.println("detection is here");
                            break;
                        case 4:
                            System.out.println("Vulnerability Scanning");
                            break;
                        case 5:
                            exit("\n Till next time!");
                            break;
                        default:
                            break;
                    }
                } catch (NumberFormatException e) {
                    printOutput("\n Invalid Command! Type `help` to see all options");
                }
        }

        }
    }

    private static void printDisplay() {
        printOutput(printBanner());
        printOutput(printDetails());
        printOutput(menuDisplay());
    }

    private static void handleScanning() {
        while (true) {
            printOutput("\n 1. Network Scanner \n\n 2. WiFi Scanner \n\n 3. Port Scanner \n");
            String resp = printInput(" SCAN INPUT >> ");
            String target = "";
            if (resp.equals("3")) {
                target = printInput(" NET IP ADDRESS (Eg: 192.168.1.1/24) >> ");
            }
            scannerChoice(resp.charAt(0), target);
            break;
        }
    }

    private static void handleReconnaissance() {
        while (true) {
            printOutput("\n 1. Choose MAC Address \n\n 2. Input MAC Address\n");
            String resp = printInput(" RECON INPUT >> ");
            String target = "";
            String manualInput = "";

            if (resp.equals("1")) {
                target = printInput(" NET IP ADDRESS (Eg: 192.168.1.1/24) >> ");
            }

            if (resp.equals("2")) {
                manualInput = printInput(" MAC ADDRESS (Eg:08:00:69:02:01:FC) >> ");
            }

            reconChoice(resp, target, manualInput);
            break;
        }
    }

    private static void handleDetection() {
        while (true) {
            printOutput("\n 1. ARP Spoof Attack \n\n 2. SYN Attack\n");
            String resp = printInput(" DETECT INPUT >> ");
            String target = printInput(" NET IP ADDRESS (Eg: 192.168.1.1/24) >> ");
            String tcp = "";

            if (resp.equals("2")) {
                tcp = printInput(" TCP NUMBER (Eg: 80) >> ");
            }

            detectChoice(resp, target, tcp);
            break;
        }
    }

    private static void printOutput(String output) {
        System.out.println(output);
    }

    private static String printInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static void exit(String message) {
        System.out.println(message);
        System.exit(0);
    }

    private static void scannerChoice(char resp, String target) {
        NetworkScanner networkScanner = new NetworkScanner();
        networkScanner.scanner_choice(resp, target);
    }

    private static void reconChoice(String resp, String target, String manualInput) {

        // Implement the logic for reconChoice
        // You may need to create a separate method or class for this logic
    }

    private static void detectChoice(String resp, String target, String tcp) {
        // Implement the logic for detectChoice
        // You may need to create a separate method or class for this logic
    }

    private static String printBanner() {
        return BLUE + "              __              ______                     __\n" + BLUE + "  _______  __/ /_  ___  _____/ ____/_  ______ __________/ /\n" + BLUE + " / ___/ / / / __ \\/ _ \\/ ___/ / __/ / / / __ `/ ___/ __  / \n" + PURPLE + "/ /__/ /_/ / /_/ /  __/ /  / /_/ / /_/ / /_/ / /  / /_/ /  \n" + PURPLE + "\\___/\\__, /_.___/\\___/_/   \\____/\\__,_/\\__,_/_/   \\__/ /   \n" + PURPLE + "    /____/";
    }

    private static String printDetails() {
        return
                PURPLE +
                        """
                                Created by Jihed Ben Zarb & Mohamed Attia V.0.0.1

                                https://github.com/Jihed-sh/cyberguard
                                ----------------------------------------------------------------------------
                                      """;
    }

    private static String menuDisplay() {
        return RESET + """
                ENTER 1 - 4 TO SELECT OPTIONS

                1.  SCANNING                   Scan for IPs, nearby APs, ports, hosts, and more

                2.  RECONNAISSANCE             Gather information about nearby MAC addresses

                3.  DETECTION                  Detect for ARP Spoofing and SYN Flood attacks
                
                4.  VULNERABILITY SCANNING     Automatically scan code for security vulnerabilities

                5.  EXIT                       Exit from Cyberguard to your terminal
                      """;
    }
}