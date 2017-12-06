### 1. Get SSL certificate

- Sign into the AWS Management Console and open the ACM console. Then choose Request a certificate.

- On the Request a certificate page, type your domain name. Use "***.csye6225-fall2017-lijin3.me**".

- After typing valid domain names, choose Review and Request.

- As the review page correctly contains the information, choose Confirm and request.

- Choosing the pending domain name, then click "**Create record set in Route 53**", and wait for the domain to be issued.


### 2. The steps taken to configure SSL certificate for the application server

- Set a Listner for the load balancer and set it to the port 443. Use the SSL certificate Arn in this listner.

- To allow an IAM user to deploy the certificate on the load balancer using the AWS Management Console, you must allow access to the ACM ListCertificates API action. Add a role attaching the policy "AWSCertificateManagerFullAccess".



