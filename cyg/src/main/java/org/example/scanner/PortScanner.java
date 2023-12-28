package org.example.scanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {

    public static void scanPorts(String target) {
        target = "127.0.0.1";
        int timeOut = 2500;
        ConcurrentLinkedQueue openPorts = new ConcurrentLinkedQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Map<Integer, String> ports = new HashMap<>();
        fillMap(ports);
        for (Map.Entry<Integer, String> port : ports.entrySet()) {
            final int currentPort = port.getKey();
            String finalTarget = target;
            executorService.submit(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(finalTarget, currentPort), timeOut);
                    socket.close();
                    openPorts.add(currentPort);
                    System.out.println(finalTarget + " ,port open: " + currentPort);
                } catch (IOException e) {

                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List openPortList = new ArrayList<>();
        System.out.println("openPortsQueue: " + openPorts.size());
        while (!openPorts.isEmpty()) {
            openPortList.add(openPorts.poll());
        }
        openPortList.forEach(p -> System.out.println("port " + p + " is open"));
    }

    private static void fillMap(Map<Integer, String> ports) {
        ports.put(80, "http");
        ports.put(23, "telnet");
        ports.put(443, "https");
        ports.put(21, "ftp");
        ports.put(22, "ssh");
        ports.put(25, "smtp");
        ports.put(3389, "ms-wbt-server");
        ports.put(110, "pop3");
        ports.put(445, "microsoft-ds");
        ports.put(139, "netbios-ssn");
        ports.put(143, "imap");
        ports.put(53, "domain");
        ports.put(135, "msrpc");
        ports.put(3306, "mysql");
        ports.put(8080, "http-proxy");
        ports.put(1723, "pptp");
        ports.put(111, "rpcbind");
        ports.put(995, "pop3s");
        ports.put(993, "imaps");
        ports.put(5900, "vnc");
        ports.put(1025, "NFS-or-IIS");
        ports.put(587, "submission");
        ports.put(8888, "sun-answerbook");
        ports.put(199, "smux");
        ports.put(1720, "h323q931");
        ports.put(465, "smtps");
        ports.put(548, "afp");
        ports.put(113, "ident");
        ports.put(81, "hosts2-ns");
        ports.put(6001, "X11:1");
        ports.put(10000, "snet-sensor-mgmt");
        ports.put(514, "shell");
        ports.put(5060, "sip");
        ports.put(179, "bgp");
        ports.put(1026, "LSA-or-nterm");
        ports.put(2000, "cisco-sccp");
        ports.put(8443, "https-alt");
        ports.put(8000, "http-alt");
        ports.put(32768, "filenet-tms");
        ports.put(554, "rtsp");
        ports.put(26, "rsftp");
        ports.put(1433, "ms-sql-s");
        ports.put(49152, "unknown");
        ports.put(2001, "dc");
        ports.put(515, "printer");
        ports.put(8008, "http");
        ports.put(49154, "unknown");
        ports.put(1027, "IIS");
        ports.put(5666, "nrpe");
        ports.put(646, "ldp");
        ports.put(5000, "upnp");
        ports.put(5631, "pcanywheredata");
        ports.put(631, "ipp");
        ports.put(49153, "unknown");
        ports.put(8081, "blackice-icecap");
        ports.put(2049, "nfs");
        ports.put(88, "kerberos-sec");
        ports.put(79, "finger");
        ports.put(5800, "vnc-http");
        ports.put(106, "pop3pw");
        ports.put(2121, "ccproxy-ftp");
        ports.put(1110, "nfsd-status");
        ports.put(49155, "unknown");
        ports.put(6000, "X11");
        ports.put(513, "login");
        ports.put(990, "ftps");
        ports.put(5357, "wsdapi");
        ports.put(427, "svrloc");
        ports.put(49156, "unknown");
        ports.put(543, "klogin");
        ports.put(544, "kshell");
        ports.put(5101, "admdog");
        ports.put(144, "news");
        ports.put(7, "echo");
        ports.put(389, "ldap");
        ports.put(8009, "ajp13");
        ports.put(3128, "squid-http");
        ports.put(444, "snpp");
        ports.put(9999, "abyss");
        ports.put(5009, "airport-admin");
        ports.put(7070, "realserver");
        ports.put(5190, "aol");
        ports.put(3000, "ppp");
        ports.put(5432, "postgresql");
        ports.put(1900, "upnp");
        ports.put(3986, "mapper-ws_ethd");
        ports.put(13, "daytime");
        ports.put(1029, "ms-lsa");
        ports.put(9, "discard");
        ports.put(5051, "ida-agent");
        ports.put(6646, "unknown");
        ports.put(49157, "unknown");
        ports.put(1028, "unknown");
        ports.put(873, "rsync");
        ports.put(1755, "wms");
        ports.put(2717, "pn-requester");
        ports.put(4899, "radmin");
        ports.put(9100, "jetdirect");
        ports.put(119, "nntp");
        ports.put(37, "time");

    }
}
