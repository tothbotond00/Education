import socket
import struct

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_addr = ('', 10000)

sock.bind(server_addr)
sock.listen(1)
sock.settimeout(1.0)

packer = struct.Struct('ici')

while True:
	try:
		print("Varunk...")
		client_socket, client_addr = sock.accept()
		
        ###########################################
		print("Csatlakozott: ", client_addr)
		
		data = client_socket.recv(packer.size)
		data = packer.unpack(data)
		if data:
			a = data[0]
			operand = data[1].decode()
			b = data[2]
			if operand == '+' :
				res = a + b
			elif operand == '-':
				res = a - b
			elif operand == '/':
				res = a / b
			elif operand == '*':
				res = a * b
			elif operand == '-':
				res = 0
			
			client_socket.send(struct.pack('!i', res))
		else:
			print("Kilepett")
		###############################################
  
		client_socket.close()
		
	
	except socket.timeout:
		pass
	except KeyboardInterrupt:
		sock.close()