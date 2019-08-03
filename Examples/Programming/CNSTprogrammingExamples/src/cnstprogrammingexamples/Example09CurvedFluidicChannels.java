package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.awt.geom.AffineTransform;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example09CurvedFluidicChannels {
    public static void main(String[] args) {
        Lib lib = new Lib();
        //
        int layerChannel = 4, layerChannel2 = 7, numElectrodes = 8;
        double shapeReso = 0.1, fontSize = 250;
        double chipSize = 10000, electrodeRadius = 250, electrodeSpacing = 100, channelWidth = 20;
        GArea electrode = Scripting.createEllipse(0, 0, electrodeRadius, electrodeRadius, 100, 0, layerChannel);
        Struct chip = new Struct("chip");
        AffineTransform at = new AffineTransform(new double[]{-1.0, 0.0, 0.0, 1.0});
        // side channels 1-8
        for (int i = 0; i < numElectrodes; i++) {
            GArea channels = new GArea(electrode);
            GArea label = Scripting.createTextGdsC("" + (i + 1), "Serif", fontSize, -electrodeRadius - fontSize / 2,
                    0, 0, layerChannel, shapeReso);
            double x = 2 * electrodeRadius;
            double y = chipSize / 4 + i * (2 * electrodeRadius + electrodeSpacing);
            double x2 = x + 200 - i * channelWidth * 2;
            double x3 = 200, y3 = chipSize - 1500 - (numElectrodes - i - 1) * 40 - y;
            channels.or(Scripting.createWaveGuide(0, 0, x2, 0, channelWidth, 0, 0, 0, layerChannel));
            channels.or(Scripting.create90degreeBendLH(x2, 0, x3, 200, channelWidth, 0, layerChannel, shapeReso));
            channels.or(Scripting.createWaveGuide(x2 + x3, 200, x2 + x3, y3, channelWidth, 0, 0, 0, layerChannel));
            double radiusChannel = 1000;
            double x4 = x2 + x3 + radiusChannel;
            channels.or(Scripting.createTorusW(x4, y3, radiusChannel, channelWidth, 90, 180, 100, 0, layerChannel));
            double y4 = y3 + radiusChannel;
            double x5 = chipSize / 2 - 2000 - (numElectrodes - i - 1) * 40;
            channels.or(Scripting.createWaveGuide(x4, y4, x5, y4, channelWidth, 0, 0, 0, layerChannel));
            radiusChannel = 500;
            double y5 = y4 - radiusChannel;
            channels.or(Scripting.createTorusW(x5, y5, radiusChannel, channelWidth, 0, 90, 100, 0, layerChannel));
            double x6 = x5 + radiusChannel;
            double y6 = -y + 2500 - (numElectrodes - i - 1) * 100;
            channels.or(Scripting.createWaveGuide(x6, y5, x6, y6, channelWidth, 0, 0, 0, layerChannel));
            radiusChannel = 500;
            double x7 = x6 + radiusChannel;
            channels.or(Scripting.createTorusW(x7, y6, radiusChannel, channelWidth, 180, 270, 100, 0, layerChannel));
            double y7 = y6 - radiusChannel;
            double x8 = chipSize / 2 - x;
            channels.or(Scripting.createWaveGuide(x7, y7, x8, y7, channelWidth, 0, 0, 0, layerChannel));
            channels.transform(AffineTransform.getTranslateInstance(-chipSize / 2 + x, -chipSize / 2 + y));
            GArea channelsMirror = new GArea(channels);
            channelsMirror.transform(at); // mirror around y-axis
            chip.add(channels);
            chip.add(channelsMirror);
            chip.add(label.transform(AffineTransform.getTranslateInstance(-chipSize / 2 + x, -chipSize / 2 + y)));
            GArea temp = new GArea(label);
            chip.add(temp.transform(AffineTransform.getTranslateInstance(chipSize - x / 2, 0)));
        }
        // bottom channels
        electrodeSpacing = 200;
        channelWidth = 50;
        double offsetX = 400;
        for (int i = 0; i < numElectrodes / 2; i++) {
            GArea channels = new GArea(electrode);
            double x = (i + 1) * (2 * electrodeRadius + electrodeSpacing) + offsetX;
            double y = 2.5 * electrodeRadius;
            double x2 = x - i * channelWidth * 20;
            double y2 = 0.55 * chipSize - y - i * channelWidth * 4;
            channels.or(Scripting.createBezierCurve(0, electrodeRadius / 2, 0, chipSize / 4,
                    x2, chipSize / 8, x2, y2, channelWidth, shapeReso, 0, layerChannel2));
            double radiusChannel = 400;
            double x3 = x2 + radiusChannel;
            channels.or(Scripting.createTorusW(x3, y2, radiusChannel, channelWidth, 90, 180, 100, 0, layerChannel2));
            double y3 = y2 + radiusChannel;
            double x4 = chipSize / 2 - x;
            channels.or(Scripting.createWaveGuide(x3, y3, x4, y3, channelWidth, 0, 0, 0, layerChannel));
            channels.transform(AffineTransform.getTranslateInstance(-chipSize / 2 + x, -chipSize / 2 + y));
            channels.setLayer(layerChannel2);
            GArea channelsMirror = new GArea(channels);
            channelsMirror.transform(at); // mirror around y-axis
            chip.add(channels);
            chip.add(channelsMirror);
        }
        // Labels for the bottom electrodes
        double x = -chipSize / 2 + (2 * electrodeRadius + electrodeSpacing) + offsetX - fontSize / 4;
        double y = -chipSize / 2 + fontSize / 2;
        double pitch = (2 * electrodeRadius + electrodeSpacing);
        Scripting.createLabelMakerAutoOutLett(0, 4, "Serif", fontSize, x, y, 0, 0, pitch, 1, chip,
                layerChannel2, shapeReso); // label A B C D
        x = Math.abs(x)-fontSize/2;
        Scripting.createLabelMakerAutoOutLett(0, 4, "Serif", fontSize, x, y, 0, 0, -pitch, 1, chip,
                layerChannel2, shapeReso); // label D C B A
//        // chip dimensions - GDS layer 11
//        chip.add(new GArea(new Rect(-chipSize / 2, -chipSize / 2, chipSize / 2, chipSize / 2, 11), 11));
        //
        lib.add(new Ref(chip, 0, 0));
        File f = lib.GDSOut("fluidics.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
