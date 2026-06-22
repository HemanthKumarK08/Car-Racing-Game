#!/bin/bash

echo "Starting Car Racing Game..."

cd "$(dirname "$0")/src" || exit

javac */*.java
java main.Main

echo "Game closed."