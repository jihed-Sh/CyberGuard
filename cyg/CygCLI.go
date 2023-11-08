package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	fmt.Print(printBanner())
	fmt.Print(printDetails())
	fmt.Print(menuDisplay())

	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("Enter your choice (1-4 or 'exit'): ")
		scanner.Scan()
		input := scanner.Text()

		switch strings.ToLower(input) {
		case "1":
			// Option 1: SCANNING - Implement your scanning logic here
			fmt.Println("Option 1: SCANNING")
		case "2":
			// Option 2: RECONNAISSANCE - Implement your reconnaissance logic here
			fmt.Println("Option 2: RECONNAISSANCE")
		case "3":
			// Option 3: DETECTION - Implement your detection logic here
			fmt.Println("Option 3: DETECTION")
		case "4":
			// Option 4: EXIT
			fmt.Println("Exiting the CLI.")
			os.Exit(0)
		case "exit":
			// User wants to exit
			fmt.Println("Exiting the CLI.")
			os.Exit(0)
		default:
			fmt.Println("Invalid choice. Please select a valid option (1-4 or 'exit').")
		}
	}
}

func printBanner() string {
	blue := "\033[34m"
	violet := "\033[35m"
	//resetColor := "\033[0m"
	//red := "\033[31m"
	return blue +
		"              __              ______                     __\n" +
		blue + "  _______  __/ /_  ___  _____/ ____/_  ______ __________/ /\n" +
		blue + " / ___/ / / / __ \\/ _ \\/ ___/ / __/ / / / __ `/ ___/ __  / \n" +
		violet + "/ /__/ /_/ / /_/ /  __/ /  / /_/ / /_/ / /_/ / /  / /_/ /  \n" +
		violet + "\\___/\\__, /_.___/\\___/_/   \\____/\\__,_/\\__,_/_/   \\__/ /   \n" +
		violet + "    /____/"
}

func printDetails() string {
	//green := "\033[32m"
	blue := "\033[34m"

	return (blue + `
                Created by Jihed Ben Zarb and Mohamed Attia  V.0.0.1

                https://github.com/Jihed-Sh/cyberguard
                ----------------------------------------------------------------------------
                      `)
}

func menuDisplay() string {
	resetColor := "\033[0m"

	return (resetColor + `
                ENTER 1 - 4 TO SELECT OPTIONS

                1.  SCANNING                   Scan for IPs, nearby APs, ports, hosts, and more

                2.  RECONNAISSANCE             Gather  information  about nearby MAC addresses

                3.  DETECTION                  Detect for ARP Spoofing and SYN Flood attacks

                4.  EXIT                       Exit from netspionage to your terminal
                      `)
}
