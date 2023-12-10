package org.example;

import java.util.Scanner;

public class Main {
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
//                        handleReconnaissance();
                        System.out.println("reconnaissance is here");
                        break;
                    case 3:
//                        handleDetection();
                        System.out.println("detection is here");
                        break;
                    case 4:
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

//            if (resp.equals("1") || resp.equals("3")) {
//                target = printInput(" NET IP ADDRESS (Eg: 192.168.1.1/24) >> ");
//            }

            scannerChoice(target);
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

    private static void exit(String message) {
        System.out.println(message);
        System.exit(0);
    }

    private static void scannerChoice(String target) {
        NetworkScanner networkScanner=new NetworkScanner();
        networkScanner.scanner_choice('1',target);
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
                PURPLE+
                """
                Created by Jihed Ben Zarb & Mohamed Attia V.0.0.1

                https://github.com/Jihed-sh/cyberguard
                ----------------------------------------------------------------------------
                      """;
    }

    private static String menuDisplay() {
        return RESET+"""
                ENTER 1 - 4 TO SELECT OPTIONS

                1.  SCANNING                   Scan for IPs, nearby APs, ports, hosts, and more

                2.  RECONNAISSANCE             Gather information about nearby MAC addresses

                3.  DETECTION                  Detect for ARP Spoofing and SYN Flood attacks

                4.  EXIT                       Exit from Cyberguard to your terminal
                      """;
    }
}