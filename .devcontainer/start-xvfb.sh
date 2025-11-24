#!/bin/bash

# Kill any existing Xvfb or VNC servers
pkill Xvfb || true
pkill x11vnc || true

# Start virtual display
Xvfb :99 -screen 0 1280x720x24 &

# Export DISPLAY
export DISPLAY=:99

# Start VNC server
x11vnc -display :99 -nopw -forever -shared &

# Start noVNC server
websockify -D 6080 localhost:5900

echo "Xvfb + VNC + noVNC started. Open http://localhost:6080/ in browser to see GUI."
