double sum(int n, double (*a)(int))
{
    double ret = 0.;
    for (int i = 0; i <= n; ++i)
    {
        ret += a(i);
    }
    return ret;
}