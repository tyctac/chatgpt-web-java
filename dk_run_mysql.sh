# 删除旧版 container （如果有的话）
docker stop mysql_gpt && docker rm mysql_gpt
# 构建 image
docker build -t mysql_gpt_img:latest . -f Dockerfile_mysql
# 运行 container
docker run -d -p 3309:3306 \
     --name mysql_gpt \
     -v ~/mydata/mysql_dummy/data:/var/lib/mysql \
     -v  ~/mydata/mysql_dummy/conf:/etc/mysql/conf.d \
     -v ~/mydata/mysql_dummy/log:/var/log/mysql \
     mysql_gpt_img:latest



     #--network test_net \
     #--network-alias gpt_net \
