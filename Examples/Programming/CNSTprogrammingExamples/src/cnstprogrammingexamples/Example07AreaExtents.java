package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example07AreaExtents {

    public static void main(String[] args) {
        Lib lib = new Lib();
        // creating and storing a y-bend
        Struct yBendTest = new Struct("yBendExample");
        GArea yBend = Scripting.createYbend(0, 0, 10, 10, 20, -40, 1, 0, 7, 0.01);
        yBendTest.add(yBend);
        // extracting pattern extents
        Struct yBendCentered = new Struct("yBendCenteredShape");
        Area a = new Area(yBend.getArea());
        Rectangle2D rec = a.getBounds2D();
        yBendCentered.add(new Ref(yBendTest, -rec.getCenterX(), -rec.getCenterY()));
        //
        lib.add(new Ref(yBendTest, 0, 0));
        lib.add(new Ref(yBendCentered, 0, 0));
        File f = lib.GDSOut("Example07AreaExtents.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
