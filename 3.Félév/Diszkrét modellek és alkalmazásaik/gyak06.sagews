︠d8ec76d8-9820-428e-8506-0930b6cae60f︠

# fsor05

a = ZZ(5)
b = ZZ(6)

a+b

︡02b668ba-50a4-4362-b9d8-a6e95ee9324c︡{"stdout":"11\n"}︡{"done":true}
︠2308046d-2d39-4dac-be67-09588f5fec62︠

# fel 2
# Végezze el az 5 + 4 és 5 / 4 műveleteket Z7-ben.

# mod m: GF(m)

a = GF(7)(5)
b = GF(7)(4)
a
b
a+b
a/b

︡fa7e7214-247b-4f53-ba55-a2ec24749a43︡{"stdout":"5\n"}︡{"stdout":"4\n"}︡{"stdout":"2\n"}︡{"stdout":"3\n"}︡{"done":true}
︠8c1d0f88-d29b-4ccb-a3ba-1d5e86bd7615︠

a = GF(6)(5)

︡3b3bf0f6-1164-42db-b25a-b41abde03e93︡{"stderr":"Error in lines 1-1\nTraceback (most recent call last):\n  File \"/cocalc/lib/python3.9/site-packages/smc_sagews/sage_server.py\", line 1230, in execute\n    exec(\n  File \"\", line 1, in <module>\n  File \"sage/structure/factory.pyx\", line 367, in sage.structure.factory.UniqueFactory.__call__ (build/cythonized/sage/structure/factory.c:2179)\n    key, kwds = self.create_key_and_extra_args(*args, **kwds)\n  File \"/ext/sage/9.4/local/lib/python3.9/site-packages/sage/rings/finite_rings/finite_field_constructor.py\", line 583, in create_key_and_extra_args\n    raise ValueError(\"the order of a finite field must be a prime power\")\nValueError: the order of a finite field must be a prime power\n"}︡{"done":true}
︠39bf8c9d-8112-43d8-be91-96c9bfc8fa07︠

p = ZZ['x'](3*x+1)
p

︡a0dca7af-acb4-43de-92bd-8c4ee860cd30︡{"stdout":"3*x + 1\n"}︡{"done":true}
︠f0d0c257-3e2a-4abe-a1e7-3fc5f1afae82︠

# fel 3
# Legyenek p(x) = 5x^4 + x^3 + 4x^2 + 6 és q(x) = 5x^2 + 1 Z7 feletti polinomok. Számítsa ki p 0, 2 és 1000 helyen vett helyettesítési értékét. Mennyi lesz p + q?

p = GF(7)['x'](5*x^4 + x^3 + 4*x^2 + 6)
q = GF(7)['x'](5*x^2 + 1)
p(0)
p(2)
p(1000)
p+q

︡df8af3b3-8587-4358-9a7d-0bc47500009f︡{"stdout":"6\n"}︡{"stdout":"5\n"}︡{"stdout":"0\n"}︡{"stdout":"5*x^4 + x^3 + 2*x^2\n"}︡{"done":true}
︠9435752a-40af-4041-8069-4bd465914d7b︠

# interpoláció

# fel 4
# Interpoláljon 3-adfokú valós együtthatós polinomot, amely átmegy a (0, 3), (1, 3), (4, 7), (-1, 0) pontokon.

p = RR['x'].lagrange_polynomial([(0, 3), (1, 3), (4, 7), (-1, 0)])
p
p(0)
p(4)

︡87714267-7066-4e5e-9dfd-11da0c57efd0︡{"stdout":"0.366666666666667*x^3 - 1.50000000000000*x^2 + 1.13333333333333*x + 3.00000000000000\n"}︡{"stdout":"3.00000000000000\n"}︡{"stdout":"7.00000000000000\n"}︡{"done":true}
︠cdcc1b4d-48f0-4499-b094-53d0b3181732︠

# fel 5
# Illesszünk Z5-beli interpolációs polinomot a (2, 4), (1, 0), (4, 3) pontokra.

p = GF(5)['x'].lagrange_polynomial([(2, 4), (1, 0), (4, 3)])
p
p(2)
p(1)
p(4)

︡e1922529-0f87-4107-8bc6-4ce7399b8013︡{"stdout":"x^2 + x + 3\n"}︡{"stdout":"4\n"}︡{"stdout":"0\n"}︡{"stdout":"3\n"}︡{"done":true}
︠d84d4d02-ff83-4141-ac54-1d71e6657294︠

# fel 6
# Állítsa elő azt a valós együtthatós polinomot, amely átmegy a (1, 2), (-3, 6), (4, 6), (7, 9), (13, -4), (56, 52) pontokon.

p = RR['x'].lagrange_polynomial([(1, 2), (-3, 6), (4, 6), (7, 9), (13, -4), (56, 52)])
p

︡6895b509-baa7-4eb5-80f5-681bd6475189︡{"stdout":"-4.92598324448841e-6*x^5 + 0.000995717310391090*x^4 - 0.0474661179893386*x^3 + 0.413526848985924*x^2 + 0.179531355082953*x + 1.45341712259332\n"}︡{"done":true}
︠9ee6c039-2f6c-4147-acbc-62afd6ae650b︠

# fel 7
# Állítsa elő azt a Z13 együtthatós polinomot, amely átmegy a (3, 5), (7, 1), (5, 8) pontokon.

p = GF(13)['x'].lagrange_polynomial([(3, 5), (7, 1), (5, 8)])
p

︡8a5d8291-fed0-426f-8720-547c2bc77f83︡{"stdout":"2*x^2 + 5*x + 11\n"}︡{"done":true}
︠9214ffa2-2667-4057-bd7a-861196f1ade5︠



