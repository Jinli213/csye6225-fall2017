#!/bin/bash
aws ec2 create-security-group --group-name csye6225-fall2017-webapp --description "security group for csye6225-fall2017-webapp" --vpc-id vpc-a3b854db
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 22 --cidr 203.0.113.0/24
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 80 --cidr 203.0.113.0/24
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 443 --cidr 203.0.113.0/24
aws ec2 run-instances --image-id ami-cd0f5cb6 --count 1 --instance-type t2.micro --security-groups csye6225-fall2017-webapp
--block-device-mappings "[{\"DeviceName\":\"/dev/sda1\",\"Ebs\":{\"VolumeSize\":16,\"VolumeType\": gp2,\"DeleteOnTermination\":false}}]"
aws ec2 describe-instances --filters "Name=instance-type,Values=t2.micro" "Name=image-id,Values=ami-cd0f5cb6"
aws route53 change-resource-record-sets --hosted-zone-id ZZO35VY49I7OL --change-batch file://C:\awscli\route53\change-resource-record-sets.json
