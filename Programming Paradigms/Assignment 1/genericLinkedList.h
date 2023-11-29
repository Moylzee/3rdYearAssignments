#ifndef CT331_ASSIGNMENT_GENERIC_LINKED_LIST
#define CT331_ASSIGNMENT_GENERIC_LINKED_LIST

typedef struct gListElementStruct gListElement;
typedef void (*PrintFunction)(void*);

gListElement* createElement(void* data, size_t size, PrintFunction printFunction);

void genericTraverse(gListElement* start);

gListElement* genericInsertAfter(gListElement* after, void* data, size_t size, PrintFunction printFunction);

void genericDeleteAfter(gListElement* after);

int genericLength(gListElement* list);

void genericPush(gListElement** list, void* data, size_t size, PrintFunction  printFunction);

void* genericPop(gListElement** list);

void genericEnqueue(gListElement** list, void* data, size_t size, PrintFunction printFunction);

void* genericDequeue(gListElement** list);

#endif