import binascii
import socket
import struct
import sys

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

server_address = ('localhost', 11000)
sock.bind(server_address)
sock.settimeout(1.0)

unpacker = struct.Struct('I I 1s')

while True:

	try:
		for i in range(5):
			try:
				data, client = sock.recvfrom(unpacker.size)

				unpacked_data = unpacker.unpack(data)
				print ('Kaptam: ', unpacked_data)
				x = eval(str(unpacked_data[0])+str(unpacked_data[2].decode())+str(unpacked_data[1]))
				
				sock.sendto(str(x).encode(), client)
					
			except socket.timeout:
				pass
			except socket.error as msg:		# socket.error,msg: python2
				print(msg)
	except socket.timeout:
		pass
sock.close()		