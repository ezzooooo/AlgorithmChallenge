#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

long long fac(int n) {
  if (n < 1) return 1;

  return n * fac(n - 1);
}

int main() {

  int n;
  long long num;

  cin >> n;

  vector<int> nums(n);

  for (int i = 0; i < n; i++) {
    nums[i] = i + 1;
  }

  cin >> num;

  if (num == 1) {
    cin >> num;
    num--;

    vector<int> answer;
    int idx;

    for (int i = 0; i < n - 1; i++) {
      idx = num / fac(n - 1 - i);
      answer.push_back(nums[idx]);
      nums.erase(nums.begin() + idx);
      num = num % fac(n - 1 - i);
    }

    answer.push_back(nums[0]);

    for (int i = 0; i < n; i++) {
      cout << answer[i] << " ";
    }
  }
  else
  {
    long long answer = 1;

    vector<int> numbers(n);

    for (int i = 0; i < n; i++) {
      cin >> numbers[i];
    }

    for (int i = 0; i < n - 1; i++) {
      int j = 0;
      while (j < n) {
        if (numbers[i] == nums[j]) {
          nums.erase(nums.begin() + j);
          answer = answer + (j * fac(n - i - 1));
          break;
        }
        j++;
      }
    }
    cout << answer << endl;
  }

  return 0;
}