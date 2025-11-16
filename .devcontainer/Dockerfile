FROM mcr.microsoft.com/devcontainers/java:21

RUN apt-get update && \
    apt-get install -y \
        openjfx \
        xfce4 \
        xfce4-goodies \
        tightvncserver \
        dbus-x11 \
        x11-xserver-utils && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV DISPLAY=:1

RUN mkdir -p /home/vscode/.vnc && \
    echo "codespaces" | vncpasswd -f > /home/vscode/.vnc/passwd && \
    chmod 600 /home/vscode/.vnc/passwd && \
    chown -R vscode:vscode /home/vscode/.vnc
