#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <set>

void connect_check(int n, int complex, int x, int y, std::vector<std::vector<int>> &map) {

  if (map[y][x] == 1) {

    map[y][x] = complex;

    if (x + 1 < n) { // 오른쪽
      if (map[y][x + 1] == 1) {
        connect_check(n, complex, x + 1, y, map);
      }
      else if (map[y][x + 1] > 1) {
        map[y][x] = map[y][x + 1];
      }
    }
    if (y + 1 < n) { // 아래쪽
      if (map[y + 1][x] == 1) {
        connect_check(n, complex, x, y + 1, map);
      }
      else if (map[y + 1][x] > 1) {
        map[y][x] = map[y + 1][x];
      }
    }
    if (x > 0) { // 왼쪽
      if (map[y][x - 1] == 1) {
        connect_check(n, complex, x - 1, y, map);
      }
      else if (map[y][x - 1] > 1) {
        map[y][x] = map[y][x - 1];
      }
    }
    if (y > 0) { // 위쪽
      if (map[y - 1][x] == 1) {
        connect_check(n, complex, x, y - 1, map);
      }
      else if (map[y - 1][x] > 1) {
        map[y][x] = map[y - 1][x];
      }
    }

  }

  return;
}

int main(void) {

  std::string line;
  int n;
  int complex = 2;

  std::cin >> n;

  std::vector<std::vector<int>> map(n, std::vector<int>(n));
  std::vector<int> complexes((n + 1)*(n + 1));
  std::set<int> count_complex;

  for (int i = 0; i < n; i++) {
    std::cin >> line;
    for (int j = 0; j < n; j++) {
      map[i][j] = line[j] - '0'; // char '1' 을 int 1로 변환
    }
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (map[i][j] == 1) {
        connect_check(n, complex + i + (j*n), j, i, map);
      }
    }
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (map[i][j] > 0) {
        complexes[map[i][j]]++;
        count_complex.insert(map[i][j]);
      }
    }
  }

  std::sort(complexes.begin(), complexes.end());

  std::cout << count_complex.size() << std::endl;

  for (std::vector<int>::iterator itr = complexes.begin(); itr != complexes.end(); ++itr) {
    if (*itr != 0) {
      std::cout << *itr << std::endl;
    }
  }

  return 0;
}