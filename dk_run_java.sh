# 删除旧版 container （如果有的话）
#docker stop chatgpt-web-zyl && docker rm chatgpt-web-zyl
#docker build -t chatgpt-web-java . 

# 如果这里要使用 java 的容器访问 mysql 容器，需要使用 host.docker.internal 而不是 localhost，才可以访问到宿主机的 3009 端口（mysql开放了3009端口）
#docker run -d -p 3002:3002  -e MYSQL_USER_NAME=root -e MYSQL_PASSWORD=123456 -e CHAT_OPENAI_API_KEY=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 -e CHAT_OPENAI_ACCESS_TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiIyMjgzNDk5OTcxQHFxLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InVzZXJfaWQiOiJ1c2VyLThISDlmSzM5b1NKMjNOY0l2YTlKTE5oMiJ9LCJpc3MiOiJodHRwczovL2F1dGgwLm9wZW5haS5jb20vIiwic3ViIjoiYXV0aDB8NjNiOTMyZTM0MjU0MzM1ZGEzYzdlZmJmIiwiYXVkIjpbImh0dHBzOi8vYXBpLm9wZW5haS5jb20vdjEiLCJodHRwczovL29wZW5haS5vcGVuYWkuYXV0aDBhcHAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY4MTg5MTg3MywiZXhwIjoxNjgzMTAxNDczLCJhenAiOiJUZEpJY2JlMTZXb1RIdE45NW55eXdoNUU0eU9vNkl0RyIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9mZmxpbmVfYWNjZXNzIn0.k9a62o3HBv2oWUL9wBeMYEsd_krsVbDHLSrPCSv-SHvVMRDOSgQxWLTGKsC0BLxsKFvFnn3He6qEm9ptCA9l0HMk1YLLCJYISLwQwykoLLBLjIQv40EYL_k9pv7smnkLaVdNzD6OuovnD4-Y5BWCb-vAqVoBh-vudKK2c3GrgdQ7L72gNAZBNhPDHx8GGiG_XaNBQDEIcLrjNAqm4GYab4lm1wgqndRDQr4uFuCwFJaFPjTULh_9YHx3qy5j5FXsUardV8JksESfBCJlgLql0NX1n6I3ZnSJFivbwGSB3VNUChqNg-EOsxav73djVhIMZvyDehhekv8sw7-9J7sUGA -e CHAT_OPENAI_API_BASE_URL=http://api.openai.com -e LD_LIBRARY_PATH=/usr/lib/x86_64-linux-gnu -e CHAT_HTTP_PROXY_HOST=127.0.0.1 -e CHAT_HTTP_PROXY_PORT=7890 -e JDBC_URL=jdbc:mysql://host.docker.internal:3309/chat?useUnicode=true\&characterEncoding=UTF-8\&autoReconnect=true\&serverTimezone=Asia/Tokyo  chatgpt-web-zyl

docker run -d -p 3002:3002 -e MYSQL_USER_NAME=root \
	-e MYSQL_PASSWORD=123456 \
	-e CHAT_OPENAI_API_KEY=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 \
	-e CHAT_OPENAI_ACCESS_TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiIyMjgzNDk5OTcxQHFxLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InVzZXJfaWQiOiJ1c2VyLThISDlmSzM5b1NKMjNOY0l2YTlKTE5oMiJ9LCJpc3MiOiJodHRwczovL2F1dGgwLm9wZW5haS5jb20vIiwic3ViIjoiYXV0aDB8NjNiOTMyZTM0MjU0MzM1ZGEzYzdlZmJmIiwiYXVkIjpbImh0dHBzOi8vYXBpLm9wZW5haS5jb20vdjEiLCJodHRwczovL29wZW5haS5vcGVuYWkuYXV0aDBhcHAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY4MTg5MTg3MywiZXhwIjoxNjgzMTAxNDczLCJhenAiOiJUZEpJY2JlMTZXb1RIdE45NW55eXdoNUU0eU9vNkl0RyIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9mZmxpbmVfYWNjZXNzIn0.k9a62o3HBv2oWUL9wBeMYEsd_krsVbDHLSrPCSv-SHvVMRDOSgQxWLTGKsC0BLxsKFvFnn3He6qEm9ptCA9l0HMk1YLLCJYISLwQwykoLLBLjIQv40EYL_k9pv7smnkLaVdNzD6OuovnD4-Y5BWCb-vAqVoBh-vudKK2c3GrgdQ7L72gNAZBNhPDHx8GGiG_XaNBQDEIcLrjNAqm4GYab4lm1wgqndRDQr4uFuCwFJaFPjTULh_9YHx3qy5j5FXsUardV8JksESfBCJlgLql0NX1n6I3ZnSJFivbwGSB3VNUChqNg-EOsxav73djVhIMZvyDehhekv8sw7-9J7sUGA \
	-e CHAT_OPENAI_API_BASE_URL=http://api.openai.com \
	-e LD_LIBRARY_PATH=/usr/lib/x86_64-linux-gnu \
	-e JDBC_URL=jdbc:mysql://13.113.114.35:3309/chat?useUnicode=true\&characterEncoding=UTF-8\&autoReconnect=true\&serverTimezone=Asia/Tokyo \
	--network test_net \
	--network-alias gpt_net chatgpt-web-java


	#-e CHAT_HTTP_PROXY_HOST=13.113.114.35 \
	#-e CHAT_HTTP_PROXY_PORT=1080 \

#docker run -d -p 3002:3002 -e JDBC_URL=jdbc:mysql://host.docker.internal:3309/chat?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai -e MYSQL_USER_NAME=root -e MYSQL_PASSWORD=123456 -e CHAT_OPENAI_API_KEY=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 -e CHAT_OPENAI_ACCESS_TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiIyMjgzNDk5OTcxQHFxLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InVzZXJfaWQiOiJ1c2VyLThISDlmSzM5b1NKMjNOY0l2YTlKTE5oMiJ9LCJpc3MiOiJodHRwczovL2F1dGgwLm9wZW5haS5jb20vIiwic3ViIjoiYXV0aDB8NjNiOTMyZTM0MjU0MzM1ZGEzYzdlZmJmIiwiYXVkIjpbImh0dHBzOi8vYXBpLm9wZW5haS5jb20vdjEiLCJodHRwczovL29wZW5haS5vcGVuYWkuYXV0aDBhcHAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY4MTg5MTg3MywiZXhwIjoxNjgzMTAxNDczLCJhenAiOiJUZEpJY2JlMTZXb1RIdE45NW55eXdoNUU0eU9vNkl0RyIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9mZmxpbmVfYWNjZXNzIn0.k9a62o3HBv2oWUL9wBeMYEsd_krsVbDHLSrPCSv-SHvVMRDOSgQxWLTGKsC0BLxsKFvFnn3He6qEm9ptCA9l0HMk1YLLCJYISLwQwykoLLBLjIQv40EYL_k9pv7smnkLaVdNzD6OuovnD4-Y5BWCb-vAqVoBh-vudKK2c3GrgdQ7L72gNAZBNhPDHx8GGiG_XaNBQDEIcLrjNAqm4GYab4lm1wgqndRDQr4uFuCwFJaFPjTULh_9YHx3qy5j5FXsUardV8JksESfBCJlgLql0NX1n6I3ZnSJFivbwGSB3VNUChqNg-EOsxav73djVhIMZvyDehhekv8sw7-9J7sUGA -e CHAT_OPENAI_API_BASE_URL=http://api.openai.com -e CHAT_HTTP_PROXY_HOST=127.0.0.1 -e CHAT_HTTP_PROXY_PORT=7890 chatgpt-web-zyl
