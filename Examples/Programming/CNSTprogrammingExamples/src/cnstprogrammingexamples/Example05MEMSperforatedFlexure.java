package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class Example05MEMSperforatedFlexure {

    public static void main(String[] args) {
        Lib lib = new Lib();
        //
        Struct top = new Struct("top");
        //
        Struct flexureWithCircles = new Struct("flexureWithCircles");
        // creating element flexure2C, structural layer = 4, anchor layer = 1
        ArrayList<GArea> al = Scripting.createFlexure2C(0, 0, 0.5, 5, 30, 2, 40, 20, 8, 10, 5.2, 4, 0.4, 1, 0, 4);
        
        // store ArrayList<GArea> into Struct
        Scripting.createStruct(flexureWithCircles, al);
        
        // creating an array of circles (circle layer = 7) in flexureWithCircles Struct
        Scripting.createSqrHoleC("circleArray", flexureWithCircles, 0, 0, 1, 1, 16, 4, 4, 10, 5, 0, 7);

        // extracting all objects with flexure layer 4
        GArea flexure = new GArea(flexureWithCircles.getArea(4), 4);
        // extracting all objects with circle layer 7
        GArea circle = new GArea(flexureWithCircles.getArea(7), 7);
        // boolean subtraction to create perforated flexure
        flexure.subtract(circle);

        // store boolean structure
        top.add(flexure);

        // extract and store anchors
        top.add(new GArea(flexureWithCircles.getArea(1), 1));
        //
        lib.add(new Ref(top, 0, 0));
        lib.add(new Ref(flexureWithCircles, 0, 0));
        //
        File f = lib.GDSOut("Example05MEMSperforatedFlexure.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
