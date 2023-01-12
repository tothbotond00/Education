#include "noveny.h"

Burgonya* Burgonya::_peldany = nullptr;
Burgonya* Burgonya::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Burgonya();
    }
    return _peldany;
}

Hagyma* Hagyma::_peldany = nullptr;
Hagyma* Hagyma::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Hagyma();
    }
    return _peldany;
}

Borso* Borso::_peldany = nullptr;
Borso* Borso::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Borso();
    }
    return _peldany;
}

Tulipan* Tulipan::_peldany = nullptr;
Tulipan* Tulipan::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Tulipan();
    }
    return _peldany;
}

Szegfu* Szegfu::_peldany = nullptr;
Szegfu* Szegfu::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Szegfu();
    }
    return _peldany;
}

Rozsa* Rozsa::_peldany = nullptr;
Rozsa* Rozsa::peldany()
{
    if(nullptr == _peldany){
        _peldany = new Rozsa();
    }
    return _peldany;
}
