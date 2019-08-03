'''
From the journal article (http://dx.doi.org/10.6028/jres.121.024):
    Figures 6j-l show surface micromachined, suspended fluidic channels with integrated spiral delay-line
    heaters. These suspended, high quality-factor, fluidic resonant-structures have applications as biological
    sensing platforms.

    (j) Schematic design of a suspended fluidic channel with integrated spiral delay-line heaters. Optical
    micrographs of (k) an array of devices and (l) a magnified single flow-cell device

From the 'Handbook', section: 2.10.9 Suspended Fluid Cell
'''
import jpype

jar='/home/nathan/Downloads/CNSTNanolithographyToolboxV2016.10.01/CNSTNanolithographyToolboxV2016.10.01.jar'
jpype.startJVM(jpype.getDefaultJVMPath(), '-Djava.class.path=%s' % jar)

JGDS = jpype.JPackage('JGDS2')
Scripting =  jpype.JPackage('CNST').Scripting
File = jpype.JPackage('java.io.File')

lib =JGDS.Lib()

struct = JGDS.Struct("fluid_cell_struct")
#         createFluidCell(double, double, double, double, double, double, double, double, double, double, double, double, double, int, double, double, double, double, double, double, double, double, double, int, int, int, int, int, int, double, double);
fc = Scripting.createFluidCell(0.,     0.,     10.,    30.,    140.,   15.,    15.,    70.,    140.,   35.,    50.,    100.,   200.,   2,   300.,   30.,    30.,    25.,    250.,   25.,    300.,   30.,    15.,    50,  125, 175, 600, 800, 400,   0.	,0.)
for piece in fc:
	struct.add(piece)
ref = JGDS.Ref(struct, 0., 0.)
lib.add(ref);
file_obj = lib.GDSOut("Example01Template.gds")