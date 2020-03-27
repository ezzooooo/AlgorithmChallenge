#include <iostream>
#include <vector>
#include <queue>

using namespace std;

priority_queue<int> max_heap;
priority_queue<int, vector<int>, greater<int>> min_heap;

int main() {

  int n;
  scanf("%d", &n);

  for (int i = 0; i < n; i++) {
    int input;
    scanf("%d", &input);

    if (max_heap.size() == min_heap.size()) {
      if (max_heap.size() == 0 && min_heap.size() == 0) {
        max_heap.push(input);
      }
      else {
        if (min_heap.top() < input) {
          int temp = min_heap.top();
          min_heap.pop();
          min_heap.push(input);
          max_heap.push(temp);
        }
        else {
          max_heap.push(input);
        }
      }
    }
    else {
      if (max_heap.top() > input) {
        int temp = max_heap.top();
        max_heap.pop();
        max_heap.push(input);
        min_heap.push(temp);
      }
      else {
        min_heap.push(input);
      }
    }

    printf("%d\n", max_heap.top());
  }

  return 0;
}