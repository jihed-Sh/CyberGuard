package org.example.dependency_check;

import java.io.IOException;

public class RunDependencyCheck {

//    public static void main(String[] args) {
//        new RunDependencyCheck("/home/jihed/Downloads/dependency-check/lib/");
//    }
    public RunDependencyCheck(String projectPath) {
        if(projectPath.isEmpty()){
            System.out.println("Invalid Path");
        }
        // Replace "/path/to/your/project" with the actual path you want to scan
//        String projectPath = "/path/to/your/project";

        try {
            // Run the shell script with the project path as an argument
            String s = "/home/jihed/projects/CyberGuard/cyg/src/main/java/org/example/dependency_check/run_dependency_check.sh";
            Process process = new ProcessBuilder(s, projectPath)
                    .inheritIO() // Redirect subprocess's standard error to the console
                    .start();

            int exitCode = process.waitFor();
            System.out.println("Dependency-Check process exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}