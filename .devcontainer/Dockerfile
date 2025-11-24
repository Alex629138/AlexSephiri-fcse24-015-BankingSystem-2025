FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
    maven \
    openjfx \
    xvfb \
    x11vnc \
    novnc \
    websockify \
    dbus-x11 \
    fluxbox \
    && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

WORKDIR /workspace
EXPOSE 5901 6080
CMD ["/bin/bash", "-c", "run-gui-app"]