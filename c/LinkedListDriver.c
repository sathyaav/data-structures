#include<stdio.h>
#include<stdlib.h>
#include "LinkedList.h"

void addElement(llist* l, int v){
  void* data = &v;
  printf("Adding %d \n",v);
  llist_add(l, data);
}

void findElement(llist* l, int v){
  if(llist_find(l, &v) == 1)
        printf("\nfound\n" );
  else
        printf("\n Not Found\n");

}

int main(){
  llist* myList = llist_create();
  addElement(myList,1);
  addElement(myList,2);
  addElement(myList,3);
  addElement(myList,4);
  addElement(myList,50000000);

  printf("size: %d\n", myList->size);
  llist_print(myList,INTEGER);

  llist_remove(myList, 1);
  llist_print(myList,INTEGER);

  llist_remove(myList,3);
  llist_print(myList,INTEGER);

  llist_remove(myList, llist_getSize(myList));
  llist_print(myList,INTEGER);


  addElement(myList,50000000);
  llist_print(myList, INTEGER);

  findElement(myList, 1);
  findElement(myList, 2);
  findElement(myList, 50000000);
  llist_free(myList);
  return 0;
}
