package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example06CustomClassMethods {

    public static void main(String[] args) {
        Lib lib = new Lib();
        // define variables
        double x = 0, y = 0;
        double r1 = 20, r2 = 20;
        double w = 1.4, we = 1, ws = 1, g = 0.25;
        int numSides = 44, gdsLayer = 4;
        double THETA = 0;
        //
        Struct yBend90 = new Struct("yBend90Test", createYBend90(x, y, r1, r2, w, numSides, THETA, gdsLayer));
        Struct yBendInv90 = new Struct("yBendInv90Test", createYBendInv90(x, y, r1, r2, w, we, numSides, THETA, gdsLayer));
        Struct yBendInvSlot90 = new Struct("yBendInvSlot90Test", createYBendInvSlot90(x, y, r1, r2, ws, g, we, numSides, THETA, gdsLayer));
        //
        lib.add(new Ref(yBend90, 0, 0));
        lib.add(new Ref(yBendInv90, 0, 0));
        lib.add(new Ref(yBendInvSlot90, 0, 0));
        //
        File f = lib.GDSOut("Example06CustomClassMethods.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }

    //
    // yBend90
    //
    public static GArea createYBend90(double x, double y, double r1, double r2, double w, int numSides,
            double THETA, int gdsLayer) {
        GArea ga = new GArea(Scripting.createTorusW(0, r1, r1, w, 270, 360, numSides, 0, gdsLayer));
        ga.or(Scripting.createTorusW(0, -r2, r2, w, 0, 90, numSides, 0, gdsLayer));
        return Scripting.transformGArea(ga, THETA, x, y);
    }

    //
    // yBendInv90
    //
    public static GArea createYBendInv90(double x, double y, double r1, double r2, double w,
            double we, int numSides, double THETA, int gdsLayer) {
        GArea ga = createYBend90(x, y, r1, r2, w + 2 * we, numSides, THETA, gdsLayer);
        return ga.subtract(createYBend90(x, y, r1, r2, w, numSides, THETA, gdsLayer));
    }

    //
    // yBendInvSlot90
    //
    public static GArea createYBendInvSlot90(double x, double y, double r1, double r2, double ws,
            double g, double we, int numSides, double THETA, int gdsLayer) {
        GArea ga = createYBend90(x, y, r1, r2, ws + 2 * we + 2 * g, numSides, THETA, gdsLayer);
        ga.subtract(createYBend90(x, y, r1, r2, ws + 2 * g, numSides, THETA, gdsLayer));
        return ga.or(createYBend90(x, y, r1, r2, ws, numSides, THETA, gdsLayer));
    }

}
