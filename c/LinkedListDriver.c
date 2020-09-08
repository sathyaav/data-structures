#include<stdio.h>
#include<stdlib.h>
#include "LinkedList.h"

void addElement(llist* l, int v){
  int* data = malloc(sizeof(int));
  printf("\nAdding %d",v);
  *data = v;
  llist_add(l, data);
}

void print(void * data){
    printf("%d \t", *(int *) data);
}

void free_data(void * data){
  free(data);
}

int equals(void * data, void * key){
  if(*(int *)data == *(int *)key)
    return 0;
  return 1;
}

void find(llist* l, int v){
  if(llist_find(l, &v, &equals) == 1)  // need to check
        printf("\n Found" );
  else
        printf("\n Not Found");
}

int compare_to(void * data1, void * data2){

  if(*(int *) data1 > *(int *) data2)
    return 1;
  else if( *(int *) data1 < *(int *) data2 )
    return -1;
  else
    return 0;

}

int main(){
  llist* myList = llist_create(sizeof(int));
  addElement(myList,1);
  addElement(myList,2);
  addElement(myList,3);
  addElement(myList,4);
  addElement(myList,50000000);

  printf("size: %d\n", myList->size);
  llist_print(myList, &print);

  llist_remove(myList, 1, &free_data);
  llist_print(myList, &print);

  llist_remove(myList,3, &free_data);
  llist_print(myList, &print);

  llist_remove(myList, llist_get_size(myList), &free_data);
  llist_print(myList, &print);


  addElement(myList,50000000);
  llist_print(myList, &print);

  find(myList, 1);
  find(myList, 2);
  find(myList, 50000000);

  int value = *((int*)(llist_get(myList,2)));
  printf("\n value at index %d is %d\n",2,value);

  llist_free(myList, &free_data);
  return 0;
}
