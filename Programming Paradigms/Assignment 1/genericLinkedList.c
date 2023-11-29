#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "genericLinkedList.h"

typedef struct gListElementStruct{
    void* data;
    size_t size;
    struct gListElementStruct* next;
    PrintFunction printFunction;
} gListElement;

gListElement* createElement(void* data, size_t size, PrintFunction printFunction) {
    gListElement* e = malloc(sizeof(gListElement));
    if (e == NULL) {
        // malloc has had an error
        return NULL; // return NULL to indicate an error.
    }

    e->data = malloc(size);
    if (e->data == NULL) {
        // malloc has had an error
        free(e); // release the previously allocated memory
        return NULL; // return NULL to indicate an error.
    }
    memcpy(e->data, data, size);
    e->size = size;
    e->next = NULL;
    e->printFunction = printFunction;
    return e;
}

void genericTraverse(gListElement* start) {
    gListElement* current = start;
    while (current != NULL) {
        current->printFunction(current->data);
        current = current->next;
    }
}

gListElement* genericInsertAfter(gListElement* after, void* data, size_t size, PrintFunction printFunction) {
    gListElement* newEl = createElement(data, size, printFunction);
    gListElement* next = after->next;
    newEl->next = next;
    after->next = newEl;
    return newEl;
}

void genericDeleteAfter(gListElement* after) {
    gListElement* delete = after->next;
    gListElement* newNext = delete->next;
    after->next = newNext;
    // need to free the memory because we used malloc
    free(delete->data);
    free(delete);
}

int genericLength(gListElement* list) {
    int numElements = 0;
    gListElement* current = list;
    while (current != NULL) {
        numElements++;
        current = current->next;
    }
    printf("Number of elements in linked list is: %d\n", numElements);
    return numElements;
}

void genericPush(gListElement** list, void* data, size_t size, PrintFunction printFunction) {
    gListElement* new = createElement(data, size, printFunction);
    new->next = *list;
    *list = new;
}

void* genericPop(gListElement** list) {
    if (*list == NULL) {
        return NULL;
    } else {
        gListElement* currentHead = *list;
        *list = currentHead->next;
        currentHead->next = NULL;
        return currentHead->data;
    }
}

void genericEnqueue(gListElement** list, void* data, size_t size, PrintFunction printFunction) {
    gListElement* new = createElement(data, size, printFunction);

    if (*list == NULL) {
        *list = new;
    } else if (*list != NULL) {
        new->next = *list;
        *list = new;
    }
}

void* genericDequeue(gListElement** list) {
    if (*list == NULL) {
        return NULL;
    }
    gListElement* temp = NULL;
    gListElement* cur = *list;
    while (cur->next != NULL) {
        temp = cur;
        cur = cur->next;
    }

    temp->next = NULL;
    return cur->data;
}

void printString(void* data) {
    char* str = (char*)data;
    printf("%s\n", str);
}