package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example02ScriptingMethodAccess {

    public static void main(String[] args) {
        Lib lib = new Lib();
        //
        // create a GDS struct SingleEllipse
        Struct ellipse = new Struct("ellipseSingle");
        // add a GArea of an ellipse from the nanolithography toolbox scripting method
        ellipse.add(Scripting.createEllipse(0, 0, 0.2, 0.5, 44, 50, 4));
        // create a GDS struct to store many instantiated ellipses
        Struct manyEllipses = new Struct("InstantiatedEllipses");
        // instantiate ellipses along a circular path
        double circleRadius = 20; // units in micrometers
        int numberOfInstances = 100;
        double increment = 2 * Math.PI / numberOfInstances;
        for (int i = 0; i < numberOfInstances; i++) {
            manyEllipses.add(new Ref(ellipse, circleRadius * Math.cos(i*increment), circleRadius * Math.sin(i*increment)));
        }
        // adding structures to GDS library
        lib.add(new Ref(ellipse, 0, 0));
        lib.add(new Ref(manyEllipses, 0, 0));
        //
        File f = lib.GDSOut("Example02ScriptingMethodAccess.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }

}
