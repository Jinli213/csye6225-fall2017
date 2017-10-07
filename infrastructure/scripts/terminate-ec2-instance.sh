#!/bin/bash
echo  "Input the id of instance which you want to terminate"
read id
aws ec2 terminate-instances --instance-ids $id
aws ec2 delete-security-group --group-name csye6225-fall2017-webapp
