package net.kikuchy.genum.cli;

import net.kikuchy.genum.Genum;
import net.kikuchy.genum.loader.LocalYamlLoader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.zip.DataFormatException;

/**
 * Created by kikuchy on 2015/11/03.
 */
public class Main {
    public static void main(String[] args) {
        CommandLineOption option = new CommandLineOption();
        CmdLineParser parser = new CmdLineParser(option);
        try {
            parser.parseArgument(args);
            generate(option);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public static void generate(CommandLineOption option) {
        LocalYamlLoader localYamlLoader = new LocalYamlLoader();
        Genum genum = new Genum.Builder(option.packageClass, option.className, localYamlLoader)
                .build();

        InputStream yamlData;
        OutputStream sourceCode;
        try {
            if (option.yamlFile == null) {
                yamlData = System.in;
            } else {
                yamlData = new FileInputStream(option.yamlFile);
            }
            sourceCode = new FileOutputStream(touchDistFile(option));
            genum.generate(yamlData, sourceCode);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (DataFormatException e) {
            System.err.println("Source data format is not valid YAML!");
            System.err.println(e.getMessage());
        }
    }

    private static File touchDistFile(CommandLineOption option) {
        StringBuilder sb = new StringBuilder(option.distDir.getAbsolutePath());
        sb.append(File.separator);
        sb.append(option.packageClass.replaceAll("\\.", File.separator));
        String packageContainingPath = sb.toString();
        File packageContainingDistDir = new File(packageContainingPath);
        if (!packageContainingDistDir.exists()) {
            packageContainingDistDir.mkdirs();
        }
        sb.append(File.separator);
        sb.append(option.className);
        sb.append(".java");
        return new File(sb.toString());
    }

    static class CommandLineOption {
        @Option(name = "-p", aliases = "--package-name", usage = "Package name that used for 'package' statement.", required = true)
        public String packageClass;
        @Option(name = "-c", aliases = "--class-name", usage = "Class name of Enum class.", required = true)
        public String className;
        @Option(name = "-s", aliases = "--source", usage = "Source YAML file. If omit this option, genum-cli try to use standard input.")
        public File yamlFile;
        @Option(name = "-o", aliases = "--output", usage = "Destination directory. Package directory will make automatically.", required = true)
        public File distDir;
    }
}
