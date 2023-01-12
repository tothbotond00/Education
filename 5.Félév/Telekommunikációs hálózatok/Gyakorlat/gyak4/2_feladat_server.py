from socket import socket, AF_INET, SOCK_STREAM
from select import select
import struct

server_addr = ('localhost', 10000)

packer = struct.Struct('ici')

with socket(AF_INET, SOCK_STREAM) as server :
	inputs = [server]
		
	server.bind(server_addr)
	server.listen(1)
	
	while True :
		timeout = 1
		r ,w, e = select(inputs, inputs, inputs, timeout)
		if not (r or w or e):
			continue
		for s in r :
			if s is server :
				client, client_addr = s.accept()
				inputs.append(client)
				print("Kapcsolodott: ", client_addr)
			else :
				data = s.recv(packer.size)
				if not data:
					print("Kilepett: ", s)
					inputs.remove(s)
					s.close()
				else :
					data = packer.unpack(data)
					a = data[0]
					operand = data[1].decode()
					b = data[2]
					if operand == '+' :
						res = a + b
					elif operand == '-':
						res = a - b
					elif operand == '/':
						res = a // b
					elif operand == '*':
						res = a * b
					elif operand == '-':
						res = 0
					print(data[0], data[1], data[2],)
					s.send(struct.pack('!i', res))