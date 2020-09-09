#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int *top;
int MAX_SIZE = 5;
int *start;
int * create(){
  int arr[MAX_SIZE];
  start = arr;
  top = arr;
  return top;
}

bool push(int element){
  if(top-start < MAX_SIZE){
    *top = element;
    top++;
    return true;
  }
  return false;
}

int pop(){
  if(top > start)
    return *--top;
  return -1;
}

int count(){
  return top-start;
}

void print(){
  int *temp = top;
  while(temp-->start)
    printf("element: %d\n",*temp);
}

int main(){
   int *stack = create();
   push(5);
   push(10);
   push(15);
   push(20);
   push(25);
   push(30); // extra element
   pop();
   push(30);
   print();
   printf("Total elements: %d\n",count());
   printf("Element: %d\n",pop());
   printf("Element: %d\n",pop());
   printf("Total elements: %d\n",count());
   printf("Element: %d\n",pop());
   printf("Element: %d\n",pop());
   printf("Total elements: %d\n",count());
   printf("Element: %d\n",pop());
   printf("Element: %d\n",pop()); //extra pop
   printf("Total elements: %d\n",count());
   push(30);
   printf("Element: %d\n",pop()); //extra pop
}
