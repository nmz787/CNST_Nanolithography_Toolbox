fileID = fopen('matlabScript.cnst','w');
fprintf(fileID,'0.001 gdsReso\n');
fprintf(fileID,'0.001 shapeReso\n\n');
fprintf(fileID,'# Creating a simple ellipse with Matlab\n');
fprintf(fileID,'<matlabEllipse struct>\n');
fprintf(fileID,'11 layer\n');
fprintf(fileID,'0 0 2 4 44 0 ellipse\n\n');
% array of 100 ellipses along a diagonal with different GDS layers
fprintf(fileID,'# Creating array of 100 ellipses along the diagonal\n');
fprintf(fileID,'<matlabEllipseArray struct>\n');
for i=1:100    
    fprintf(fileID,'%s%s\n', int2str(i),' layer');
    fprintf(fileID,'%s%s%s%s\n', int2str(i), ' ', int2str(i),'  0.4 4 44 0 ellipse');
end
fclose(fileID);

% run the java code from matlab
[status,cmdout] = dos('java -jar CNSTnanoToolbox.jar cnstscripting matlabScript.cnst matlabGDSoutput.gds');
status, cmdout

% open GDS with klayout - use quotes around directory names with spaces
[status,cmdout] = dos('"C:\Program Files\KLayout (64bit)\klayout" D:\DOCUMENTS\zTESTfiles\saveFiles\matlabGDSoutput.gds');
status, cmdout
