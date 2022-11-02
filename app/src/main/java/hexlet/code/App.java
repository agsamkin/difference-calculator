package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Gendiff()).execute(args);
        System.exit(exitCode);
    }
}