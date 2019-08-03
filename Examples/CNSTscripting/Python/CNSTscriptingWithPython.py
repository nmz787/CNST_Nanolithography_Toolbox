# -*- coding: utf-8 -*-
"""
Created on Wed Jan 20 21:21:47 2016

@author: rob
"""

import math

f = open('pythonScript.cnst', 'w')
f.write(str('0.001 gdsReso\n'))
f.write(str('0.001 shapeReso\n\n'))

f.write(str('# Creating a simple ellipse with Python'))

f.write(str('<pythonEllipse struct>\n'))
f.write(str('11 layer\n'))
f.write(str('0 0 2 4 44 0 ellipse\n\n'))

f.write(str('# 100 circles along a circular path\n'))
f.write(str('<pythonCirclesAlongPath struct>\n'))

radius = 10
num = 100
increment = 2*math.pi/num
for x in xrange(num):
    f.write(str('%d layer\n' %(x+1)))
    xCoord = radius * math.cos(x*increment)
    yCoord = radius * math.sin(x*increment)
    f.write(str('%.4f %.4f 0.2 0.2 44 0 ellipse\n'%(xCoord,yCoord)))
    f.close
