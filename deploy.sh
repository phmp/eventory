#!/bin/bash
#kill process
ssh root@eventory.proccorp.pl "ps -aux | grep java " > tmp.txt
id=`cat tmp.txt | grep spring | awk '{print $2}'`

ssh root@eventory.proccorp.pl "rm /eventory/app/eventory.jar"
scp target/eventory-1.0-SNAPSHOT.jar root@eventory.proccorp.pl:/eventory/app/eventory.jar

echo "### process to be killed: $id"
ssh root@eventory.proccorp.pl "kill $id"
ssh root@eventory.proccorp.pl "java -jar /eventory/app/eventory.jar --spring.profiles.active=db &"

