︠f867abe7-9aae-4c63-8782-62c302d5c4d6s︠
#1 Shamir titokmegosztással az 5 titkot osszuk szét 4 ember között, ahol legalább 3 ember legyen szükséges az eredeti titok előállításához. Az 1, 3, 4 kezdetű titokrészletekből állítsuk elő az eredeti titkot Lagrange-interpolációval.

︡8cc36ac2-4939-4ccb-b8b1-49145da85417︡{"done":true}
︠3a4121fd-3e1f-4309-a992-df98267bcb3ds︠
#2 Írjon SSS_secret_pieces_from_primenum_and_coeffs(num_of_people, secret, primenum, coeff) szignatúrával függvényt, amely a secret titkokból num_of_people db titokrészletet készít, ahol a használt prímszám primenum legyen, coeff pedig tartalmazza a polinom szabad tagján kívüli együtthatókat.

def SSS_secret_pieces_from_primenum_and_coeffs(num_of_people, secret, primenum, coeff) :
    list_of_coeff = coeff + [secret]
    list_of_coeff.reverse()
    poly = GF(primenum)['x'](list_of_coeff)
    result = []
    for i in [1..num_of_people]:
        result.append((i,poly(i)))
    return result

SSS_secret_pieces_from_primenum_and_coeffs(4 ,5 ,7 , [4,3])
SSS_secret_pieces_from_primenum_and_coeffs(10 , 8 , 11 , [1,2,3,4,5])

︡95f0e5c5-5415-45a0-beac-d804070bfbb4︡{"stdout":"[(1, 5), (2, 6), (3, 1), (4, 4)]\n"}︡{"stdout":"[(1, 1), (2, 1), (3, 6), (4, 5), (5, 10), (6, 0), (7, 8), (8, 10), (9, 1), (10, 5)]\n"}︡{"done":true}
︠b7d493e9-91ab-4171-a576-ad7acf38f511s︠
#3 Írjon SSS_compute_secret(primenum, secret_pieces) szignatúrával függvényt, amely a secret_pieces titokrészletekből előállítja a titkot, a használt prímszám pedig primenum.

def SSS_compute_secret(primenum, secret_pieces):
    return GF(primenum)['x'].lagrange_polynomial(secret_pieces)(0)

SSS_compute_secret(7,[(1, 5), (2, 6), (3,1)])
SSS_compute_secret(11,[(1, 1), (2, 1), (3, 6), (4, 5), (5, 10), (6, 0), (7, 8)])

︡32c22c00-6c55-4525-a91b-9b7402437171︡{"stdout":"5\n"}︡{"stdout":"8\n"}︡{"done":true}
︠d09a0607-a907-4538-b36b-2c3e64f9a9dfs︠

#4Írjon get_possible_secrets(primenum) szignatúrával függvényt, amely egy Shamir titokmegosztáskor használt prímszámot kap paraméterként, és visszatér a lehetséges titok listájával.

def get_possible_secrets(primenum):
    return [previous_prime(primenum)..primenum-1]

get_possible_secrets(7)
︡76a292e2-fa53-4f2f-a111-b7bc191b485f︡{"stdout":"[5, 6]\n"}︡{"done":true}









