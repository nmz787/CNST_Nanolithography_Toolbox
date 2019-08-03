package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example04LabeledArrays {

    public static void main(String[] args) {
        Lib lib = new Lib();
        //
        Struct top = new Struct("top");
        // initialize variables
        double[] diameter = new double[]{1, 2, 3, 4};
        double[] separation = new double[]{0.25, 0.5, 1, 2};
        double arrayExtent = 750;
        double arrayPitch = 2000;
        String s, lbl;
        double pitch;
        int numSides = 44;
        int numElements;
        int gdsLayer = 4;
        double labelOffset = 200;
        double fontSize = 170;
        double shapeReso = 0.1;
        // for loop to create arrays and labels
        for (int i = 0; i < diameter.length; i++) {
            for (int j = 0; j < separation.length; j++) {
                pitch = (diameter[i] + separation[j]);
                s = "circle_d" + (int) diameter[i] + "um_p" + (int) (pitch * 1000) + "nm";
                numElements = (int) (arrayExtent / pitch);
                Scripting.createSqrHoleC(s, top, i * arrayPitch, j * arrayPitch, diameter[i] / 2, diameter[i] / 2,
                        numSides, pitch, pitch, numElements, numElements, 0, gdsLayer);
                lbl = "d=" + (int) diameter[i] + "um; p=" + (int) (pitch * 1000) + "nm";
                top.add(Scripting.createTextGdsC(lbl, "Arial", fontSize, i * arrayPitch,
                        j * arrayPitch - labelOffset - arrayExtent / 2, 0, gdsLayer, shapeReso));
            }
        }
        lib.add(new Ref(top, 0, 0));
        //
        File f = lib.GDSOut("Example04LabeledArrays.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
