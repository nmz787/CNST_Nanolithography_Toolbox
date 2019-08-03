package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example08PostScriptFracturing implements Example08PSinterface {

    public static void main(String[] args) {
        Lib lib = new Lib();
        // variables
        int gdsLayer = 44;
        double shapeReso = 0.001;
        double pixelValue = .1;
        int fractureSegments = 10;
        //
        Struct ps2gds = new Struct("postScript2GDS");
        Scripting.createPostScript(ps2gds, 0, 0, psString, fractureSegments, 0, shapeReso, pixelValue, gdsLayer);
        //
        lib.add(new Ref(ps2gds, 0, 0));
        File f = lib.GDSOut("Example08PostScriptFracturing.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
