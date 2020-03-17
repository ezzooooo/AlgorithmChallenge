#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int queen_count;
vector<int> queen(15);

void nqueen(int n, int max_queen) {

  if (n == max_queen) {
    queen_count++;
    return;
  }

  for (int x = 0; x < max_queen; x++) {
    bool can_queen = true;

    for (int i = 0; i < n; i++) {
      if (queen[i] == x || (abs(n - i) == abs(x - queen[i]))) {
        can_queen = false;
        break;
      }
    }

    if (can_queen) {
      queen[n] = x;
      nqueen(n + 1, max_queen);
    }
  }

  return;
}

int main() {

  int n;
  cin >> n;

  nqueen(0, n);

  cout << queen_count << endl;

  return 0;
}