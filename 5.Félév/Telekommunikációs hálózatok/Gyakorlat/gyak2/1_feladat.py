from logging import exception
import socket
import struct

for i in range(100):
    try:
        print(i,socket.getservbyport(i))
    except:
        pass

packer = struct.Struct('20si')

with open('domainPort.bin', 'rb') as f:
    f.seek(0)
    data = f.read(packer.size)
    while data :
        data = packer.unpack(data)
        domain = data[0]
        port = data[1]
        domain = domain.decode().strip('\x00') 
        print(domain,socket.gethostbyname(domain),port,socket.getservbyport(port))
        data = f.read(packer.size)
