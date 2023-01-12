c37af45d-027b-409e-a63d-69fdbe80bc75s︠

# fsor7

# 2. feladat

def caesar_encrypt(plain, shift) :
    str = ""
    for ch in plain :
        code = ord(ch)+shift
        str = str + (chr(code))
    return str

def caesar_decrypt(cipher, shift):
    str = ""
    for ch in cipher :
        code = ord(ch)-shift
        str = str + (chr(code))
    return str

cipher = caesar_encrypt("árvı́ztűrő tükörfúrógép", 16)
print(cipher)
caesar_decrypt(cipher, 16)

︡1671ad09-d7af-4a18-b590-8a719f37ad5a︡{"stdout":"ñŁ̑Ɓš0Č{ĆvĊăwù\n"}︡{"stdout":"'árvı́ztűrő tükörfúrógép'\n"}︡{"done":true}
︠ef34a827-fed6-4206-b325-9cab31b66847s︠

# 3. feladat

def RSA_encrypt(plain, public_key):
    #plaintext -> 256-os számrendszerbeli szám
    plaintext = list(plain)
    plaintext.reverse()
    m = 0
    i = 0
    for ch in plaintext:
        m += ord(ch) * 256^i
        i += 1
    #return m^public_key[1] % public_key[0]
    return power_mod(m, public_key[1], public_key[0])

RSA_encrypt("This is my dummy string", (10577795823851879016615591732095183350836585707548354280149683226062077461, 425771351))

︡0477f29d-d2ca-4f22-a078-f6c9d5a83863︡{"stdout":"6748849292753128600630676460412715382604897001081279527883591019946624088\n"}︡{"done":true}
︠740f980b-fbf5-43ab-9c9b-00c62c0b9f42s︠

def RSA_decrypt(cipher, private_key, modulus):
    m = power_mod(cipher, private_key, modulus)
    plain_list = []
    while m > 0:
        plain_list.append(chr(m % 256))
        m //= 256
    plain_list.reverse()
    return "".join(plain_list)


RSA_decrypt(6748849292753128600630676460412715382604897001081279527883591019946624088, 3619046467562554196577511756786147781029176477176585617426048706025604583, 10577795823851879016615591732095183350836585707548354280149683226062077461)

︡370043bc-3a50-4382-9ace-68a31fdf1e91︡{"stdout":"'This is my dummy string'\n"}︡{"done":true}
︠9ea1ba74-42d3-423e-b1c4-9a9bf5bcba11







