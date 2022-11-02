package hexlet.code;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference."
)
public class Gendiff implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format;

    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", usageHelp = true)
    private boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp = true)
    private boolean versionInfoRequested;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Override
    public Integer call() throws Exception {
        return 0;
    }

}
