from socket import socket, AF_INET, SOCK_STREAM
import random

server_addr = ('localhost',5050)
winNumber = [] 
hitNum = 0

with socket(AF_INET, SOCK_STREAM) as server:
    server.bind(server_addr)
    server.listen(1)
    client, client_addr = server.accept()
    print('Kapcsolodott:',client_addr)
    data = client.recv(200).decode()
    numbers = data.split(':')
    for i in range(5):
        winNumber.append(random.randint(1,20))
    for i in winNumber:
        for j in range(5):
            if( i == int(numbers[j])):
                hitNum += 1
    client.sendall(str(int(numbers[5]) * hitNum ).encode())
    client.close()