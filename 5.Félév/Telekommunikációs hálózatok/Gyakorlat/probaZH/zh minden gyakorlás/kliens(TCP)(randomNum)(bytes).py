from socket import socket, AF_INET, SOCK_STREAM
import random


server_addr = ('localhost',5050)
numbers = ''
with socket(AF_INET, SOCK_STREAM) as client:
    client.connect(server_addr)
    for i in range(5):
        numbers += str(random.randint(1,20)) + ':'
    numbers+= '400'    
    client.sendall(numbers.encode())
    data = client.recv(200).decode()

    print('Nyertem:',data)