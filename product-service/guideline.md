#    In VPS server (Amazon Linux 2):
# Install Docker
sudo amazon-linux-extras install docker

# Start docker
Start Service: sudo systemctl start docker

Enable on Boot: sudo systemctl enable docker

# Grant permission

Create the docker group (if it doesn't already exist): 
sudo groupadd docker

Add your current user to the group:
sudo usermod -aG docker  $USER

Activate the changes:
newgrp docker

# Create a shared network for all microservices
docker network create microservice-net

# Build a mongodb container
docker run -d \
--name mongodb \
--network microservice-net \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=root \
-e MONGO_INITDB_ROOT_PASSWORD=password \
-v /opt/mongodb/data:/data/db \
--restart unless-stopped \
mongo:7.0.5

# Generate a deploy key (no passphrase)
ssh-keygen -t ed25519 -C "github-actions-deploy" -f ~/.ssh/deploy_key -N ""

# Copy public key to VPS
ssh-copy-id -i ~/.ssh/deploy_key.pub your_user@your_vps_ip

# Paste contents of deploy_key (private) into GitHub Secret VPS_SSH_KEY
cat ~/.ssh/deploy_key