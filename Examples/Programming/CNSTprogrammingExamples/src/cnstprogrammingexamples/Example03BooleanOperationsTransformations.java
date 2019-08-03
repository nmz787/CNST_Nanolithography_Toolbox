package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.awt.geom.AffineTransform;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example03BooleanOperationsTransformations {

    public static void main(String[] args) {
        Lib lib = new Lib();
        //
        // Subtraction of 2 GAreas - circle - circleWave
        Struct booleanCCW = new Struct("BooleanCircleCircleWave");
        GArea circle = Scripting.createEllipse(0, 0, 14, 14, 44, 0, 4);
        GArea circWave = Scripting.createCircleWave(0, 0, 10, 10, 0.5, 128, 0, 4);
        circle.subtract(circWave);
        booleanCCW.add(circle);

        // boolean examples between 2 circles
        Struct booleanCircles = new Struct("BooleanCircles");
        GArea circle1 = Scripting.createEllipse(0, 0, 1, 1, 44, 0, 4);
        GArea circle2 = Scripting.createEllipse(1, 1, 1, 1, 44, 0, 4);
        // add both circles to the struct
        booleanCircles.add(circle1);
        booleanCircles.add(circle2);

        // OR Example
        // create a temporary copy of circle1
        GArea temp = new GArea(circle1);
        temp.or(circle2);
        // affine transform - translation 4um in x
        temp.transform(AffineTransform.getTranslateInstance(4, 0));
        booleanCircles.add(temp);

        // AND Example
        temp = new GArea(circle1);
        temp.and(circle2);
        // affine transform - translation 8um in x
        temp.transform(AffineTransform.getTranslateInstance(8, 0));
        booleanCircles.add(temp);

        // XOR Example
        temp = new GArea(circle1);
        temp.xor(circle2);
        // affine transform - translation 12um in x
        temp.transform(AffineTransform.getTranslateInstance(12, 0));
        booleanCircles.add(temp);

        // SUBTRACT Example
        temp = new GArea(circle1);
        temp.subtract(circle2);
        // affine transform - translation 16um in x        
        temp.transform(AffineTransform.getTranslateInstance(16, 0));
        booleanCircles.add(temp);

        lib.add(new Ref(booleanCCW, 0, 0));
        lib.add(new Ref(booleanCircles, 0, 0));
        //
        File f = lib.GDSOut("Example03BooleanOperationsTransformations.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }
}
