import struct

packer_1 = struct.Struct('f ? c')
packer_2 = struct.Struct('c 9s i')
packer_3 = struct.Struct('i ? f')
packer_4 = struct.Struct('c f 9s')

print(struct.pack("f ? c",12.222,False,b'b'))
print(struct.pack("c 9s i",b'1',b"szer",123))
print(struct.pack("i ? f",123,True,23.22131))
print(struct.pack("c f 9s",b'Z',124213.123123,b"kjasn"))

