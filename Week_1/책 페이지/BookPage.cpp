#include <iostream>
#include <vector>
#include <string>
#include <math.h>

using namespace std;

vector<int> answer(10);

void book_page(string str_n) {
  int str_len = str_n.length(); // 숫자의 길이 : 7

  int first_num = str_n[0] - '0'; // 1

  int start_num = first_num * pow(10, str_len - 1);         // 1000000
  int end_num = stoi(str_n);                                // 1234567 이 둘 사이의 값만 계산

  int count_num = (str_len - 1) * pow(10, str_len - 2); // 600000

  for (int i = 0; i < 10; i++) {
    answer[i] += first_num * count_num;                 // 600000
  }

  for (int i = 0; i < str_len - 1; i++) {
    answer[0] -= pow(10, i);                            // 600000 - 111111
  }

  for (int i = 1; i < first_num; i++) {
    answer[i] += pow(10, str_len - 1);
  }

  if (end_num - start_num > 100) {
    string new_num = to_string(end_num - start_num);    // 234567

    answer[first_num] += end_num - start_num + 1;
    answer[0] += str_len - 1;

    for (int i = 2; i < new_num.length() + 1; i++) {
      answer[0] += 9 * (str_len - i) * pow(10, i - 2);
      start_num += 9 * pow(10, i - 2);                // 1099999
    }

    answer[0] += (str_len - new_num.length() - 1) * (end_num - start_num);

    start_num = first_num * pow(10, str_len - 1);     // 1000000

    book_page(to_string(end_num - start_num));
  }

  else {
    for (int i = start_num; i <= end_num; i++) {
      string str_num = to_string(i);
      for (int j = 0; j < str_num.length(); j++) {
        answer[str_num[j] - '0']++;
      }
    }
  }
}

int main() {

  int n;
  string str_n;
  std::cin >> n;            // 입력받은 숫자 예) 1234567

  str_n = to_string(n);

  book_page(str_n);

  for (int i = 0; i < 10; i++) {
    cout << answer[i] << " ";
  }
  cout << endl;

  return 0;

}



