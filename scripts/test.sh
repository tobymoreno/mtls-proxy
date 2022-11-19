#!/bin/bash

IMAGE_CHECK="Image is up to date"

while [ 1 -ne 0 ]
do
    date
    docker login -utoby.moreno@gmail.com devsecopsenv.jfrog.io -p "${jfrog-pwd}"
    docker pull 1rom2ny/mtls-proxy:v7 | grep "${IMAGE_CHECK}"

    if [ $? -ne 1 ]; then 
        echo "${IMAGE_CHECK}"
    else
        echo "There is a new image. Update the service"
        kubectl apply -f ../secrets.yml
        kubectl replace -f ../deploymentservice-cluster.yml
        sleep 2
        kubectl apply -f ../mtls-loadbalancer.yaml
        sleep 3
        kubectl rollout restart -n default deployment mtls-proxy
        echo "Restarting ..."
        sleep 3
    fi
  echo "Sleeping ..."
  sleep 60
done



