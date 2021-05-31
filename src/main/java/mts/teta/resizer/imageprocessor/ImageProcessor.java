package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.Resizers;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




public class ImageProcessor {
    Map<String,Boolean> flow = new HashMap<>();
    private float quality;
    private void resizeWrapper(MarvinImage image, ResizerApp app){
        MarvinPluginCollection.scale(image.clone(), image, app.getResizeWidth(), app.getResizeHeight());
    }

    private void cropWrapper(MarvinImage image,ResizerApp app)  {
        MarvinPluginCollection.crop(image.clone(), image, app.getCropX(), app.getCropY(), app.getCropWidth(), app.getCropHeight());
    }
    private void blurWrapper (MarvinImage image,ResizerApp app){
        MarvinPluginCollection.gaussianBlur(image.clone(), image, app.getBlur());
    }

    private void writeOut(BufferedImage image,ResizerApp app) throws IOException {
        if (!flow.get("format")) {
            Thumbnails.of(image).resizer(Resizers.NULL).scale(1).outputQuality(quality).toFile(app.getOutputFile());
        } else {
            Thumbnails.of(image).resizer(Resizers.NULL).scale(1).outputQuality(quality).outputFormat(app.getFormat()).toFile(app.getOutputFile());
        }
    }
    private void check(ResizerApp app) throws BadAttributesException {
            flow.put("resize",app.getResizeHeight() != null);
            flow.put("crop",app.getCropY() != null);
            flow.put("blur",app.getBlur() != 0);
            flow.put("quality",app.getQuality()!= 0);
            flow.put("format",app.getFormat() != null);

        if (flow.get("resize") & flow.get("crop")){
            throw new BadAttributesException("Composition of crop and resize is not specified");
        }
        if (flow.get("resize") && (app.getResizeWidth() < 1 || app.getResizeHeight() < 1)){
            throw new BadAttributesException("Please check params!");
        }
        if (flow.get("quality") && (app.getQuality() < 1 || app.getQuality() > 100)){
            throw new BadAttributesException("Please check params!");
        }
        if (flow.get("crop") && (app.getCropWidth() < 1 || app.getCropHeight() < 1 || app.getCropX() < 1 || app.getCropY() < 1)){
            throw new BadAttributesException("Please check params!");
        }
        if (flow.get("blur") && app.getBlur()<0){
            throw new BadAttributesException("Please check params!");
        }
    }

    public void processImage (BufferedImage image, ResizerApp app) throws BadAttributesException, IOException {
        check(app);
        MarvinImage marvinImage = new MarvinImage(image);
        if (flow.get("resize")){
            resizeWrapper(marvinImage,app);
        }else if (flow.get("crop")){
            cropWrapper(marvinImage,app);
        }
        if (flow.get("blur")){
            blurWrapper(marvinImage,app);
        }
        if (flow.get("quality")){
            quality = app.getQuality()/100f;
        }else {
            quality = 1f;
        }
        writeOut(marvinImage.getBufferedImageNoAlpha(),app);
    }
}