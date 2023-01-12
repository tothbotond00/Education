from socket import socket, AF_INET,SOCK_STREAM, SOCK_DGRAM, timeout, error
import sys


server_addr = ("",int(sys.argv[1]))

with socket(AF_INET,SOCK_STREAM) as server:
	server.settimeout(1.0)

	server.bind(server_addr)

	server.listen(2)

	while True:
		try:
			client, client_addr = server.accept()
			print("csatlakozott: ",client_addr)
			data = client.recv(200).decode()
			print("Kaptam: ",data)
			
			client.send("Hello Client!".encode())
			
			client.close()
		except error:
			pass