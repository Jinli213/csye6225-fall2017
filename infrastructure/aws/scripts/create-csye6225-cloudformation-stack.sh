#!/bin/bash
#create a stack instance
echo "input the stack name"
read name

VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)
Account_ID=$(aws sts get-caller-identity --output text --query Account)
SUBNET_ID=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[0].SubnetId" --output text)
SUBNET_ID_2=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[1].SubnetId" --output text)

HOSTEDZONE_ID=$(aws route53 list-hosted-zones --query "HostedZones[0].Id" --output text)

NAME=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)

aws cloudformation create-stack --stack-name $name --template-body file://../cloudformation/assignment7.json --capabilities CAPABILITY_NAMED_IAM --parameters "ParameterKey=ParamAccountID,ParameterValue=$Account_ID" "ParameterKey=ParamVPCID,ParameterValue=$VPC_ID" "ParameterKey=ParamSubnetID,ParameterValue=$SUBNET_ID" "ParameterKey=ParamHostedZoneID,ParameterValue=$HOSTEDZONE_ID" "ParameterKey=ParamRecordSetsName,ParameterValue=$NAME" "ParameterKey=ParamSubnetID2,ParameterValue=$SUBNET_ID_2"
