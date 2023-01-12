// s->A
// H->T
// f->g

// kevés memória
char conv( char ch)
{
    switch (ch)
    {
        case 's': return 'A'; break;
        case 'H': return 'T'; break;
        case 'g': return 'g'; break;
        default : return ch;
    }
}