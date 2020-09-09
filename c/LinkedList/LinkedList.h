struct List{
  struct Node *head;
  struct Node *tail;
  int dataSize;
  int size;
};

typedef struct List llist;

struct Node {
    void *data;
    struct Node *next;
};

typedef struct Node node;

/* llist_create: Create a linked list */
llist *llist_create();

/* llist_free: Free a linked list */
void llist_free(llist *list, void (*free_data_fptr)(void *));

/* llist_push: Add to tail of list */
int llist_add(llist *list, void *data);

/* reverse a linked list and returns a instance of it*/
llist *llist_reverse(llist *list);

/* gets the value of the data at index , returns NULL if no data exists */
void *llist_get(llist *list, int index);

/* returns the size of list */
int llist_get_size(llist *list);

/* remove data at location 'index', NULL if there is no data at specified location*/;
int llist_remove(llist *list, int index, void (* free_data_fptr)(void *));

/* print all values in list */
void llist_print(llist *list, void (* print_fptr)(void*));

/* remove first element */
int llist_remove_first(llist *list, void (* free_data_fptr)(void *));

/* remove last element */
int llist_remove_last(llist *list, void (* free_data_fptr)(void *));

/* returns 1 if element is found or 0 otherwise */
int llist_find(llist *list, void* key, int (* equals_fptr)(void *, void *));

/* sort list in place */
void llist_sort(llist *list, int (* compare_to_fptr)(void *, void *));
