#include <stdio.h>
#include "linkedList.h"
#include "tests.c"
#include "genericTests.c"

int main(int arg, char* argc[]){
    runGenericTests();
    runTests();
    return 0;
}