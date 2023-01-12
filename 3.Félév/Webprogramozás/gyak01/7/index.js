function lnko(n,m){
    if (n < m) {
        let t = n;
        n = m;
        m = t;
    }
    let mar = n % m;
    while (mar > 0){
        n = m;
        m = mar;
        mar = n % m;
    }
    return m;
}
console.log(lnko(12,10));