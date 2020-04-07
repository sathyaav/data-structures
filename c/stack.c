
#include <stdio.h>

/*
  Struct Definition
 */

struct stack
{
   int *stackptr;
   int sz;         /* Data member of a struct */
   int sidx;   /* Index of top of the stack */
};

/*
  0 - success
  -1 = failure
 */
int stack_init(struct stack *sp, int stack_sz)
{
    if (sp == NULL) {
        return -1;
    }

    sp->sz = stack_sz;
    sp->sidx = -1;
    sp->stackptr = malloc(sizeof(int)*stack_sz);
    return 0;
}

void stack_destroy(struct stack *sp)
{
    free(sp->stackptr);
}


int stack_push(struct stack *sp, int val)
{
    if (sp->sidx == sp->sz -1)
       return -1;

    sp->sidx++;
    sp->stackptr[sp->sidx] = val;
    return 0;
}

int stack_pop(struct stack *sp)
{
    if(sp->sidx == -1)
       return -1;
    sp->sidx--;
    return 0;
}

/*
   1 - Empty
   0 - Non empty
 */

int stack_isempty(struct stack *sp)
{
   return (sp->sidx == -1)?1:0;
}


/*
   1 - Full
   0 - Not full
 */
int stack_isfull(struct stack *sp)
{
  return (sp->sidx == sp->sz -1)?1:0;
}

/*
   Returns no. of element in the stack.
   0 - no element
 */
int stack_size(struct stack *sp)
{
  return sp->sidx +1;
}


/*
   Prints the stack elements
 */
int stack_print(struct stack *sp)
{
  for(int i =  sp->sidx; i>=0 ; i--){
    printf("%d\n",sp->stackptr[i] );
  }
}

int stack_peek(struct stack *sp){
  if(stack_isempty(sp) == 1)
    return -1;
  return sp->stackptr[sp->sidx];
}


int debug(struct stack *sp){
  printf("\nMax size : %d\n", sp->sz);
  printf("Index: %d\n", sp->sidx);
  printf("Peek Element: %d\n", stack_peek(sp));
  printf("Elements:\n");
  stack_print(sp);
  printf("Full?: %d\n", stack_isfull(sp));
  printf("Empty?: %d\n", stack_isempty(sp));
  printf("Size: %d\n", stack_size(sp));
}

int main()
{

   struct stack s1;

   stack_init(&s1, 10);

   stack_push(&s1, 5);
   stack_push(&s1, 10);
   stack_push(&s1, 15);
   stack_push(&s1, 20);
   stack_push(&s1, 25);
   stack_push(&s1, 30);
   stack_push(&s1, 35);
   stack_push(&s1, 40);
   stack_push(&s1, 45);
   stack_push(&s1, 50);
   stack_push(&s1, 55);
   //debug(&s1);
   printf("POP:\n");
   stack_pop(&s1);
   //debug(&s1);
   printf("POP:\n");
   stack_pop(&s1);
   //debug(&s1);
   printf("POP all:\n");
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   stack_pop(&s1);
   debug(&s1);

   stack_destroy(&s1);

}
