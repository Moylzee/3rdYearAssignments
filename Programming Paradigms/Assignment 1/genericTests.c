#include <stdio.h>
#include "genericLinkedList.h"

void runGenericTests() {
    printf("Generic Tests running...\n");

    // Create a generic list
    gListElement* l = createElement("First Element Added (1)", 30, &printString);
    printf("Traversing List: ");
    genericTraverse(l);
    printf("\n");

    // Test insert after
    printf("Testing Insert After \n--------------------\n");
    gListElement* l2 = genericInsertAfter(l, "another string (2)", 30, &printString);
    genericInsertAfter(l2, "a final string (3)", 30, &printString);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");

    // Test delete after
    printf("\nTesting Delete After \n--------------------\n");
    genericDeleteAfter(l);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");

    // Test List Length
    printf("\nTesting Length \n--------------------\n");
    genericLength(l);
    printf("\n");

    // Test Push Method
    printf("\nPush Test \n--------------------\n");
    genericPush(&l, "Pushed Element", 30, &printString);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");

    // Test pop Method
    printf("\nPop Test \n--------------------\n");
    genericPop(&l);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");

    // Test Enqueue
    printf("\nEnqueue Test \n--------------------\n");
    genericEnqueue(&l, "Enqueue Test", 30, &printString);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");

    // Test Dequeue
    printf("\nDequeue Test \n--------------------\n");
    genericDequeue(&l);
    printf("Traversing List: ");
    printf("\n");
    genericTraverse(l);
    printf("Traverse Complete");
    printf("\n");
    printf("Tests complete.\n------------------\n");
}