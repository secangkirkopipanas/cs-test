FROM java:9-jre
MAINTAINER Robertus Lilik Haryanto <robert.djokdja@gmail.com>

# Build arguments
ARG TARGET_DIR
ARG APP_NAME
ARG APP_VERSION

# Preparing application directory
RUN \
   echo "### Preparing application directory"  && \
	mkdir -p /opt/apps

# Copying application binaries
COPY $TARGET_DIR/$APP_NAME-$APP_VERSION.jar /opt/apps/

# Environment variables
ENV TZ Asia/Singapore

# Define default command
CMD ["/bin/bash"]