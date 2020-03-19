# BFS로 단지 확인 알고리즘
def bfs(house, i, j, N, visited):
    if house[i][j] == 0:  # 0일 경우 함수를 넘김
        visited.append([i, j])
        return [0, visited]

    block = []  # 함수 안에서만 쓰일 블록
    queue = [[i, j]]  # 함수 안에서만 쓰일 큐

    while queue:
        [i, j] = queue.pop(0)
        block.append([i, j])  # 블록에 쌓아줌
        visited.append([i, j])  # 방문 리스트에 쌓아줌

        if house[i][j] == 1:  # 각각 상하좌우를 확인하는 조건문
            if i < N - 1 and house[i + 1][j] == 1 and [i + 1, j] not in block and [i + 1, j] not in queue:
                queue.append([i + 1, j])
            if j < N - 1 and house[i][j + 1] == 1 and [i, j + 1] not in block and [i, j + 1] not in queue:
                queue.append([i, j + 1])
            if j > 0 and house[i][j - 1] == 1 and [i, j - 1] not in block and [i, j - 1] not in queue:
                queue.append([i, j - 1])
            if i > 0 and house[i - 1][j] == 1 and [i - 1, j] not in block and [i - 1, j] not in queue:
                queue.append([i - 1, j])

    return [len(block), visited]  # 블록의 크기와, 방문한 노드들만 반환


N = int(input())
house = []
visited = []
result = []
for _ in range(N):  # 단지를 2차원 배열로 받음
    house.append(list(map(int, str(input()))))

# 0,0 부터 N,N까지 각각의 노드에 관하여 함수 실행
for i in range(N):
    for j in range(N):
        if [i, j] not in visited:
            [size, visited] = bfs(house, i, j, N, visited)
            if size != 0:  # 1이 하나라도 포함된 사이즈 추가
                result.append(size)

            # 출력문
print(len(result))
for i in sorted(result):
    print(i)
