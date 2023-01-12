from socket import socket, AF_INET, SOCK_STREAM
import struct
import sys

server_addr = ('localhost',5051)
numbers = []
counter = 0
with socket(AF_INET, SOCK_STREAM) as client:
    for line in sys.stdin:
        if counter == 5:
            break
        print(f'Processing Message from sys.stdin *****{line}*****')
        if int(line)>0 and int(line)<=20:
            numbers.append(int(line))
            counter+=1

    numbers.append(400)
    client.connect(server_addr)
    client.sendall(struct.pack("i i i i i i",numbers[0],numbers[1],numbers[2],numbers[3],numbers[4],numbers[5]))
    print(client.recv(200).decode())


