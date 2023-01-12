from socket import socket, AF_INET, SOCK_STREAM, SOCK_DGRAM
import select

proxy_addr = ('localhost',10000)
server_addr = ('localhost',11000)

with socket(AF_INET, SOCK_STREAM) as proxy, socket(AF_INET,SOCK_DGRAM) as calcClient:
	proxy.bind(proxy_addr)
	proxy.listen(5)
	
	inputs = [proxy]
	
	while True:
		r, w, e = select.select(inputs,inputs,inputs,1)
		
		if not (r or w or e):
			continue
		
		for s in r:
			if s is proxy:
				client, client_addr = s.accept()
				print("Kliens csatlakozott ",client_addr)
				inputs.append(client)
			else:
				data = s.recv(9)
				if not data:
					print("Kliens kilepett")
					inputs.remove(s)
					s.close()
				else:
					print("send to server")
					calcClient.sendto(data,server_addr)
					respData, _ = calcClient.recvfrom(200)
					print("Server response")
					s.sendall(respData)
					print("Send back to client")