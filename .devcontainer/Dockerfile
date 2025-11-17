FROM mcr.microsoft.com/devcontainers/java:21


RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
    maven\
    openjfx\
    xfce4 \
    xfce4-goodies \
    tigervnc-standalone-server \
    tigervnc-common \
    novnc \
    websockify \
    dbus-x11 \
    && apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /root/.vnc && \
    echo "password" | vncpasswd -f > /root/.vnc/passwd && \
    chmod 600 /root/.vnc/passwd

RUN echo '#!/bin/bash\nunset SESSION_MANAGER\nunset DBUS_SESSION_BUS_ADDRESS\nexec startxfce4' > /root/.vnc/xstartup && \
    chmod +x /root/.vnc/xstartup

EXPOSE 5901 6080