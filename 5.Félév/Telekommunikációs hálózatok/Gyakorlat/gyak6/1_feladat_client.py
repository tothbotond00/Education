from socket import socket,AF_INET, SOCK_STREAM, timeout, SOL_SOCKET, SO_REUSEADDR
import struct

server_addr = ('localhost', 10000)
packer = struct.Struct('I I 1s')  #int, int, char[1]

with socket(AF_INET, SOCK_STREAM) as client:
	szam1 = input("Adj meg egy szamot:")
	op = input("Adj meg egy operatort:")
	szam2 = input("Adj meg masik szamot:")

	
	values = (int(szam1), int(szam2), op.encode())
	packed_data = packer.pack(*values)
	# packed_data = packer.pack(int(szam1), int(szam2), op.encode())   # ua mint az elozo sor
	
	client.connect(server_addr)
	client.sendall(packed_data)
	data = client.recv(10).decode()
	
	print("Eredmeny:",data)
	