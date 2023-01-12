import socket
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # ipv4, TCP

server_addr = ('', int(sys.argv[1]))	# ('localhost',10000) <- ez csak localba megy

sock.bind(server_addr)
sock.listen(1)
sock.settimeout(1.0)	# windowson kell

while True:
	try:
		print("Varunk...")
		client_socket, client_addr = sock.accept()
		
		print("Csatlakozott: ", client_addr)
		
		data = client_socket.recv(16)
		print("Kaptam:",data.decode())
		if data:
			client_socket.send("Hello Client!".encode())
		else:
			print("Kilepett")
		
		client_socket.close()
		
	
	except socket.timeout:
		pass
	except KeyboardInterrupt:
		sock.close()