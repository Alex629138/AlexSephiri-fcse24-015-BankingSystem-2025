#!/bin/bash
export DISPLAY=:1
export JAVA_HOME=/usr/lib/jvm/msopenjdk-current
echo "🏦 Starting Banking App..."
echo "📱 Access GUI at: http://localhost:6080"
mvn javafx:run
