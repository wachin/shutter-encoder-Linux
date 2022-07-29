<img align=left src="https://www.shutterencoder.com/images/icon.png" width="64">
<h1>Shutter Encoder v16.1</h1>

[https://www.shutterencoder.com/](https://www.shutterencoder.com/)


# Shutter-Enconder para Linux de 32 bits

Para Sistemas Operativos Linux de 32 bits como son:

[MX Linux](https://mxlinux.org/)
[Linux Mint Debian Edition](https://linuxmint.com/download_lmde.php)
[Escuelas Linux](https://escuelaslinux.sourceforge.io/)
[AV Linux](https://www.bandshed.net/) 

**Nota: **También funciona en 64 bits.

**Probado en:**

- MX Linux 21 de 32 bits
- AV Linux MXDE de 32 bits

## Dependencias

```
sudo apt-get install default-jre git p7zip-full \
     dcraw yt-dlp ffmpeg libimage-exiftool-perl \
     mkvtoolnix dvdauthor mediainfo
```

El autor de este programa [Paul Pacífico](https://twitter.com/paulpacifico) lo diseñó para trabajar con las siguientes dependencias:

7za: www.7-zip.org
dcraw: www.dechifro.org/dcraw
yt-dlp: github.com/yt-dlp/yt-dlp
ffmpeg: ffmpeg.org
exiftool: exiftool.org
tsMuxeR: github.com/justdan96/tsMuxer
mkvmerge: mkvtoolnix.download
mediainfo: mediaarea.net/fr/MediaInfo
dvdauthor: dvdauthor.sourceforge.net
WeTransfer: developers.wetransfer.com
XpdfReader: www.xpdfreader.com
bmxtranswrap: sourceforge.net/p/bmxlib/home/Home

De esas están disponibles la mayoría en los repositorios de MX Linux 21 excepto:

1. tsMuxeR: github.com/justdan96/tsMuxer
2. WeTransfer: developers.wetransfer.com
3. XpdfReader: www.xpdfreader.com
4. bmxtranswrap: sourceforge.net/p/bmxlib/home/Home

de estas la 1, 3 y 4 creo que se las podría instalar desde código fuente, pondré las instrucciones luego, por el momento el programa puede funcionar sin esas para las cosas más comunes y necesarias para mí como son:

Conversión de audio, video a diversos formatos con ffmpeg, vista de la información de los archivos multimedia con mediainfo


## Instalación
Les cuento que estoy haciendo funcionar algunas aplicaciones en Linux de 32 bits e igual esta sirve también para 64 bits y las estoy instalando en una carpeta llamada:

AppLinux

le pido por favor que la cree y no la borre, pues para tener todas estas aplicaciones allí:

![](vx_images/333465882356642.png)

ahora sí ponga una terminal allí :

```
git clone https://github.com/wachin/shutter-encoder-Linux
cd shutter-encoder-Linux
./set_icon_and_java_launcher.sh
cp Shutter-Encoder.desktop $HOME/.local/share/applications/
echo "Dios les bendiga"
```

![](vx_images/206615910115068.png)

después de instalado búsquelo entre sus aplicaciones:

shutter

![](vx_images/46265916122890.png)

y se abrirá

Shutter Encoder abierto, dar clic en Explorar o arrastrar los archivos allí para cargarlos:

![](vx_images/109565964937234.png)

# Ejemplos de uso

## Conversión de Video a mp3
He cargado un video mp4 y lo voy a convertir en mp3

Le recomiendo saber primero qué calidad tiene el audio del video, como ya instalamos mediainfo lo usaremos para saber esa información, de clic derecho en el video que haya cargado y clic en informaciones, ejemplo:

![](vx_images/589913107901560.png)

allí aparecerá la información:

![](vx_images/347962103523100.png)

como sé que es:

192 kbps no le puedo poner más de este valor

ahora voy a elegir la función:

MP3

así:

![](vx_images/578865974807420.png)

aparecerá una ventana más, por defecto aparecerá en la calidad del audio:

320

pongale en vez del valor por defecto el valor que tiene su mp3, en este caso:

![](vx_images/404573814648936.png)

clic en iniciar funcion:

![](vx_images/23034061549476.png)

ahora a esperar un poco, eso dependerá de la duración del video y tendrá su mp3

cuando aparezca completado al 100%:

![](vx_images/311604649751878.png)

# Manual de uso de Shutter Encoder
Está en inglés:

**Shutter Encoder - Documentation**  
[https://www.shutterencoder.com/documentation.html](https://www.shutterencoder.com/documentation.html)


# Consultas

Desktop Launcher to a Java App?  
https://forums.linuxmint.com/viewtopic.php?t=238109
