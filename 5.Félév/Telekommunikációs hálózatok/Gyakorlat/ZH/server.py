from socket import socket, AF_INET, SOCK_STREAM, SOCK_DGRAM
from select import select
import struct

def makeData(topic, value):
    obj = {
		"topic": topic.decode().strip('\x00'),
		"value": value,
		"subsribers": [],
	}
    return obj

def inTopics(topic,topics):
    for element in topics :
        if element["topic"] == topic :
            return True
    return False

def findData(topic,topics):
    for element in topics :
        if element["topic"] == topic :
            return element["value"]
    return -9999

udp_addr = ('localhost', 11000)
server_addr = ("localhost",10000)
packer = struct.Struct("3s10sf")
topics = []

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
					if data[0].decode().strip('\x00') == "IN" :
						if inTopics(data[1].decode().strip('\x00'),topics):
							for element in topics :
								if element["topic"] == data[1].decode().strip('\x00') :
									element["value"] = data[2]
									for subscriber in element["subsribers"]:
										with socket(AF_INET, SOCK_DGRAM) as client:
											#JELENLEG BEÉGETTETT UDP SERVER CÍMMEL SIKERÜLT MŰKÖDÉSRE BÍRNOM
											#ITT A subscriber VALAMILYEN ATTRIBÚTUMA ALAPJÁN KÉNE ELKÜLDENI A value-T UDP-N KERESZTÜL
											client.sendto(str(element["value"]).encode(), udp_addr)
											data, _ = client.recvfrom(200)
											print(data.decode())
								break
						else :
							topics.append(makeData(data[1],data[2]))
						s.send("ACK".encode())
					elif data[0].decode().strip('\x00') == "OUT":
						value = findData(data[1].decode().strip('\x00'),topics)
						s.send(str(value).encode())
					elif data[0].decode().strip('\x00') == "REG":
						for element in topics :
								if element["topic"] == data[1].decode().strip('\x00') :
									element["subsribers"].append(s)
									break
						s.send("ACK".encode())