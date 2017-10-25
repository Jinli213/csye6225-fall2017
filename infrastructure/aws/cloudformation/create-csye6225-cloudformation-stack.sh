#!/bin/bash
echo  "Input the stack name which you want to create"
read name
aws cloudformation create-stack --stack-name $name --template-body file:///home/jinli/CSYE6225/assignment4.template
