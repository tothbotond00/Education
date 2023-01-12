import time
from sys import argv
from socket import socket, AF_INET, SOCK_STREAM
from select import select

checksums = []
ts = int(time.time())

def storeMessage(message):
    obj = {
        "file_id" : message[1],
        "valid" : int(message[2]) + int(time.time()),
    	"checksum_length" : message[3],
    	"checksum_bytes" : message[4],
    }
    return obj

def getMessage(file_id, checksums):
    for checksum in checksums :
        if checksum["file_id"] == file_id :
            return checksum["checksum_length"] + "|" + checksum["checksum_bytes"]
    return "0|"

def isOlder(obj):
    if obj["valid"] > int(time.time()):
        return True
    else : 
        return False

def removeOld(checksums):
    return list(filter(isOlder,checksums))

srv_ip = argv[1]
srv_port = argv[2]

with socket(AF_INET, SOCK_STREAM) as server :
	inputs = [server]
		
	server.bind((srv_ip, int(srv_port)))
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
				data = s.recv(200)
				if not data:
					print("Kilepett: ", s)
					inputs.remove(s)
					s.close()
				else :
					checksums = removeOld(checksums)
					data = data.decode()
					message = data.split('|')
					if message[0] == "BE" :
						obj = storeMessage(message)
						checksums.append(obj)
						s.sendall(("Ok").encode())
					else :
						message = getMessage(message[1], checksums)
						s.sendall(message.encode())