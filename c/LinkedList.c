#include "LinkedList.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void increaseSize(llist *list){
  list -> size += 1;
}

void decreaseSize(llist *list){
  list -> size -= 1;
}


int checkMemAlloc(void* newmem){
   if(newmem == NULL)
      return -1;
   return 0;
}


int llist_getSize(llist *list){
  if(checkMemAlloc(list) != -1 )
     return list -> size;
}


llist* llist_create(){
   llist* list = malloc(sizeof(llist));
   if(checkMemAlloc(list) == -1 )
      return NULL;
   list -> size = 0;
   list -> head = NULL;
   list -> tail = NULL;
   return list;
}

int llist_add(llist *list, void *data){
  node* newnode = malloc(sizeof(node));
  if(checkMemAlloc(newnode) == -1 || checkMemAlloc(list) ==-1)
     return -1;
  void* newData;
  memcpy(&newData , data, sizeof(data));
  newnode -> data = newData;
  newnode -> next = NULL;

  if(list -> head == NULL){
    list -> head = newnode;
    list -> tail = newnode;
  }else{
    list -> tail -> next = newnode;
    list -> tail = newnode;
  }
  increaseSize(list);
  return 0; //success
}

void llist_free(llist *list){
  if(checkMemAlloc(list) !=-1){
    node* currentnode = list->head;
    while(currentnode != NULL){
      node* nextNode = currentnode -> next;
      free(currentnode);
      currentnode = nextNode;
      decreaseSize(list);
    }
    free(list);
  }
}


void llist_print(llist *list, int datatype){
  if(checkMemAlloc(list) != -1){
    printf("\n Printing values: " );
    node *currentNode = list->head;
    while(currentNode != NULL){
      if(datatype == INTEGER)
        printf("%d\t", (int)currentNode -> data );
      currentNode = currentNode -> next;
    }
  }
}

int llist_remove_first(llist *list){
  return llist_remove(list, 1);
}


int llist_remove_last(llist *list){
  return llist_remove(list, list->size);
}

int llist_remove(llist *list, int index){
  index -= 1;
  if(checkMemAlloc(list) == -1)
    return -1;
  if(index > llist_getSize(list))
    return -1;

  node* currentNode = list -> head;
  node* previousNode = NULL;
  node* nextNode = NULL;

  if(index == 0){
      list -> head = list -> head -> next;
  }else{
      for(int i=0; i< index;i++){
        previousNode = currentNode;
        currentNode = currentNode -> next;
        nextNode = currentNode->next;
      }

  }

  if(previousNode!= NULL)
    previousNode -> next = nextNode;

  if(index ==  llist_getSize(list)-1)
    list -> tail = previousNode;

  free(currentNode);
  decreaseSize(list);
  return 0;
}

int llist_find(llist *list, void* data ){

   if(checkMemAlloc(list) == -1)
      return -1;

   node * currentNode = list -> head;
   for(int i=0 ; i < llist_getSize(list) ; i++){
     void * nodeData = currentNode -> data;
     if(memcmp(&nodeData, data, sizeof(data)) == 0  ){
       return 1;
     }
     currentNode = currentNode->next;
   }

   return 0;
}
