#!/bin/bash

echo "🚀 Starting GUI environment for JavaFX Banking App..."

# Kill any existing processes
pkill -9 Xvfb x11vnc websockify 2>/dev/null

# Start virtual display
echo "📺 Starting virtual display..."
Xvfb :99 -screen 0 1280x720x24 > /dev/null 2>&1 &
export DISPLAY=:99
sleep 2

# Start VNC server
echo "🔌 Starting VNC server..."
x11vnc -display :99 -forever -shared -rfbport 5900 -nopw > /dev/null 2>&1 &
sleep 1

# Start noVNC web interface
echo "🌐 Starting web interface..."
websockify --web=/usr/share/novnc 6080 localhost:5900 > /dev/null 2>&1 &
sleep 2

echo "✅ GUI environment ready!"
echo "📱 Access your app at: http://localhost:6080/vnc.html"
echo ""
echo "🏦 Starting Banking App..."

# Set Java home and run app
export JAVA_HOME=/usr/lib/jvm/msopenjdk-current
mvn javafx:run
