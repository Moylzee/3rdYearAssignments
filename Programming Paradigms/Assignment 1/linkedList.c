#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"

typedef struct listElementStruct{
  char* data;
  size_t size;
  struct listElementStruct* next;
} listElement;

//Creates a new linked list element with given content of size
//Returns a pointer to the element
listElement* createEl(char* data, size_t size){
  listElement* e = malloc(sizeof(listElement));
  if(e == NULL){
    //malloc has had an error
    return NULL; //return NULL to indicate an error.
  }
  char* dataPointer = malloc(sizeof(char)*size);
  if(dataPointer == NULL){
    //malloc has had an error
    free(e); //release the previously allocated memory
    return NULL; //return NULL to indicate an error.
  }
  strcpy(dataPointer, data);
  e->data = dataPointer;
  e->size = size;
  e->next = NULL;
  return e;
}

//Prints out each element in the list
void traverse(listElement* start){
  listElement* current = start;
  while(current != NULL){
    printf("%s\n", current->data);
    current = current->next;
  }
}

//Inserts a new element after the given el
//Returns the pointer to the new element
listElement* insertAfter(listElement* el, char* data, size_t size){
  listElement* newEl = createEl(data, size);
  listElement* next = el->next;
  newEl->next = next;
  el->next = newEl;
  return newEl;
}


//Delete the element after the given el
void deleteAfter(listElement* after){
  listElement* delete = after->next;
  listElement* newNext = delete->next;
  after->next = newNext;
  //need to free the memory because we used malloc
  free(delete->data);
  free(delete);
}

// Question 2

// Returns the number of elements in a linked list
int length(listElement* list) {
    // Counter for number of elements
    int numElements = 0;
    listElement* current = list;
    while (current != NULL) {
        // increment counter
        numElements++;
        current = current->next;
    }
    printf("Number of elements in linked list is: %d", numElements);
    return numElements;
}

// Push a new element onto the head of a list
// Update the list reference using side effects
void push(listElement** list, char* data, size_t size) {
    // Create new element
    listElement* new = createEl(data, size);
    // update the list to point to the new head
    new->next = *list;
    *list = new;
    printf("New Element Added to List");
}

// Pop an element from the head of the list
// Update the list reference using side effects
listElement* pop(listElement** list) {
// Check if the list is empty and return NULL if it is
    if (*list == NULL) {
        return NULL;
    } else {
        // If the list is not empty it gets the current head of the list
//      // It then removes the element from the list and updates the list reference
        listElement *currentHead = *list;
        *list = currentHead->next;
        currentHead->next = NULL;
        // return the element popped
        return currentHead;
    }
}

// Enqueue a new element onto the head of the list.
// Update the list reference using side effects
void enqueue(listElement** list, char* data, size_t size) {
    listElement* new = createEl(data, size);

    // If the list is empty the new element is added as the head and the tail
    if (*list == NULL ) {
        *list = new;
    } else if (*list != NULL) {
        // If then list is not empty, add the new element to the head and update the list references
        new->next = *list;
        *list = new;
    }
}

// Dequeue an element from the tail of the list
listElement* dequeue(listElement **list) {
    // Check if the list is empty and return NULL if it is
    if (*list == NULL ) {
        return NULL;
    }

    // Use temp and cur variables to find the tail of the list
    // Temp is also used for the element before the tail element
    listElement* temp = NULL;
    listElement* cur = *list;
    // iterate through linked list until it reaches the end
    while (cur->next != NULL) {
        // Keep track of current and previous element
        temp = cur;
        cur = cur->next;
    }
    // Set the final element to null, De-queueing it
    // set Temp as the new tail of the list
    temp->next = NULL;
    // Return the tail element
    return cur;
}