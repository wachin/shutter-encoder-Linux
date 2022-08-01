#!/bin/bash -e

#
# Run this to update the launcher file with the current path to the application icon
#

APPDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
if [ -w "$APPDIR"/Shutter-Encoder.desktop ]; then
	sed -i -e "s@^Icon=.*@Icon=$APPDIR/src/contents/icon.png@" "$APPDIR"/Shutter-Encoder.desktop
else
	echo "$APPDIR"/Shutter-Encoder.desktop is not writable
	exit 1
fi

if [ -w "$APPDIR"/Shutter-Encoder.desktop ]; then
	sed -i -e "s@^Exec=.*@Exec=/usr/bin/java -jar $APPDIR/Shutter-Encoder.jar@" "$APPDIR"/Shutter-Encoder.desktop
else
	echo "$APPDIR"/Shutter-Encoder.desktop is not writable
	exit 1
fi

# 
# Tomado del orignal del colocador de la ruta del lanzador de Zotero (https://www.zotero.org/) para que funcione 
# el lanzador .desktop y modificadolo para corregir el nombre de usuario y la ruta del lanzador
# Por: Washington Indacochea Delgado, 2022, wachin.id@gmail.com 
