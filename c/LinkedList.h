struct List{
  struct Node *head;
  struct Node *tail;
  int size;

};

typedef struct List llist;

struct Node {
    void *data;
    struct Node *next;
};

typedef struct Node node;

enum ListDatatype {INTEGER, CHAR, FLOAT};

/* llist_create: Create a linked list */
llist *llist_create();

/* llist_free: Free a linked list */
void llist_free(llist *list);

/* llist_push: Add to tail of list */
int llist_add(llist *list, void *data);

/* reverse a linked list and returns a instance of it*/
llist *llist_reverse(llist *list);

/* sort a linked list and returns a instance of it*/
llist *llist_sort(llist *list);

/* gets the value of the data at index , returns NULL if no data exists */
void *llist_get(llist *list, int index);

/* returns the size of list */
int llist_getSize(llist *list);

/* remove data at location 'index' and returns the data , NULL if there is no data at specified location*/;
int llist_remove(llist *list, int index);

/* print all values in list */
void llist_print(llist *list, int datatype);

/* remove first element */
int llist_remove_first(llist *list);

/* remove last element */
int llist_remove_last(llist *list);

/* returns 1 if element is found or 0 otherwise */
int llist_find(llist *list, void* data );
