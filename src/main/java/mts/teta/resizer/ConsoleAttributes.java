package mts.teta.resizer;


import picocli.CommandLine;
import java.io.File;
public class ConsoleAttributes {


    private Integer[] resize = new Integer[2];
    @CommandLine.Option(
            names = "--resize",
            paramLabel = "width height",
            description = "resize the image",
            arity = "2..2",
            type=Integer.class,
            split = " "
    )
    public void setResize(Integer[] val) {
        this.resize = val;
    }


    private int quality;
    @CommandLine.Option(
            names = "--quality",
            paramLabel = "value",
            type=Integer.class,
            description = "JPEG/PNG compression level"
    )
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    private Integer[] crop = new Integer[4];
    @CommandLine.Option(
            names = "--crop",
            paramLabel = "width height x y",
            description = "cut out one rectangular area of the image",
            arity = "4..4",
            type=Integer.class,
            split = " "
    )
    public void setCrop(Integer[] val) {
        this.crop = val;
    }


    private int blur;
    @CommandLine.Option(
            names = "--blur",
            paramLabel = "radius",
            type=Integer.class,
            description = "reduce image noise detail levels"
    )
    public void setBlur(Integer blur) {
        this.blur = blur;
    }



    private String format;
    @CommandLine.Option(
            names = "--format",
            paramLabel = "\"outputFormat\"",
            type=String.class,
            description = "the image format type"
    )
    public void setFormat(String format) {
        this.format = format;
    }


    File inputFile;
    @CommandLine.Parameters(
            index = "0",
            paramLabel = "input-file",
            description = "input file"
    )
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    private File outputFile;
    @CommandLine.Parameters(
            index = "1",
            paramLabel = "output-file",
            description = "create output file"
    )
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }


    public Integer[] getResize() {
        return resize;
    }

    public Integer getResizeWidth() {
        return resize[0];
    }

    public void setResizeWidth(Integer width) {
        resize[0] = width;
    }

    public Integer getResizeHeight() {
        return resize[1];
    }

    public void setResizeHeight(Integer height) {
        resize[1] = height;
    }

    public Integer getQuality() {
        return quality;
    }

    public Integer[] getCrop() {
        return crop;
    }

    public Integer getCropWidth() {
        return crop[0];
    }

    public void setCropWidth(Integer cropWidth) {
        crop[0] = cropWidth;
    }

    public Integer getCropHeight() {
        return crop[1];
    }

    public void setCropHeight(Integer cropHeight) {
        crop[1] = cropHeight;
    }

    public Integer getCropX() {
        return crop[2];
    }

    public void setCropX(Integer cropX) {
        crop[2] = cropX;
    }

    public Integer getCropY() {
        return crop[3];
    }

    public void setCropY(Integer cropY) {
        crop[3] = cropY;
    }

    public Integer getBlur() {
        return blur;
    }


    public String getFormat() {
        return format;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}