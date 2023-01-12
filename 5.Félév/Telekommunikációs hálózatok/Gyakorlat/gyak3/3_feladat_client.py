from socket import socket, AF_INET,SOCK_STREAM, SOCK_DGRAM, timeout, error
import sys

test_host, test_port_number = sys.argv[1], int(sys.argv[2])

mukodo = False
'''
c1.connect
c2.connect

	Nem mukodo:
		c2.send
		c2.recv
		c1.send
		
	Mukodo:
		c1.send
		c2.send
		c2.recv
	
c1.recv
'''

client1 = socket(AF_INET, SOCK_STREAM)
client1.settimeout(0.5)
client1.connect((test_host,test_port_number))

client2 = socket(AF_INET, SOCK_STREAM)
client2.settimeout(0.5)
client2.connect((test_host,test_port_number))


client3 = socket(AF_INET, SOCK_STREAM)
client3.settimeout(0.5)

client3.connect((test_host,test_port_number))

# itt nem mkodik
if not mukodo:
	client2.send('Hello Server'.encode())
	d2 = client2.recv(200)
	d2 = d2.decode()


client1.send("Hello Server".encode())

#itt mukodik
if mukodo:
	client2.send("Hello Server".encode())
	d2 = client2.recv(200)
	d2 = d2.decode()


d1 = client1.recv(200)
client3.send("Hello Server".encode())
d1 = d1.decode()
d3 = client3.recv(200)
d3 = d3.decode()


client1.close()
client2.close()
client3.close()

print(d1,d2,d3)