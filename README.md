# FFMPEGconv
A simple GUI Java application for compressing video, leveraging ffmpeg and imagemagick open source library

## Prerequisites

1. FFMPEG
2. imagemagick
3. A package manager to download FFMPEG and imagemagick (eg. Homebrew for macOS)
4. Java openjdk18 or newer

## Installation

The installer script included in the repository can be used to install all the prerequisites. It can be run by using the command `chmod +x installer.sh; ./installer.sh`

OR

You can install it individually yourself manually

### 1. Package Manager 

Homebrew can be installed from [brew.sh](https://brew.sh) or via this command :

`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`

### 2. FFMPEG

Once a package manager is installed, 

a. macOS

`brew install ffmpeg imagemagick`

b. Linux 

Just use FFMPEG and imagemagick via the CLI, nerd! /s

i. Ubuntu 

`sudo apt-get -y ffmpeg imagemagick`

ii. RHEL

`sudo yum install ffmpeg imagemagick`

iii. Fedora

`sudo dnf install -y ffmpeg imagemagick`

iv. Arch

`sudo pacman -Syu ffmpeg imagemagick`

### 3. openjdk

Requires openjdk 18 or later. Download from openjdk website [here](https://jdk.java.net/archive/)

### 4. FFMPEGconv

Clone this repository on you local machine. Simply locate, unzip the file, and run the GUI using 

`java -jar ffmpeg-gui.jar`


   
