package cnstprogrammingexamples;

import CNST.Scripting;
import JGDS2.*;
import java.io.File;

/**
 *
 * @author rob
 */
public class Example01Template {

    public static void main(String[] args) {
        Lib lib = new Lib();

        // Insert Code Here
        File f = lib.GDSOut("Example01Template.gds");
        System.out.println(" Saved to " + f.getAbsolutePath());
    }

    // Insert class methods here
}
