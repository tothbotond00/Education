import numbers
from socket import socket, AF_INET, SOCK_DGRAM,SOCK_STREAM
import struct
import json
server_addr = ('localhost',5051)
proxy_addr = ('localhost',9000)
serv_addr = ('localhost', 9020)
listObj = []


with open('win.json') as fp:
  listObj = json.load(fp)


with socket(AF_INET, SOCK_STREAM) as proxyclient, socket(AF_INET, SOCK_DGRAM) as proxyServer, open('win.json', 'w') as json_file:
    
    proxyclient.bind(server_addr)
    proxyclient.listen(1)
    proxyServer.bind(proxy_addr)

    client,_ = proxyclient.accept()
    data = struct.unpack("i i i i i i",client.recv(struct.calcsize("i i i i i i")))
    print(data)
    proxyServer.sendto(struct.pack("i i i i i i",data[0],data[1],data[2],data[3],data[4],data[5]),serv_addr)
    winNumber, ad = proxyServer.recvfrom(struct.calcsize("i i i  i i") + 20)
    print(winNumber,ad)
    winNumbers = struct.unpack("i i i i i",winNumber)
    win, _ = proxyServer.recvfrom(200)
    win = int(win.decode())
    client.sendall(str(win).encode())
    listObj.append({
        "GuessNumber:" : data,
        "WinnerNumber:": winNumbers,
        "win": win
    })
    json.dump(listObj, json_file, 
                    indent=4,  
                    separators=(',',': '))
