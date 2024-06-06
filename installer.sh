#!/bin/bash

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Determine the OS
OS="$(uname -s)"

if [ "$OS" == "Darwin" ]; then
    echo "macOS detected."

    # Check if Homebrew is installed
    if ! command_exists brew; then
        echo "Homebrew not found. Installing Homebrew..."
        /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
    else
        echo "Homebrew is already installed."
    fi

    # Install ffmpeg using Homebrew
    echo "Installing ffmpeg..."
    brew install ffmpeg

    # Install openjdk@18 using Homebrew
    echo "Installing imagemagick..."
    brew install imagemagick

    # Install openjdk@18 using Homebrew
    echo "Installing openjdk@21..."
    brew install openjdk@21

elif [ "$OS" == "Linux" ]; then
    echo "Linux detected."

    # Check for package manager and install ffmpeg and openjdk@18
    if command_exists apt-get; then
        echo "apt-get detected."
        sudo apt-get update
        sudo apt-get install -y ffmpeg openjdk-21-jdk imagemagick
    elif command_exists yum; then
        echo "yum detected."
        sudo yum install -y epel-release
        sudo yum install -y ffmpeg java-21-openjdk-devel imagemagick
    elif command_exists dnf; then
        echo "dnf detected."
        sudo dnf install -y ffmpeg java-21-openjdk-devel imagemagick
    elif command_exists pacman; then
        echo "pacman detected."
        sudo pacman -Syu ffmpeg jdk-openjdk imagemagick
    else
        echo "No supported package manager found. Please install ffmpeg and openjdk@18 manually."
        exit 1
    fi

else
    echo "Unsupported OS: $OS"
    exit 1
fi

echo "Script execution completed."
