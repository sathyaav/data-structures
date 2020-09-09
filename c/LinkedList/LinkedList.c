#include "LinkedList.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int llist_get_size(llist *list){
  if(list != NULL )
     return list -> size;
}

llist* llist_create(int dataSize){
   llist* list = malloc(sizeof(llist));
   if(list == NULL)
      return NULL;
   list->size = 0;
   list->head = NULL;
   list->tail = NULL;
   list->dataSize = dataSize;
   return list;
}

int llist_add(llist *list, void *data){
  node* newnode = malloc(sizeof(node));
  if(newnode == NULL|| list == NULL)
     return -1;
  //void* newData;
  //memcpy(&newData , data, sizeof(data));
  newnode->data = data;
  newnode->next = NULL;
  if(list->head == NULL){
    list->head = newnode;
    list->tail = newnode;
  }else{
    list->tail->next = newnode;
    list->tail = newnode;
  }
  list->size += 1;
  return 0; //success
}

void llist_free(llist *list, void (*free_data_fptr)(void *)){
  if(list == NULL)
    return;
  node* currentNode = list->head;
  while(currentNode != NULL){
    node* nextNode = currentNode->next;
    free_data_fptr(currentNode->data);
    free(currentNode);
    currentNode = nextNode;
    list->size -= 1;
  }
  free(list);
}


void llist_print(llist *list, void (* print_fptr)(void *data)){
  if(list == NULL)
    return;

  printf("\n Printing values: " );
  node *currentNode = list->head;
  while(currentNode != NULL){
    print_fptr(currentNode->data);
    currentNode = currentNode->next;
  }
}

int llist_remove_first(llist *list , void (* free_data_fptr)(void * data)){
  return llist_remove(list, 1, free_data_fptr);
}


int llist_remove_last(llist *list , void (* free_data_fptr)(void * data)){
  return llist_remove(list, list->size, free_data_fptr);
}

int llist_remove(llist *list, int index, void (* free_data_fptr)(void * data)){
  index -= 1;
  if(list == NULL)
    return -1;
  if(index > list->size)
    return -1;

  node* currentNode = list->head;
  node* previousNode = NULL;
  node* nextNode = NULL;

  if(index == 0){
      list->head = list->head->next;
  }else{
      for(int i=0; i< index; i++){
        previousNode = currentNode;
        currentNode = currentNode->next;
        nextNode = currentNode->next;
      }
  }

  if(previousNode!= NULL)
    previousNode->next = nextNode;

  if(index ==  llist_get_size(list)-1)
    list->tail = previousNode;

  free_data_fptr(currentNode);
  list->size -= 1;
  return 0;
}


int llist_find(llist *list, void* key, int (* equals_fptr)(void *data, void *key) ){

   if(list == NULL)
      return -1;

   node * currentNode = list -> head;
   for(int i=0 ; i < llist_get_size(list) ; i++){
     if(equals_fptr(currentNode->data, key) == 0  ){
       return 1;
     }
     currentNode = currentNode->next;
   }

   return 0;
}



void *llist_get(llist *list, int index){
  if(list == NULL || index > llist_get_size(list) || index <= 0 )
     return NULL;

  node * currentNode = list->head;
  for(int i=1 ; i < index ; i++){
    currentNode = currentNode->next;
  }
  return currentNode->data;

}

void llist_sort(llist *list, int (* compare_to_fptr)(void * data1, void * data2)){

  if(list == NULL )
     return;

  node * currentNode = list->head;
  for(int i=0 ; i < llist_get_size(list)-1 ; i++){
    if(compare_to_fptr(currentNode->data, currentNode->next->data) == 1){
      printf("comparing %d\n",i );
      node * temp = currentNode;
      currentNode = currentNode->next;
      currentNode->next= temp;
      if(i==0)
        list->head = currentNode;
      if(i == llist_get_size(list)-1)
        list -> tail = temp;
    }
    currentNode = currentNode->next;
  }
  return;

}
