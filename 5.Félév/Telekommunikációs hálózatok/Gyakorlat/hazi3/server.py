from pickle import TRUE
from socket import socket, AF_INET, SOCK_STREAM
from select import select
import struct
import sys
import random

##FUNCTIONS AND DATA
isOver = False
clients = 0
solution = random.randint(1,100)

def getResponse(isOver, solution, operand, number):
	if isOver :
		return b'V'
	if operand == '>':
		if solution > number :
			return b'I'
		else :
			return b'N'
	if operand == '<':
		if solution < number :
			return b'I'
		else :
			return b'N'
	if operand == '=':
		if solution == number:
			return b'Y'
		else :
			return b'K'

##SERVER COMMUNICATION
server_addr = ((sys.argv[1]),int(sys.argv[2]))
packer = struct.Struct('ci')

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
				clients += 1
			else :
				data = s.recv(packer.size)
				if not data:
					print("Kilepett: ", s)
					inputs.remove(s)
					s.close()
					clients -= 1
					if (clients == 0):
						isOver = False
						solution = random.randint(1,100)
				else :
					data = packer.unpack(data)
					operand = data[0].decode()
					number  = int(data[1])
					responseChar = getResponse(isOver,solution, operand, number)
					if(responseChar == b'Y'):
						isOver = True
					s.send(packer.pack(responseChar, 0))