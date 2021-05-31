package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.ImageProcessor;
import org.bytedeco.javacpp.opencv_core;
import picocli.CommandLine;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Stream;


@CommandLine.Command(
        name = "convert",
        version = "name version https://gitlab.com/link/",
        description = "Available formats: jpeg png",
        parameterListHeading = "Parameters settings:\n",
        optionListHeading = "Options settings:\n",
        usageHelpAutoWidth = true,
        sortOptions = false,
        separator = " "
)


public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {


    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        CommandLine cmd = new CommandLine(new ResizerApp());
        return cmd.execute(args);
    }

    @Override
    public Integer call() throws Exception {
        ImageProcessor imageProcessor = new ImageProcessor();
        try {
            imageProcessor.processImage(ImageIO.read(inputFile), this);
        } catch (IIOException e) {
            throw new IIOException("Can't read input file!");
        }
        return 0;
    }
}
