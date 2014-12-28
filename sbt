#!/bin/bash
java -Xms512M -Xmx2048M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar sbt-launch.jar "$@"
