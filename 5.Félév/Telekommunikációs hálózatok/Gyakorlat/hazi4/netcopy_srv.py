import hashlib
from sys import argv
from socket import socket, AF_INET, SOCK_STREAM

srv_ip = argv[1]
srv_port = argv[2]
chsum_serv_ip = argv[3]
chsum_srv_port = argv[4]
file_id = argv[5]
file_path = argv[6]

with socket(AF_INET,SOCK_STREAM) as server:
	server.bind((srv_ip,int(srv_port)))
	server.listen(1)
	
	client, client_addr = server.accept()
	end = False
	with open(file_path, "wb") as f:
		while not end:
			data = client.recv(10)
			if data:
				f.write(data)
			else:
				client.close()
				end = True
		f.close()
	with socket(AF_INET,SOCK_STREAM) as client:
		md5 = hashlib.md5(open(file_path,'rb').read()).hexdigest()
		client.connect((chsum_serv_ip,int(chsum_srv_port)))
		client.sendall(("KI|"+str(file_id)).encode())
		data = client.recv(200)
		data = data.decode()
		if data == "0|" :
			print("CSUM CORRUPTED")
		else :
			message = data.split('|')
			if message[1] == md5 :
				print("CSUM OK")
			else :
				print("CSUM CORRUPTED")