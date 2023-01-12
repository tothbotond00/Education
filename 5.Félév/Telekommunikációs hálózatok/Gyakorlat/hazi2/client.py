import struct
import sys

#1_Feladat
packer_1 = struct.Struct('f ? c')
packer_2 = struct.Struct('c 9s i')
packer_3 = struct.Struct('i ? f')
packer_4 = struct.Struct('c f 9s')

with open(sys.argv[1], 'rb') as f1:
    f1.seek(0)
    data = f1.read(packer_1.size)
    data = packer_1.unpack(data)
    print(data)
with open(sys.argv[2], 'rb') as f2:
    f2.seek(0)
    data = f2.read(packer_2.size)
    data = packer_2.unpack(data)
    print(data)
with open(sys.argv[3], 'rb') as f3:
    f3.seek(0)
    data = f3.read(packer_3.size)
    data = packer_3.unpack(data)
    print(data)
with open(sys.argv[4], 'rb') as f4:
    f4.seek(0)
    data = f4.read(packer_4.size)
    data = packer_4.unpack(data)
    print(data)

#2_Feladat
packer_5 = struct.Struct('13si?')
packer_6 = struct.Struct('f?c')
packer_7 = struct.Struct('i11sf')
packer_8 = struct.Struct('ci14s')

data_1 = (b"elso", 81, True)
data_2 = (84.5, False, b'X')
data_3 = (72, b"masodik", 91.9)
data_4 = (b'Z', 103, b"harmadik")

packed_data_1 = packer_5.pack(*data_1)
packed_data_2 = packer_6.pack(*data_2)
packed_data_3 = packer_7.pack(*data_3)
packed_data_4 = packer_8.pack(*data_4)

print(packed_data_1)
print(packed_data_2)
print(packed_data_3)
print(packed_data_4)