#!/bin/bash
#create a stack instance
echo "input the stack name"
read name


NAME=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)

aws cloudformation create-stack --stack-name $name --template-body file://../cloudformation/assignment8.json --capabilities CAPABILITY_NAMED_IAM --parameters "ParameterKey=ParamRecordSetsName,ParameterValue=$NAME"
