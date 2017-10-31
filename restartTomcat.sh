#!/bin/bash

sudo service tomcat8 stop
cd /var/lib/tomcat8/webapps
sudo rm -rf test-1.0.0
sudo service tomcat8 start