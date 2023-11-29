#include <stdio.h>
#include "tests.h"
#include "linkedList.h"
#include "linkedList.c"
#include "genericLinkedList.c"


void runTests(){
  printf("Tests running...\n");
  listElement* l = createEl("Test String (1).", 30);
  //printf("%s\n%p\n", l->data, l->next);
  //Test create and traverse
  traverse(l);
  printf("\n");

  //Test insert after
  listElement* l2 = insertAfter(l, "another string (2)", 30);
  insertAfter(l2, "a final string (3)", 30);
  traverse(l);
  printf("\n");

  // Test delete after
  deleteAfter(l);
  traverse(l);
  printf("\n");

  // Test List Length
  length(l);
  printf("\n");
  // Test Push Method
  push( l, "Push Test", 30);
  printf("\n");
  // Test pop Method
  pop( l);
  // Test Enqueue
  enqueue(l, "Enqueue Test", 30);
  //Test Dequeue
  dequeue(l);
  printf("\nTests complete.\n");
}