# -----------------------------------------------------
# Team Name: ENIGMATICS
# Lead: Senura
# String Manipulation: Pasan
# Algorithm : Thamindu
# Data Structures Specialist: Ranuga
# Problem Solver: Siyath (Behemoth)
# -----------------------------------------------------



import heapq


def dijkstra(graph, start, end, n):
    pq = [(0, start)]
    dist = {i: float('inf') for i in range(n)}
    dist[start] = 0

    while pq:
        current_dist, u = heapq.heappop(pq)

        if current_dist > dist[u]:
            continue

        for v, travel_time in graph[u]:
            if current_dist + travel_time < dist[v]:
                dist[v] = current_dist + travel_time
                heapq.heappush(pq, (dist[v], v))

    return dist[end]


def solve():
    T = int(input())
    for t in range(1, T + 1):
        n, m = map(int, input().split())
        graph = {i: [] for i in range(n)}

        for _ in range(m):
            city1, city2, departure_time, travel_time = input().split()
            departure_time = int(departure_time)
            travel_time = int(travel_time)


            if 18 <= departure_time < 24 or 0 <= departure_time < 6:
                if departure_time >= 18:
                    arrival_time = departure_time + travel_time
                else:
                    arrival_time = departure_time + travel_time  # next day


                if arrival_time <= 6 or (arrival_time >= 24 and arrival_time - 24 <= 6):
                    graph[city1].append((city2, travel_time))

        start_city, end_city = input().split()

        result = dijkstra(graph, start_city, end_city, n)

        if result == float('inf'):
            print(f"There is no route Jhon Doe can take.")
            # Case  # {t}: There is no route Jhon Doe can take.
        else:
            blood_needed = result // 1
            print(f"Jhon Doe needs {blood_needed} litre(s) of blood.")
            # Case #{t}: Jhon Doe needs {blood_needed} litre(s) of blood.



solve()