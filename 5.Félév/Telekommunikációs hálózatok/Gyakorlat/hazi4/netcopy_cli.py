import hashlib
from socket import socket, AF_INET, SOCK_STREAM
from sys import argv

srv_ip = argv[1]
srv_port = argv[2]
chsum_serv_ip = argv[3]
chsum_srv_port = argv[4]
file_id = argv[5]
file_path = argv[6]

with socket(AF_INET,SOCK_STREAM) as client:
	md5 = hashlib.md5(open(file_path,'rb').read()).hexdigest()	
	with open(file_path, "rb") as f:
		client.connect((srv_ip,int(srv_port)))
		l = f.read(10)
		while l:
			client.sendall(l)
			l = f.read(10)
		f.close()
	with socket(AF_INET,SOCK_STREAM) as client:
		client.connect((chsum_serv_ip,int(chsum_srv_port)))
		client.sendall(("BE|"+str(file_id)+"|60|"+str(len(str(md5)))+"|"+ str(md5)).encode())
		data = client.recv(200)
		print(data.decode())