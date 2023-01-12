#include <cstdio>
#include <cstdlib>

struct Node
{
    int val;
    Node *left, *right;
};

void print(const Node* n)
{
    if(n)
    {
        print(n->left);
        std::printf("%d ",n->val );
        print(n->right);
    }
}

void insert(Node** n, int v)
{
    if(*n)
    {
        insert(v <= (*n)->val ? &(*n)->left : &(*n)->right, v);
    }
    else
    {
        *n = (Node*)std::malloc(sizeof(Node));
        (*n)->val = v;
        (*n)->left = (*n)->right = 0;
    }
}

void dealloc(Node* n)
{
    if(n)
    {
        dealloc(n->left);
        dealloc(n->right);
        std::free(n);
    }
}

int main()
{
    int i;
    Node* root = 0;
    while(-1 != std::scanf("%d", &i))
    {
        insert(&root, i);
    }
    print(root);
    std::printf("\n");
    dealloc(root);
}