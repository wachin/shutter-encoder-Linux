<img align=left src="https://www.shutterencoder.com/images/icon.png" width="64">
<h1>Shutter Encoder v16.1</h1>

![logo](/SocialBanner.png)


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
sudo apt-get install default-jre libimage-exiftool-perl ffmpeg p7zip-full dcraw mkvtoolnix mediainfo youtube-dl git
```



## Instalación
Ponga una por una las siguientes líneas en una terminal ejecutandolas:

```
mkdir AppLinux
cd AppLinux
git clone https://github.com/wachin/shutter-encoder-Linux
cd shutter-encoder-Linux
./set_icon_and_java_launcher.sh
cp Shutter-Encoder.desktop $HOME/.local/share/applications/
echo "Dios les bendiga"

```




# Consultas

Desktop Launcher to a Java App?  
https://forums.linuxmint.com/viewtopic.php?t=238109
