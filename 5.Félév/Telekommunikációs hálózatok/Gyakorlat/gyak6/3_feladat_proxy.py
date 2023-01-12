from socket import socket, AF_INET, SOCK_STREAM
from select import select

proxy_addr = ('localhost', 9000)
server_addr = ('ggombos.web.elte.hu',80)

with socket(AF_INET, SOCK_STREAM) as server :
	inputs = [server]
		
	server.bind(proxy_addr)
	server.listen(10)
	
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
				data = s.recv(65000)
				if not data:
					print("Kilepett: ", s)
					inputs.remove(s)
					s.close()
				else :
					if "SzamHalo" in data.decode():
						header = "HTTP/1.1 404 NOT FOUND"
						body = "<html><body><h1>PROBa</h1></body></html>"
						s.sendall((header + body).encode())
					else:
						with socket(AF_INET, SOCK_STREAM) as proxy_client:
							proxy_client.connect(server_addr)
							
							data = data.decode().replace("localhost:9000", server_addr[0]+ ":" + str(server_addr[1])).encode()
							proxy_client.sendall(data)
							recv_data = proxy_client.recv(65000)
							s.sendall(recv_data)