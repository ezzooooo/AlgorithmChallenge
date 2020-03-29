#include <iostream>
#include <vector>

using namespace std;

#define MAX 100001

vector<int> in_order(MAX);
vector<int> post_order(MAX);

void pre_order(int in_left, int in_right, int post_left, int post_right) {
  if (in_left > in_right || post_left > post_right) {
    return;
  }

  int root = post_order[post_right];
  printf("%d ", root);

  int i;
  for (i = in_left; in_order[i] != root; i++); // in_order에서 루트 인덱스 찾기

  int left = i - in_left;
  int right = in_right - i;

  pre_order(in_left, in_left + left - 1, post_left, post_left + left - 1);
  pre_order(in_right - right + 1, in_right, post_right - right, post_right - 1);
}

int main() {
  int n;
  int num;
  scanf("%d", &n);

  for (int i = 0; i < n; i++) {
    scanf("%d", &num);
    in_order[i] = num;
  }

  for (int i = 0; i < n; i++) {
    scanf("%d", &num);
    post_order[i] = num;
  }

  pre_order(0, n - 1, 0, n - 1);
}